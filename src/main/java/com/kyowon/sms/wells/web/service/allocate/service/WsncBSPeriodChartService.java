package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncBSPeriodChartConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncBSPeriodChartDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBSPeriodChartDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncBSPeriodChartMapper;
import com.sds.sflex.common.utils.DateUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsncBSPeriodChartService {
    WsncBSPeriodChartMapper mapper;

    WsncBSPeriodChartConverter converter;

    @Transactional
    public int processBSPeriodChart(WsncBSPeriodChartDto.SearchReq dto) throws Exception {
        WsncBSPeriodChartDto.SearchBaseInfoRes baseInfoRes = mapper.selectPeriodChartBaseInfo(dto);

        //AC201_BS_GB1 != '00' 일 경우 아무것도 처리 하지 않는다.
        if (baseInfoRes == null || !"00".equals(baseInfoRes.bfsvcSppStpRsonCd())) {
            return 0;
        }

        /*******************************************************************************************************
         * 변수 선언부
         *******************************************************************************************************/
        int chekInstMths = 0; //설치차월
        int wrkTypDtlCnt; //1회성 작업 체크용 변수
        int chekCyclMths; //방문기준 차월수 ::: VST_NMN_N
        String chekVstDt; //방문예정일자
        String chekCockYn; //슬림형 정수기 품질개선(위생성 향상)을 위한 교체형 COCK TIP 적용 대상 상품이지만 생산년도가 1709 이전일 경우
        String vVs104CfrmDt; //고객이 요청한 방문일자
        String newWrkTypDtl; //작업유형상세
        String newPartCd; //주기표에 최종적으로 들어갈 자재코드(계절별맞춤형 필터 도입때문에)
        String vVs104CstmFltr; //고객이 마지막으로 신청한 필터가 있는지 체크
        String newVstGb; //방문구분 10 방문, 11 방문 매니저, 12 방문 엔지니어, 13 방문 홈케어, 20 택배
        String chekVstGb20 = "N"; //강제택배처리건(신안군보건소)
        String vVs107WrkDt; //주기표상 마지막 방문일자
        String priorityVstDt = ""; // 가구화 결과 ::: 방문예정일자(YYYYMMDD)
        int priorityVstDiff = 0; //가구화 결과 ::: 방문예정일자와 가구화 결과 방문예정일자와의 차월 수

        List<WsncBSPeriodChartDto.Search06Res> chart06ResList = mapper.selectBSPeriodChartBs03_06(baseInfoRes);
        WsncBSPeriodChartDvo processParam = converter.mapBaseInfoResToPeriodChartDvo(baseInfoRes);

        //주기표 생성 데이터가 없을 경우, 오류 로그 처리 후 종료.
        if (CollectionUtils.isEmpty(chart06ResList)) {
            processParam.setCrtErrCn("'NO DATA NOT FOUND!'");
            mapper.insertBSPeriodChartError(processParam);
            return 0;
        }

        chekVstGb20 = mapper.selectBSPeriodChartBs03_03(processParam); //강제택배처리건(신안군보건소)
        chekCyclMths = mapper.selectBSPeriodChartBs03_04(processParam); //방문기준 차월수
        vVs107WrkDt = mapper.selectBSPeriodChartBs03_05(processParam); //주기표상 마지막 방문일자
        chekCockYn = mapper.selectBSPeriodChartBs03_02(processParam); //슬림형 정수기 품질개선(위생성 향상)을 위한 교체형 COCK TIP 적용 대상 모델이지만 1709년 이전생산분이면 Y, Y이면 코크팁 주기에서 제거

        processParam.setChekCyclMths(chekCyclMths);
        processParam.setChekCockYn(chekCockYn);
        processParam.setChekVstGb20(chekVstGb20);

        /*******************************************************************************************************
         * 가구화 로직 (방문일자 및 차월 계산)
         *******************************************************************************************************/
        priorityVstDt = mapper.selectPriorityVstDt(dto);
        if (StringUtils.isNotEmpty(priorityVstDt)) {
            //가구화에 따른 방문월 padding 계산
            priorityVstDiff = mapper.selectPriorityVstDiff(priorityVstDt, chekCyclMths);
        }

        //AS-IS ::: WORK_X Loop(chart06Res)
        for (WsncBSPeriodChartDto.Search06Res chart06Res : chart06ResList) {
            //SELL_TP_CD = 1 ::: 렌탈
            if ("1".equals(baseInfoRes.sellTpCd()) && chart06Res.vstNmnN() == 0) {
                chekInstMths = chart06Res.vstNmnN();
                //GOTO GOTO_FOR;
                continue;
            }

            //SELL_TP_CD = 3 ::: 멤버십 (AS-IS는 2)
            if ("3".equals(baseInfoRes.sellTpCd()) && chart06Res.vstNmnN() == 0) {
                //TB_PDBS_RGBS_WK_BASE_DTL.TOT_STPL_MCN 로 대체

                chekInstMths = baseInfoRes.chekInstMths();
                chekInstMths = chekInstMths + chart06Res.vstNmnN();
            }

            List<WsncBSPeriodChartDto.Search07Res> chart07ResList = mapper.selectBSPeriodChartBs03_07(processParam);
            //AS-IS ::: C1 Loop(chart07Res)
            for (WsncBSPeriodChartDto.Search07Res chart07Res : chart07ResList) {

                if (baseInfoRes.chekInstMths() == 0) {
                    //GOTO GOTO_CR;
                    continue;
                }

                //DB2_ICDE IN ('4018', '4014')
                if ("DB2_ICDE".indexOf("'4018', '4014'") > 0) {

                    if (chart06Res.vstNmnN() == 6 && chekInstMths > 6) {
                        //GOTO GOTO_CR;
                        continue;
                    }

                    wrkTypDtlCnt = mapper.selectBSPeriodChartBs03_08(processParam);
                    if (wrkTypDtlCnt > 7 && chekInstMths < 37) {
                        //GOTO GOTO_CR;
                        continue;
                    }
                }

                //cherro ::: P_CYCL_TYP 조건 생략으로 로직 추가로 생각해야 함.
                if ("DB2_ICDE".indexOf("'4013'") > 0) {
                    wrkTypDtlCnt = mapper.selectBSPeriodChartBs03_09(processParam);
                    if (wrkTypDtlCnt > 0) {
                        //GOTO GOTO_CR;
                        continue;
                    }
                }

                //멤버십 가입일자가 없는 경우 (렌탈인경우인지 확인 필요) (멤버십 ::: "3".equals(baseInfoRes.sellTpCd()))
                if (StringUtils.isEmpty(baseInfoRes.cntrPdStrtdt())
                    || "01".equals(baseInfoRes.cntrPdStrtdt().substring(6, 8))) {
                    //기존 주기표에 데이터가 없는 경우 - 첫 계약인 경우
                    if (StringUtils.isEmpty(vVs107WrkDt)) {
                        chekVstDt = DateUtil.addMonths(baseInfoRes.istDt(), chekInstMths);
                        chekVstDt = mapper.selectBSPeriodChartBs03_10(chekVstDt);
                    } else {
                        chekVstDt = DateUtil.addMonths(baseInfoRes.istDt(), chekInstMths).substring(0, 6)
                            + vVs107WrkDt.substring(6, 8);
                    }
                } else {
                    //멤버십인 경우, 멤버십 가입일자를 가져와서 그대로 사용 (휴일체크 할 필요 없음) (cherro ::: 멤버십 조건 넣어야 할지 모르겠음.)
                    chekVstDt = DateUtil.addMonths(baseInfoRes.istDt(), chekInstMths).substring(0, 6)
                        + baseInfoRes.cntrPdStrtdt().substring(6, 8);
                }
                processParam.setChekVstDt(chekVstDt);
                vVs104CfrmDt = mapper.selectBSPeriodChartBs03_11(processParam);
                //고객 요청 방문일자가 있다면 그걸로 세팅, 아님 설치 차월 기준으로 계산한 일자 세팅
                //위의 차기 방문일자가 있는 경우만 일자 변경
                if (StringUtils.isNotEmpty(vVs104CfrmDt)) {
                    chekVstDt = vVs104CfrmDt;
                }

                //방문예정일이 없다면 다시 설치차월에 방문차월 더해서 계산
                if (StringUtils.isEmpty(chekVstDt)) {
                    chekVstDt = DateUtil.addMonths(baseInfoRes.istDt(), chekInstMths);
                }

                /*******************************************************************************************************
                 * 가구화 로직 (로직 첫 부분에서 계산한 차월 반영)
                *******************************************************************************************************/
                if (StringUtils.isNotEmpty(priorityVstDt) && priorityVstDiff != 0) {
                    chekVstDt = DateUtil.addMonths(chekVstDt, priorityVstDiff).substring(0, 6)
                        + priorityVstDt.substring(6, 8);
                }

                //월에 마지막일자보다 크다면 해당월 마지막 일자라 세팅
                if (DateUtil.getLastDateOfMonth(chekVstDt).compareTo(chekVstDt) < 0) {
                    chekVstDt = DateUtil.getLastDateOfMonth(chekVstDt);
                }

                newWrkTypDtl = chart07Res.wrkTypDtl();
                processParam.setNewWrkTypDtl(newWrkTypDtl);

                //위 로직에 포함. 제외
//                if("Y".equals(chekVstGb20)){
//                    newVstGb = "20";
//                } else {
//                    newVstGb = chart07Res.vstGb();
//                }
                newVstGb = chart07Res.vstGb();
                processParam.setNewVstGb(newVstGb);

                /*  23.02.20 차세대에서는 서비스내용이 다르다면 별도 주기를 생성하고 이용해서, 예외처리가 없게 해야 한다.
                    주기 생성시에 판단할지, 배정시에 제외 하고 배정 할지 판단 해야 함
                */
                //비데 1회성 작업 체크 2172 살균전극모듈
                if ("2172".equals(newWrkTypDtl)) {
                    wrkTypDtlCnt = mapper.selectBSPeriodChartBs03_12(processParam);
                    //3M4 일 경우 18, 36 두번 교체 / 3M4 가 아닌 경우 18, 36 중 1번 교체 (P_CYCL_TYP 조건은 삭제되었으므로 로직 다시 생각해야 함)
                    if (wrkTypDtlCnt >= 2 || wrkTypDtlCnt >= 1) {
                        //GOTO GOTO_CR;
                        continue;
                    }
                }

                /*23.02.20 차세대에서는 서비스내용이 다르다면 별도 주기를 생성하고 이용해서, 예외처리가 없게 해야 한다.
                 주기 생성시에 판단할지, 배정시에 제외 하고 배정 할지 판단 해야 함
                */
                /*안마의자 1회성 작업 체크 2163 주요패드교체 */
                /*22.09.08 한세인매니저님 확인, 49240000000 안마의자고급(H05B1)_금융리스 6M3 주기는 18개월마다 주요패드 교체 하는걸로 계약이 되어 있다고 함.*/
                if ("2163".equals(newWrkTypDtl)) {
                    wrkTypDtlCnt = mapper.selectBSPeriodChartBs03_13(processParam);
                    int vChk2163 = 0; //무상으로 안마의자 주요패드교체 했는지 체크 (cherro ::: 이 변수에 값 세팅하는 로직은 없음.)
                    if (wrkTypDtlCnt >= 1 || vChk2163 > 0) {
                        //GOTO GOTO_CR;
                        continue;
                    }
                }

                /*23.02.20 차세대에서는 서비스내용이 다르다면 별도 주기를 생성하고 이용해서, 예외처리가 없게 해야 한다.
                  주기 생성시에 판단할지, 배정시에 제외 하고 배정 할지 판단 해야 함
                */
                /*20.04.08 전기레인지(전기레인지 하이브리드(KW-RK1B1) 49280-000000) 1회성 작업 체크 2178 글라스상판교체 */
                if ("2178".equals(newWrkTypDtl)) {
                    // cherro ::: 이거 같은 쿼리가 있어서 그거 써도 될거 같은데...일단 selectBSPeriodChartBs03_12가 같음.
                    wrkTypDtlCnt = mapper.selectBSPeriodChartBs03_14(processParam);
                    int vChk2178 = mapper.selectBSPeriodChartBs03_15(processParam);
                    if (wrkTypDtlCnt >= 1 || vChk2178 > 0) {
                        //GOTO GOTO_CR;
                        continue;
                    }
                }

                /*23.02.20 차세대에서는 서비스내용이 다르다면 별도 주기를 생성하고 이용해서, 예외처리가 없게 해야 한다.
                 주기 생성시에 판단할지, 배정시에 제외 하고 배정 할지 판단 해야 함*/
                /*21.10.22 매트리스 탑퍼교체 1회성 작업체크 - 박성민 매니저님 확인  */
                if ("2186".equals(newWrkTypDtl)) {
                    wrkTypDtlCnt = mapper.selectBSPeriodChartBs03_16(processParam);
                    int vChk2186 = mapper.selectBSPeriodChartBs03_17(processParam);
                    if (wrkTypDtlCnt >= 1 || vChk2186 > 0) {
                        //GOTO GOTO_CR;
                        continue;
                    }
                }

                /*23.02.20 차세대에서는 서비스내용이 다르다면 별도 주기를 생성하고 이용해서, 예외처리가 없게 해야 한다.
                  주기 생성시에 판단할지, 배정시에 제외 하고 배정 할지 판단 해야 함
                */
                /*23.01.18 매트리스 탑퍼교체 1회성 작업체크 - 김민석 파트장님 확인  */
                if ("2174".equals(newWrkTypDtl)) {
                    wrkTypDtlCnt = mapper.selectBSPeriodChartBs03_18(processParam);
                    int vChk2178 = mapper.selectBSPeriodChartBs03_19(processParam); //AS-IS가 2178로 되어있음.
                    if (wrkTypDtlCnt >= 1 || vChk2178 > 0) {
                        //GOTO GOTO_CR;
                        continue;
                    }
                }

                //cherro ::: TO-BE Table이 없음.(TB_PDBS_ARCLE_CHOFLT_BASE_DTL -> TB_PDBS_LVLH_CSTMW_FILT_BASE)
                processParam.setVstGb(chart07Res.vstGb());
                processParam.setPartCd(chart07Res.partCd());
                newPartCd = mapper.selectBSPeriodChartBs03_20(processParam);
                processParam.setNewPartCd(newPartCd);

                /*20.02.27 이영진 - 고객이 마지막으로 신청한 필터가 있는지 체크*/
                /*21.03.04 확인 결과 현재 다음 방문시 필터를 받을때 방문이냐 택배냐 구분해서 맞는 필터를 받음으로 아래 로직이 필요 없음. 상황봐서 제거 필요함*/
                //cherro ::: TO-BE Table이 없음.(TB_PDBS_ARCLE_CHOFLT_BASE_DTL)
                vVs104CstmFltr = mapper.selectBSPeriodChartBs03_21(processParam);
                //고객 요청 필터가 있으면 맞춤형필터보다 고객 요청 필터를 우선
                if (StringUtils.isNotEmpty(vVs104CstmFltr)) {
                    newPartCd = vVs104CstmFltr;
                }

                processParam.setChngStg(chart07Res.chngStg());
                processParam.setItemKnd(chart07Res.itemKnd());
                processParam.setQty(chart07Res.qty());
                mapper.insertBSPeriodChart(processParam);

                //<<GOTO_CR>>

            }

            //<<GOTO_FOR>>

        }

        //<<GOTO_END>>

        return 1;
    }

}
