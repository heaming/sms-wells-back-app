package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbGiroDepositMgtConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SaveIntegrationReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SaveErrosReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchDtlStateRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchErrosRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchLedgerItemizationReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchLedgerItemizationRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchSumRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroDepositDeleteInfoDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroDepositErrorSaveDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroDepositSaveDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroDepositSaveInfoDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbGiroDepositMgtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WwdbGiroDepositMgtService {

    private final WwdbGiroDepositMgtMapper mapper;
    private final WwdbGiroDepositMgtConverter convert;

    /**
     * 지로입금 목록조회
     *
     * @param req
     * @param pageInfo
     * @return PagingResult
     */
    @Transactional
    public PagingResult<SearchRes> getBillingDocumentMgtPages(SearchReq dto, PageInfo pageInfo) {

        return mapper.selectGiroDepositMgt(dto, pageInfo);
    }

    @Transactional
    public SearchSumRes getGiroDepositSum(SearchReq dto) {

        return mapper.selectGiroDepositSum(dto);
    }

    @Transactional
    public List<SearchRes> getBillingDocumentMgtExcels(SearchReq dto) {

        return mapper.selectGiroDepositMgt(dto);
    }

    @Transactional
    public int saveBillingDocumentMgt(List<SaveReq> dtos) throws Exception {
        int processCount = 0;

        WwdbGiroDepositDeleteInfoDvo deleteDvo = new WwdbGiroDepositDeleteInfoDvo();

        String errorChk = ""; //에러체크

        if (dtos.size() > 0) {
            String toDay = mapper.selectGiroDepositDate();

            for (int i = 0; i < dtos.size(); i++) {
                WwdbGiroDepositSaveDvo dvo = convert.mapSaveWwwdbGiroDepositSaveDvo(dtos.get(i));
                dvo.setGiroDpUldDtm(toDay);
                processCount += mapper.inertGiroDeposit(dvo); //원장내역 저장

            }

            // 중복 제거
            List<SaveReq> duplicates = dtos.stream().distinct().collect(Collectors.toList());

            String[] fntDt = new String[dtos.size()];

            for (int i = 0; i < duplicates.size(); i++) {
                if (duplicates.get(i).giroDpMtrDvCd().equals("22")) {
                    fntDt[i] = duplicates.get(i).rveDt();
                }
            }

            deleteDvo.setFntDt(fntDt);

            processCount += mapper.deleteGiroDepositItemization(deleteDvo);

            List<WwdbGiroDepositSaveInfoDvo> dvos = mapper.selectGiroDepositItemizationInfo(); //원장내역 데이터를 가공한다.

            for (WwdbGiroDepositSaveInfoDvo infoDvo : dvos) {

                SearchDtlStateRes selectDtlState = mapper.selectDtlState(infoDvo);

                if (Objects.isNull(selectDtlState)) { //자료가없으면 주문 x
                    errorChk = "1";
                } else if (!selectDtlState.cntrDtlStatCd().equals("101")) { //정상처리가 아니면 2
                    errorChk = "2";
                } else {
                    errorChk = "3"; //정상처리
                }
                infoDvo.setProcsErrTpCd(errorChk);

                processCount += mapper.inertGiroDepositItemization(infoDvo); //지로입금내역 저장
                processCount += mapper.updateGiroDeposit(infoDvo);
            }
        }
        return processCount;
    }

    @Transactional
    public int saveBillingCreateDocument(SaveIntegrationReq dto) throws Exception {

        int processCount = 0;

        WwdbGiroDepositSaveInfoDvo dvo = convert.mapSaveGiroDepositSaveDvo(dto);

        String rveDt = dto.rveDt(); //입금일자
        String fntDt = dto.fntDt(); //실적일자

        /*입금등록 해야하는 데이터 조회 */
        //        List<WwdbGiroDepositMgtDto.SearchDepositRes> inserList = mapper.selectGiroDepositList(dto);
        //        SearchReq giroDto = new SearchReq(dto.rveDt(), dto.fntDt(), null, "2", null, null);
        //        List<SearchRes> insertList = mapper.selectGiroDepositMgt(giroDto);
        //
        //        int tcnt = 0; // 자료　건수
        //        int rcnt = 0; // 제외　건수
        //        int acnt = 0; // 기　처리건
        //        int ecnt = 0; // 오류　건수
        //        int wcnt = 0; // 처리　건수
        //        int jcnt = 0; // 묶음건수
        //        int dcnt = 0; // 단일건수
        //
        //        /*입금등록 해야하는 데이터 조회 후 생성*/
        //        for (SearchRes list : insertList) {
        //
        //            //제외 건수
        //            if (list.giroRveDvCd().equals("R") ||
        //                list.giroRveDvCd().equals("A")) {
        //                rcnt = rcnt + 1; //제외 건수
        //                continue;
        //            }
        //
        //            //기 처리건 찾아보기
        //
        //            //지료OCR묶음 대표조회
        //            Map<String, Object> regMap = new HashMap<String, Object>();
        //            regMap.put("giroOcrBndlYm", fntDt.substring(0, 6));
        //            regMap.put("dgCntrNo", list.cntrNo());
        //            regMap.put("dgCntrSn", list.cntrSn());
        //            regMap.put("fntDt", dto.fntDt());
        //            regMap.put("rveDt", dto.rveDt());
        //
        //            int selectGiroDepositCount = mapper.selectGiroDepositCount(regMap); //지로OCR묶음기본 대표고객 조회
        //
        //            if (selectGiroDepositCount == 0) { //만약 대표고객이 0이면
        //
        //            } else {
        //
        //                // 지로입금 처리 할 데이터 조회
        //                SearchReq giroS = new SearchReq(
        //                    dto.rveDt(), dto.fntDt(), null, "2", list.dgCntrNo(), list.dgCntrSn()
        //                );
        //
        //                regMap = new HashMap<String, Object>();
        //                regMap.put("giroOcrBndlYm", fntDt.substring(0, 6));
        //                regMap.put("dgCntrNo", list.cntrNo());
        //                regMap.put("dgCntrSn", list.cntrSn());
        //
        //                //계약상세상태코드 정상(101)인 설정금액
        //                int settingAmount = mapper.selectGiroDepositSettingAmount(regMap); //설정금액
        //                int payAmount = Integer.parseInt(list.rveAmt());// 납입금액
        //
        //                if (settingAmount != payAmount) { //만약 설정금액이랑 납입금액이 다를 경우
        //
        //                    Map<String, Object> errorRegMap = new HashMap<String, Object>(); //오류건 처리 데이터 입력
        //
        //                    errorRegMap.put("procsErrTpCd", "4"); //PROCS_ERR_TP_CD (통합발행-오류포함(4))
        //                    errorRegMap.put("giroNo", list.giroNo()); //GIRO_NO 지로번호
        //                    errorRegMap.put("fntDt", dto.fntDt()); //FNT_DT 실적일자
        //                    errorRegMap.put("rveDt", dto.rveDt()); //RVE_DT 수납일자
        //                    errorRegMap.put("giroDpMtrDvCd", list.giroDpMtrDvCd()); //GIRO_DP_MTR_DV_CD 구분코드
        //                    errorRegMap.put("giroDpSn", list.giroDpSn()); //GIRO_DP_SN 일련번호
        //
        //                    processCount += mapper.updateGiroDepositItemization(errorRegMap);//PROCS_ERR_TP_CD (통합발행-오류포함(4))
        //
        //                    ecnt = ecnt + 1; // 오류　건수
        //                } else { //설정금액과 납입금액이 일치한다면
        //
        //                }
        //
        //            }
        //
        //        }

        processCount += mapper.inertIntegrationItemization(dvo);
        processCount += mapper.updateIntegrationItemization(dvo);

        //
        //                int tcnt = 0; // 자료　건수
        //                int rcnt = 0; // 제외　건수
        //                int acnt = 0; // 기　처리건
        //                int ecnt = 0; // 오류　건수
        //                int wcnt = 0; // 처리　건수
        //                int jcnt = 0; // 묶음건수
        //                int dcnt = 0; // 단일건수
        //                long saveWamt = 0; // 처리총금액
        //                long cwtamt = 0; // 설정금액
        //                long cwqamt = 0; // 납입금액
        //                long workAmt1 = 0;
        //                long workTamt = 0;
        //                long saveQamt = 0;
        //                long saveSamt = 0;
        //
        //                Map<String, Object> regMap = new HashMap<String, Object>();
        //
        //                        String rveDt = dto.rveDt(); //입금일자
        //                        String fntDt = dto.fntDt(); //실적일자
        //
        //                List<SearchDepositRes> inserList = mapper.selectGiroDepositList(dto);
        //
        //                if (inserList.size() > 0) {
        //                    for (int i = 0; i < inserList.size(); i++) {
        //                        tcnt += 1; //자료 건수
        //
        //                        if (inserList.get(i).giroRveDvCd().equals("R") ||
        //                            inserList.get(i).giroRveDvCd().equals("A")) {
        //                            rcnt = rcnt + 1; //제외 건수
        //                            continue;
        //                        }
        //
        //                        regMap = new HashMap<String, Object>();
        //                        regMap.put("giroOcrBndlYm", fntDt.substring(0, 6));
        //                        regMap.put("dgCntrNo", inserList.get(i).cntrNo());
        //                        regMap.put("dgCntrSn", inserList.get(i).cntrSn());
        //                        //                SearchDepositCountReq searchDepositCountReq = new SearchDepositCountReq(
        //                        //                    fntDt.substring(0, 6), inserList.get(i).cntrNo(), inserList.get(i).cntrSn()
        //                        //                );
        //                        int selectGiroDepositCount = mapper.selectGiroDepositCount(regMap); //지로OCR묶음기본 대표고객 조회
        //
        //                        if (selectGiroDepositCount == 0) { // 일반건과　통합건　구분　처리
        //
        //                            //일반　지로　입금　처리--------------------------------
        //                            regMap = new HashMap<String, Object>();
        //                            regMap.put("cntrNo", inserList.get(i).cntrNo());
        //                            regMap.put("cntrSn", inserList.get(i).cntrSn());
        //
        //                            SearchContractDetailRes selectContractDetail = mapper.selectContractDetail(regMap); //렌탈고객 조회
        //
        //                            //                    if (StringUtil.isNull(selectContractDetail.toString())) { //이건 확인해보자 업데이트
        //                            if (Objects.isNull((selectContractDetail))) { //이건 확인해보자 업데이트
        //                                ecnt += 1;
        //                                regMap.put("procsErrTpCd", "1"); //1번 주문자료 없음
        //                                regMap.put("giroNo", inserList.get(i).giroNo());
        //                                regMap.put("fntDt", inserList.get(i).fntDt());
        //                                regMap.put("giroDpMtrDvCd", inserList.get(i).giroDpMtrDvCd());
        //                                regMap.put("giroDpSn", inserList.get(i).giroDpSn());
        //                                mapper.updateGiroDepositItemization(regMap); //파라미터 넘겨야함
        //                                //                        regMap.put("CWFLG1",  "1");  // 주문자료　없음！
        //                                //                        procCnt = dpstMapper.updateGiroDpstDat(regMap); // 지로입금 자료 수정 CW5520P
        //                                //                        if(procCnt != 1){
        //                                //                            throw new BusinessException("지로입금 자료 수정 CW5520P 오류!");
        //                                //                        }
        //                            } else if (!selectContractDetail.cntrDtlStatCd().equals("101")) { //계약상세상태코드 정상이 아니면 취소건이있다는듯 이거는 물어보자 CNTR_DTL_STAT_CD
        //                                ecnt += 1;
        //
        //                                regMap.put("procsErrTpCd", "2"); //2번 취소된 고객
        //                                regMap.put("giroNo", inserList.get(i).giroNo());
        //                                regMap.put("fntDt", inserList.get(i).fntDt());
        //                                regMap.put("giroDpMtrDvCd", inserList.get(i).giroDpMtrDvCd());
        //                                regMap.put("giroDpSn", inserList.get(i).giroDpSn());
        //
        //                                mapper.updateGiroDepositItemization(regMap); //이거 파라미터 넘겨야함
        //
        //                                //
        //                                //                        regMap.put("CWFLG1",  "2");  // 취소된　고객코드
        //                                //                        procCnt = dpstMapper.updateGiroDpstDat(regMap); // 지로입금 자료 수정 CW5520P
        //                                //                        if(procCnt != 1){
        //                                //                            throw new BusinessException("지로입금 자료 수정 CW5520P 오류!");
        //                                //                        }
        //                            } else {
        //                                regMap = new HashMap<String, Object>(); //일단 더 짜야하니까 다시 확인
        //                                regMap.put("cntrNo", inserList.get(i).cntrNo());
        //                                regMap.put("cntrSn", inserList.get(i).cntrSn());
        //
        //                                //
        //
        //                                //                        수납상세 INSERT 문이랑 히스토리 INSERT문 짜야함
        //                            }
        //
        //                        } else { // 통합발행　지로　입금　처리
        //                            regMap = new HashMap<String, Object>();
        //                            regMap.put("giroOcrBndlYm", fntDt.substring(0, 6)); // 발행년
        //                            regMap.put("dgCntrNo", inserList.get(i).cntrNo()); //
        //                            regMap.put("dgCntrSn", inserList.get(i).cntrSn()); // 대표고객년도
        //
        //                            int settingAmount = mapper.selectGiroDepositSettingAmount(regMap); //계약상세상태코드 정상(101)인 설정금액
        //                            int pyAmt = Integer.parseInt(inserList.get(i).pyAmt()); //납입금액
        //
        //                            if (settingAmount != pyAmt) { // 설정금액  납입금액
        //                                regMap = new HashMap<String, Object>();
        //                                regMap.put("procsErrTpCd", "4"); //4번 통합발행-오류포함
        //                                regMap.put("giroNo", inserList.get(i).giroNo());
        //                                regMap.put("fntDt", inserList.get(i).fntDt());
        //                                regMap.put("giroDpMtrDvCd", inserList.get(i).giroDpMtrDvCd());
        //                                regMap.put("giroDpSn", inserList.get(i).giroDpSn());
        //                                mapper.updateGiroDepositItemization(regMap); //이거도 수정해야함
        //                                ecnt += 1; //오류 건수
        //                            } else {
        //                                regMap = new HashMap<String, Object>();
        //                                regMap.put("giroOcrBndlYm", fntDt.substring(0, 6)); // 발행년
        //                                regMap.put("dgCntrNo", inserList.get(i).cntrNo()); //
        //                                regMap.put("dgCntrSn", inserList.get(i).cntrSn()); // 대표고객년도
        //                                List<SearchDepositSettingRes> selectGiroDepositSettingList = mapper
        //                                    .selectGiroDepositSettingList(regMap);
        //
        //                                if (selectGiroDepositSettingList.size() > 0) {
        //                                    for (int j = 0; j < selectGiroDepositSettingList.size(); j++) {
        //                                        int dpSeAmt = Integer.parseInt(selectGiroDepositSettingList.get(j).dpSeAmt()); //설정금액
        //
        //                                        if (dpSeAmt > pyAmt) { // 설정금액 > 납입금액
        //                                            workAmt1 = pyAmt;
        //                                            pyAmt = 0;
        //                                        } else {
        //                                            workAmt1 = dpSeAmt;
        //                                            pyAmt = pyAmt - dpSeAmt;
        //                                        }
        //                                        saveSamt = saveSamt + workAmt1;
        //                                        //-----------------------------------------------------------------------------------
        //
        //                                        //수납상세 INSERT 문이랑 히스토리 INSERT문 짜야함
        //
        //                                    }
        //
        //                                }
        //                            }
        //
        //                        }
        //
        //                        //                if (StringUtil.isEquals("UP", ConvertUtil.castString(insertList.get(i).get("CWFLG3")))) {
        //                        //                    acnt = acnt + 1; //기　처리건
        //                        //                    continue;
        //                        //                }
        //
        //                    }
        //
        //                }
        return processCount;

    }

    @Transactional
    public PagingResult<SearchErrosRes> getBillingDocumentErrorsPages(SearchReq dto, PageInfo pageInfo) {

        return mapper.selectBillingDocumentErrors(dto, pageInfo);
    }

    @Transactional
    public List<SearchErrosRes> getBillingDocumentErrorsExcels(SearchReq dto) {

        return mapper.selectBillingDocumentErrors(dto);
    }

    @Transactional
    public int saveBillingDocumentErrors(List<SaveErrosReq> dtos) throws Exception {
        int processCount = 0;
        String errorChk = "";
        for (SaveErrosReq saveReq : dtos) {
            WwdbGiroDepositErrorSaveDvo dvo = convert.mapSaveGiroDepositErrorSaveDvo(saveReq);

            switch (saveReq.rowState()) {
                case CommConst.ROW_STATE_UPDATED -> {
                    String cntr = dvo.getCntr();
                    dvo.setCntrNo(cntr.substring(0, 12));
                    dvo.setCntrSn(cntr.substring(12));

                    WwdbGiroDepositSaveInfoDvo selectDtlStateChk = new WwdbGiroDepositSaveInfoDvo();
                    selectDtlStateChk.setCntrNo(dvo.getCntrNo());
                    selectDtlStateChk.setCntrSn(dvo.getCntrSn());

                    SearchDtlStateRes selectDtlState = mapper.selectDtlState(selectDtlStateChk);

                    if (Objects.isNull(selectDtlState)) { //자료가없으면 주문 x
                        errorChk = "1";
                    } else if (!selectDtlState.cntrDtlStatCd().equals("101")) { //정상처리가 아니면 2
                        errorChk = "2";
                    } else {
                        errorChk = "3";
                    }

                    dvo.setProcsErrTpCd(errorChk);

                    processCount += mapper.updateBillingDocumentErrors(dvo);
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }

        return processCount;
    }

    @Transactional
    public SearchLedgerItemizationRes getBillingDocumentMgtLedgerItemization(List<SearchLedgerItemizationReq> dtos) {

        List<String> fntDts = dtos.stream().distinct()
            .filter(dto -> "22".equals(dto.giroDpMtrDvCd())).map(dto -> dto.fntDt()).collect(Collectors.toList());

        SearchLedgerItemizationRes itemizationRes = mapper
            .selectBillingDocumentMgtLedgerItemization(fntDts);

        return itemizationRes;
    }
}
