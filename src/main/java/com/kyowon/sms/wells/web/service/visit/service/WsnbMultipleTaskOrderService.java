package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbMultipleTaskOrderDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbMultipleTaskOrderDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbMultipleTaskOrderMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-S-0012 다건 작업오더, 정보변경 처리
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.02.09
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbMultipleTaskOrderService {
    private WsnbMultipleTaskOrderMapper mapper;

    /**
     * W-SV-S-0012 다건 작업오더, 정보변경 처리
     * TODO : 테이블 정의 완료 시 정리
     * @param dto : [{  asIstOjNo : AS설치대상번호, histChDtm : 이력변경일시, cntrNo : 계약번호, cntrSn : 계약일련번호,
     *            cntrCst_no : 계약고객번호, inChnlDvCd : 입력채널구분코드, svBizHclsfCd : 서비스업무대분류코드,
     *            svBizDclsfCd : 서비스업무세분류코드, rcpSvBizDclsfCd : 접수서비스업무세분류코드, rcpdt : 접수일자,
     *            rcpHh : 접수시간, urgtYn : 긴급여부, vstRqdt : 방문요청일자, vstAkHh : 방문요청시간,
     *            cnslTpHclsfCd : 상담유형대분류코드, cnslTpMclsfCd : 상담유형중분류코드, cnslTpLclsfCd : 상담유형소분류코드,
     *            asRefriDvCd : AS유무상구분코드, bfsvcRefriDvCd : BS유무상구분코드, smsFwYn : SMS발송여부, dpDvCd : 입금구분코드,
     *            svEtAmt : 서비스예상금액, svCnrOgId : 서비스센터조직ID, mrtStatCd : 자료상태코드, pdCd : 상품코드,
     *            pdGdCd : 상품등급코드, pdUswyCd : 상품용도코드, cstSvAsnNo : 고객서비스배정번호, cnslMoCn : 상담메모내용,
     *            cnslDtlpTpCd : 상담세부유형코드, asAkDvCd1 : AS요청구분코드1, asAkDvCd2 : AS요청구분코드2,
     *            istllKnm : 설치자한글명, adrDvCd : 주소구분코드, istAdr : 설치주소 }]
     */
    public int saveMultipleTaskOrders(SaveReq dto) throws Exception {
        int processCount = 0;
        WsnbMultipleTaskOrderDvo dvo = new WsnbMultipleTaskOrderDvo();

        int instDtCnt = 0;
        int reqdDtCnt = 0;
        int asIstAsnIzCnt = 0;
        int stopDtcnt = 0;
        int useMonths = 0;
        int totChangeCnt = 0;
        int rangeChnageCnt = 0;
        int rangeChangeBsCnt = 0;
        int itemCnt = 0;
        int asIstOjIzCnt = 0;

        /* KSS 접수 건이면 작업상세코드 재확인 */
        if (List.of("3", "9").contains(dto.inChnlDvCd()) && List.of("1", "2").contains(dto.mrtStatCd())
            && !StringUtils.startsWith(dto.svBizDclsfCd(), "7")) {
            if ("Y".equals(dto.compYn())) {
                dvo.setSvBizDclsfCd("1124");
            } else if ("Y".equals(dto.mchnClnOjYn()) || "D".equals(dto.compYn())) {
                /*상태코드가 전기레인지 체크*/
                int rangeCnt = mapper.selectCountItemizationByItemGroup(dto.cntrNoB());
                /*설치고객코드가 전기레인지 체크*/
                int rangeCnt2 = mapper.selectCountItemizationByItemGroup(dto.cntrNo());
                /*철거 가능한 상대 코드가 존재하는지 체크*/
                int reqdCnt = mapper.selectCountItemizationByCntrNoB(dto.cntrNoB());

                if (rangeCnt > 0 && rangeCnt2 == 0) {
                    dvo.setSvBizDclsfCd("1126"); //이종간기변
                } else if (reqdCnt == 0 && "D".equals(dto.mchnClnOjYn())) {
                    dvo.setSvBizDclsfCd("1122"); //자사미회수
                } else {
                    dvo.setSvBizDclsfCd("1121"); //자사회수
                }
            } else {
                dvo.setSvBizDclsfCd(dto.svBizDclsfCd());
            }
        } else if (List.of("2", "3").contains(dto.mrtStatCd())) {
            //wrkTypDtl, cfrmStusWrk, dataStus, ac221CfrmDt, ac221ProcStus
            WsnbMultipleTaskOrderDvo res = mapper.selectWorkRequidationItemization(dto.asIstOjNo());
            dvo.setSvBizDclsfCd(res.getSvBizDclsfCd());
            dvo.setWkAcpteStatCd(res.getWkAcpteStatCd());
            dvo.setMtrStatCd(res.getMtrStatCd());
            dvo.setWkAcpteDt(res.getWkAcpteDt());
            dvo.setWkPrgsStatCd(res.getWkPrgsStatCd());
        } else {
            dvo.setSvBizDclsfCd(dto.svBizDclsfCd());
        }

        /* 고객명이 없으면 구한다. */
        if (dto.custKnm().trim().length() == 0) {
            dvo.setRcgvpKnm(mapper.selectRcgvpKnm(dto.cntrNo()));
        } else {
            dvo.setRcgvpKnm(dto.rcgvpKnm());
        }
        String[] cstNm = {dvo.getRcgvpKnm()};

        if ("1".equals(dto.mrtStatCd())) {
            /* 정상 접수 건인지 체크 */

            if (StringUtils.startsWith(dvo.getSvBizDclsfCd(), "11")) {
                /* 설치 오더인데 설치 일자가 이미 있는지 체크 */
                instDtCnt = mapper.selectCountInstallationDate(dto.cntrNo());
            } else if (StringUtils.startsWith(dvo.getSvBizDclsfCd(), "34")) {
                /* 철거 오더인데 철거 일자가 이미 있는지 체크 */
                reqdDtCnt = mapper.selectCountRequidationDate(dto.cntrNo());
            }
            /* 해당 고객에 미완료 된 동일 유형의 오더 건이 존재 하는지 체크 */
            asIstAsnIzCnt = mapper.selectCountAsIstAsnIz(dvo.getSvBizDclsfCd(), dto.cntrNo());
            /* 고객중지여부 확인 */
            stopDtcnt = mapper.selectCountStopDate(dto.cntrNo());

            if (DateUtil.getNowDayString().equals(dto.vstRqdt().trim())
                && List.of("3", "4", "5").contains(dto.inChnlDvCd())) {
                BizAssert.isTrue(false, "MSG_ALT_TOD_VST_AK_REJ");
            }
            BizAssert.isFalse(instDtCnt > 0, "MSG_ALT_ARDY_IST", cstNm);
            BizAssert.isFalse(reqdDtCnt > 0, "MSG_ALT_ARDY_REQD", cstNm);
            String cntrNo = dto.cntrNo().substring(1, 4) + "-" + dto.cntrNo().substring(5);
            String workContent = mapper.selectWorkContent(dvo.getSvBizDclsfCd());
            String[] param = {dvo.getRcgvpKnm(), cntrNo, workContent};
            BizAssert.isFalse(asIstAsnIzCnt > 0, "MSG_ALT_ARDY_WK", param);

            if ("Y".equals(dto.mchnClnOjYn()) && dto.cntrNoB().length() == 0) {
                BizAssert.isTrue(false, "MSG_ALT_RET_NOT_CNTRNO", cstNm);
            }

            if (StringUtils.startsWith(dvo.getSvBizDclsfCd(), "34") && stopDtcnt > 0) {
                BizAssert.isTrue(false, "MSG_ALT_DLQ_SV_STP");
            }
            if (StringUtils.startsWith(dvo.getSvBizDclsfCd(), "3123")
                || StringUtils.startsWith(dvo.getSvBizDclsfCd(), "3124")) {
                /*21.12.22 이영진, 김예은 매니저님요청, 라이트상품(6M3) 상판교체 서비스 없음*/
                if (StringUtils.startsWith(dvo.getSvBizDclsfCd(), "3124")
                    && !"6M1".equals(dto.svPrd().trim().replace("M0", "M1"))) {
                    BizAssert.isTrue(false, "MSG_ALT_NOT_ESTOV_TOP_CHNG", cstNm);
                }

                /* 사용 개월 체크 */
                useMonths = mapper.selectUseMonth(dto.vstRqdt(), dto.cntrNo());
                /*21.10.18 최고급안마의자 토탈체인지 서비스 수행 여부 체크*/
                totChangeCnt = mapper.selectCountChangeTotal(dto.cntrNo());
                /*21.10.19 전기레인지 글라스 상판 교체 서비스 수행 여부 체크, 또는 특정자재 사용 여부 체크*/
                /* 49280000000 전기레인지 하이브리드(KW-RKB1)
                   49281000000 전기레인지 RM523ABA 하이브리드 3구
                   49282000000 전기레인지 RM723ABA 인덕션 3구 */
                rangeChnageCnt = mapper.selectCountRangeChange(dto.cntrNo());
                /*21.10.19 전기레인지 글라스 상판 교체 BS 서비스 수행 여부 체크*/
                rangeChangeBsCnt = mapper.selectCountRangeChangeBs(dto.cntrNo());
                /*21.10.19 안마의자 토탈체인지, 전기레인지 상판교체 자재 사용으로 서비스 체크*/
                itemCnt = mapper.selectCountItem(dto.cntrNo());

                /*21.10.18 이영진, 최고급 안마의자 토탈 체인지 서비스, 전기레인지 상판 교체 설치 차월 체크*/
                if (StringUtils.startsWith(dvo.getSvBizDclsfCd(), "3123")) {
                    BizAssert.isFalse(useMonths <= 12, "MSG_ALT_NOT_YET_12M", cstNm);
                    /*21.10.18 이영진, 최고급 안마의자 토탈 체인지 서비스 체크*/
                    if (totChangeCnt > 0 || itemCnt > 0) {
                        BizAssert.isTrue(false, "MSG_ALT_ARDY_TOT_CHNG", cstNm);
                    }
                }
                if (StringUtils.startsWith(dvo.getSvBizDclsfCd(), "3124")) {
                    BizAssert.isFalse(useMonths <= 24, "MSG_ALT_NOT_YET_24M", cstNm);
                    /*21.10.19 이영진, 전기레인지 글라스 상판 교체 서비스 체크*/
                    if ((rangeChnageCnt > 0 || rangeChangeBsCnt > 0) || itemCnt > 0) {
                        BizAssert.isTrue(false, "MSG_ALT_ARDY_RANGE_TOP_CHNG", cstNm);
                    }
                }

            }
        }

        if (List.of("2", "3").contains(dto.mrtStatCd())) {
            /*해당접수키로 존재하는지 체크*/
            asIstOjIzCnt = mapper.selectCountAsIstOjIz(dto);

            if (!"00".equals(dvo.getWkPrgsStatCd()) && !"10".equals(dvo.getWkPrgsStatCd())) {
                BizAssert.isTrue(false, "MSG_ALT_NOT_MDFC_CAN_STAT");
            }
            if (DateUtil.getNowDayString().equals(dto.vstRqdt().trim())
                && List.of("3", "4", "5").contains(dto.inChnlDvCd())) {
                BizAssert.isTrue(false, "MSG_ALT_TOD_VST_AK_REJ");
            }
            BizAssert.isFalse(asIstOjIzCnt == 0, "MSG_ALT_RCP_DIFF_WAY", cstNm);
            if (DateUtil.getNowDayString().equals(dvo.getWkAcpteDt())) {
                BizAssert.isTrue(false, "MSG_ALT_TOD_VST_MDFC_CAN_IMP", cstNm);
            }
            /* 수락 상태이면 수정/취소 안되게 */
            BizAssert.isFalse("Y".equals(dvo.getWkAcpteStatCd()), "MSG_ALT_ARDY_ACPTE_STAT", cstNm);
            /* 취소 상태이면 수정/취소 안되게 */
            BizAssert.isFalse("3".equals(dvo.getMtrStatCd()), "MSG_ALT_ARDY_CAN_STAT", cstNm);
        }

        /*LC_ALLOCATE_AC211TB 키를 생성한다.
        P_IN_GB 입력구분 1:CC, 2:KIWI, 3:KSS 1이면 CC에서 입력된 P_WRK_DT, P_SEQ 사용 아니면 자체 생성
        SEQ_LC_ALLOCATE_AC211TB 5자리 시퀀스 생성, MAX 되면 CYCLE*/
        WsnbMultipleTaskOrderDvo res2 = mapper.selectAsIstOjIzKey(dto);
        dvo.setInChnlDvCd(res2.getInChnlDvCd());
        dvo.setSvBizHclsfCd(res2.getSvBizHclsfCd());
        dvo.setRcpdt(res2.getRcpdt());
        dvo.setAsIstOjNo(res2.getAsIstOjNo());

        /*KIWI 코드 및 판매 코드를 세팅한다
         *TODO : saleCd 뭔지확인 + GET_DB2_ICDE 처리
         */
        if ("000".equals(dto.saleCd())) {
            dvo.setBasePdCd("60100000000");
            dvo.setSaleCd("100");
        } else if (dto.basePdCd().trim().length() > 0 || dto.basePdCd().trim() != null) {
            dvo.setBasePdCd(dto.basePdCd());
        } else if ((dto.basePdCd().trim().length() == 0 || dto.basePdCd().trim() == null)
            && dto.saleCd().trim().length() > 0) {
            dvo.setBasePdCd(mapper.selectBasePdCdBySaleCd(dto.saleCd()));
        } else {
            dvo.setBasePdCd(mapper.selectBasePdCdByCntrNo(dto.cntrNo()));
        }

        if (dto.saleCd().trim().length() > 0) {
            dvo.setSaleCd(dto.saleCd());
        } else {
            /*TODO : GET_DB2_ICDE*/
        }

        /* 테이블별로 dvo 만들어서 처리. */
        /*1. 고객서비스수행내역(TB_SVPD_CST_SV_EXCN_IZ)을 저장한다.*/
        if (!"3".equals(dto.mrtStatCd()) && dto.rnadr().trim().length() > 0) {
            if (mapper.selectCountExecutionItemization(dto.cntrNo()) != 0) {
                processCount += mapper.updateExecutionItemization(dvo);
            } else {
                processCount += mapper.insertExecutionItemization(dvo);
            }

            /*TODO : 216TB처리. 사라진 테이블이라 처리필요한지? */

            /* TODO : 정보 변경 프로시저 호출  (SP_WRK_TYP_DTL_7100) */

        }

        /* 정보 변경이 아니라면 오더 생성 */
        if (!(StringUtils.startsWith(dvo.getSvBizDclsfCd(), "7"))) {
            /* 받아온 접수키가 존재하는데 P_DATA_STUS 수정(2)이나 취소(3)가 아닌경우 에러 로그 TB_SVPD_CST_SVAS_IST_OJ_ERR_IZ에 저장 */
            if (!"1".equals(dto.inChnlDvCd().trim()) && dto.asIstOjNo().trim().length() > 0
                && List.of("2", "3").contains(dto.mrtStatCd())) {
                processCount += mapper.insertErrorItemization(dvo);
            } else if ("1".equals(dto.mrtStatCd())) {
                processCount += mapper.insertIstObjectItemization(dvo);
            } else if ("2".equals(dto.mrtStatCd())) {
                processCount += mapper.updateIstObjectItemization(dvo);
            } else if ("3".equals(dto.mrtStatCd())) {
                processCount += mapper.updateIstObjectItemizationByPk(dvo);
            }

            if (!"3".equals(dto.mrtStatCd()) && List.of("1310", "3531").contains(dto.inChnlDvCd())
                && dto.partList() != null) {
                mapper.deleteAsPutItemIz(dvo);
                /*PART_LIST 자재코드,수량,금액 | 자재코드,수량,금액 | ~~~
                * 위 형태의 List를 쪼개서 자재, 수량, 금액으로 저장해서 for문 insert
                * */
                String[] partList = StringUtils.split(dto.partList(), "|");
                for (String part : partList) {
                    String[] arr = StringUtils.split(part, ",");
                    dvo.setPartCd(arr[0]);
                    dvo.setPartQty(arr[1]);
                    dvo.setPartAmt(arr[2]);
                    processCount += mapper.insertAsPutItemIz(dvo);
                }
            }
            String cstSvAsnNo = "";
            /*고객서비스AS설치배정내역(TB_SVPD_CST_SV_AS_IST_ASN_IZ) 키를 조회 한다.*/
            if (List.of("2", "3").contains(dto.mrtStatCd())) {
                cstSvAsnNo = mapper.selectCustomerServiceAssignNo(dvo);
                dvo.setCstSvAsnNo(cstSvAsnNo);
                /* 로그저장(TB_SVPD_CST_SV_AS_IST_ASN_HIST) */
                mapper.insertAsInstallationAssignHist(dvo);
                mapper.deleteAsInstallationAssignHist(dvo.getCstSvAsnNo());
                /*모종 고객이라면 확정되지 않은 해당 방문 스케쥴의 모종 배송 스케쥴을 삭제한다.*/
                /*  TODO :  ST101 테이블 매핑 확인 후 */
            } else {
                /* TODO : SEQ_LC_ALLOCATE_AC221TB.NEXTVAL 확인해보고 현재 db 형식에 맞게 수정 */
                String seq = "1";
                cstSvAsnNo = dvo.getInChnlDvCd() + DateUtil.getNowDayString() + StringUtils.leftPad(seq, 6, "0");
            }

            if (!"3".equals(dto.mrtStatCd())) {
                /* 고객서비스AS설치배정내역(TB_SVPD_CST_SV_AS_IST_ASN_IZ) 키를 생성한다.*/
                /* TODO : SEQ_LC_ALLOCATE_AC221TB.NEXTVAL 확인해보고 현재 db 형식에 맞게 수정 */
                String seq = "1";
                cstSvAsnNo = dvo.getInChnlDvCd() + DateUtil.getNowDayString() + StringUtils.leftPad(seq, 6, "0");
                dvo.setCstSvAsnNo(cstSvAsnNo);

                /*배정 정보를 구한다.*/
                /* TODO : 테이블 정리 후 로직 추가 */

                /* 고객서비스AS설치배정내역(TB_SVPD_CST_SV_AS_IST_ASN_IZ), HIST insert*/
                mapper.insertAsInstallationAssign(dvo);
                mapper.insertAsInstallationAssignHist(dvo);

                /*모종 고객이라면 생성된 방문 오더 기준 모종 배송 스케쥴을 업데이트 또는 인서트 해주고 방문 오더를 생성한다.*/
                /*  TODO :  ST101 테이블 매핑 확인 후 */

            }

            /* 고객서비스AS설치배정내역(TB_SVPD_CST_SV_AS_IST_ASN_IZ) 배정 키 update*/
            mapper.updateAsInstallationAssign(dvo);
        }

        return processCount;
    }
}
