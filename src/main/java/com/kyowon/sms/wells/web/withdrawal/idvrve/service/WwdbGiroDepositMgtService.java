package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbGiroDepositMgtConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SaveErrosReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchDtlStateRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchErrosRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchRes;
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
    public List<SearchRes> getBillingDocumentMgtExcels(SearchReq dto) {

        return mapper.selectGiroDepositMgt(dto);
    }

    @Transactional
    public int saveBillingDocumentMgt(List<SaveReq> dtos) throws Exception {
        int processCount = 0;
        processCount += mapper.deleteGiroDeposit();
        log.info("service1");
        log.info(dtos.toString());
        log.info("service1");
        String errorChk = ""; //에러체크

        if (dtos.size() > 0) {
            for (SaveReq dto : dtos) {
                WwdbGiroDepositSaveDvo dvo = convert.mapSaveWwwdbGiroDepositSaveDvo(dto);
                processCount += mapper.inertGiroDeposit(dvo);
                //                processCount += mapper.inertGiroDeposit(dto);
            }
            List<WwdbGiroDepositSaveInfoDvo> dvos = mapper.selectGiroDepositItemizationInfo();
            for (WwdbGiroDepositSaveInfoDvo infoDvo : dvos) {

                SearchDtlStateRes selectDtlState = mapper.selectDtlState(infoDvo);

                if (Objects.isNull(selectDtlState)) { //자료가없으면 주문 x
                    errorChk = "1";
                } else if (!selectDtlState.cntrDtlStatCd().equals("101")) { //정상처리가 아니면 2
                    errorChk = "2";
                } else {
                    errorChk = "3";
                }
                infoDvo.setProcsErrTpCd(errorChk);

                processCount += mapper.inertGiroDepositItemization(infoDvo);
                processCount += mapper.inertIntegrationItemization(infoDvo);

                //                processCount += mapper.inertIntegrationItemizationHistory(infoDvo);
            }
        }
        return processCount;
    }

    @Transactional
    public int saveBillingCreateDocument(SearchReq dto) throws Exception {
        //        
        //        int tcnt = 0; // 자료　건수
        //        int rcnt = 0; // 제외　건수
        //        int acnt = 0; // 기　처리건
        //        int ecnt = 0; // 오류　건수
        //        int wcnt = 0; // 처리　건수
        //        int jcnt = 0; // 묶음건수
        //        int dcnt = 0; // 단일건수
        //        long saveWamt = 0; // 처리총금액
        //        long cwtamt = 0; // 설정금액
        //        long cwqamt = 0; // 납입금액
        //        long workAmt1 = 0;
        //        long workTamt = 0;
        //        long saveQamt = 0;
        //        long saveSamt = 0;
        //
        //        Map<String, Object> regMap = new HashMap<String, Object>();
        //
        //        String rveDt = dto.rveDt(); //입금일자
        //        String fntDt = dto.fntDt(); //실적일자
        //
        //        List<SearchDepositRes> inserList = mapper.selectGiroDepositList(dto);
        //
        //        if (inserList.size() > 0) {
        //            for (int i = 0; i < inserList.size(); i++) {
        //                tcnt += 1; //자료 건수
        //
        //                if (inserList.get(i).giroRveDvCd().equals("R") ||
        //                    inserList.get(i).giroRveDvCd().equals("A")) {
        //                    rcnt = rcnt + 1; //제외 건수
        //                    continue;
        //                }
        //
        //                regMap = new HashMap<String, Object>();
        //                regMap.put("giroOcrBndlYm", fntDt.substring(0, 6));
        //                regMap.put("dgCntrNo", inserList.get(i).cntrNo());
        //                regMap.put("dgCntrSn", inserList.get(i).cntrSn());
        //                //                SearchDepositCountReq searchDepositCountReq = new SearchDepositCountReq(
        //                //                    fntDt.substring(0, 6), inserList.get(i).cntrNo(), inserList.get(i).cntrSn()
        //                //                );
        //                int selectGiroDepositCount = mapper.selectGiroDepositCount(regMap); //지로OCR묶음기본 대표고객 조회
        //
        //                if (selectGiroDepositCount == 0) { // 일반건과　통합건　구분　처리
        //
        //                    //일반　지로　입금　처리--------------------------------
        //                    regMap = new HashMap<String, Object>();
        //                    regMap.put("cntrNo", inserList.get(i).cntrNo());
        //                    regMap.put("cntrSn", inserList.get(i).cntrSn());
        //
        //                    SearchContractDetailRes selectContractDetail = mapper.selectContractDetail(regMap); //렌탈고객 조회
        //
        //                    //                    if (StringUtil.isNull(selectContractDetail.toString())) { //이건 확인해보자 업데이트
        //                    if (Objects.isNull((selectContractDetail))) { //이건 확인해보자 업데이트
        //                        ecnt += 1;
        //                        regMap.put("procsErrTpCd", "1"); //1번 주문자료 없음
        //                        regMap.put("giroNo", inserList.get(i).giroNo());
        //                        regMap.put("fntDt", inserList.get(i).fntDt());
        //                        regMap.put("giroDpMtrDvCd", inserList.get(i).giroDpMtrDvCd());
        //                        regMap.put("giroDpSn", inserList.get(i).giroDpSn());
        //                        mapper.updateGiroDepositItemization(regMap); //파라미터 넘겨야함
        //                        //                        regMap.put("CWFLG1",  "1");  // 주문자료　없음！
        //                        //                        procCnt = dpstMapper.updateGiroDpstDat(regMap); // 지로입금 자료 수정 CW5520P
        //                        //                        if(procCnt != 1){
        //                        //                            throw new BusinessException("지로입금 자료 수정 CW5520P 오류!");
        //                        //                        }
        //                    } else if (!selectContractDetail.cntrDtlStatCd().equals("101")) { //계약상세상태코드 정상이 아니면 취소건이있다는듯 이거는 물어보자 CNTR_DTL_STAT_CD
        //                        ecnt += 1;
        //
        //                        regMap.put("procsErrTpCd", "2"); //2번 취소된 고객
        //                        regMap.put("giroNo", inserList.get(i).giroNo());
        //                        regMap.put("fntDt", inserList.get(i).fntDt());
        //                        regMap.put("giroDpMtrDvCd", inserList.get(i).giroDpMtrDvCd());
        //                        regMap.put("giroDpSn", inserList.get(i).giroDpSn());
        //
        //                        mapper.updateGiroDepositItemization(regMap); //이거 파라미터 넘겨야함
        //
        //                        //                        
        //                        //                        regMap.put("CWFLG1",  "2");  // 취소된　고객코드
        //                        //                        procCnt = dpstMapper.updateGiroDpstDat(regMap); // 지로입금 자료 수정 CW5520P
        //                        //                        if(procCnt != 1){
        //                        //                            throw new BusinessException("지로입금 자료 수정 CW5520P 오류!");
        //                        //                        }
        //                    } else {
        //                        regMap = new HashMap<String, Object>(); //일단 더 짜야하니까 다시 확인
        //                        regMap.put("cntrNo", inserList.get(i).cntrNo());
        //                        regMap.put("cntrSn", inserList.get(i).cntrSn());
        //
        //                        //                        
        //
        //                        //                        수납상세 INSERT 문이랑 히스토리 INSERT문 짜야함
        //                    }
        //
        //                } else { // 통합발행　지로　입금　처리
        //                    regMap = new HashMap<String, Object>();
        //                    regMap.put("giroOcrBndlYm", fntDt.substring(0, 6)); // 발행년
        //                    regMap.put("dgCntrNo", inserList.get(i).cntrNo()); // 
        //                    regMap.put("dgCntrSn", inserList.get(i).cntrSn()); // 대표고객년도
        //
        //                    int settingAmount = mapper.selectGiroDepositSettingAmount(regMap); //계약상세상태코드 정상(101)인 설정금액
        //                    int pyAmt = Integer.parseInt(inserList.get(i).pyAmt()); //납입금액
        //
        //                    if (settingAmount != pyAmt) { // 설정금액  납입금액
        //                        regMap = new HashMap<String, Object>();
        //                        regMap.put("procsErrTpCd", "4"); //4번 통합발행-오류포함
        //                        regMap.put("giroNo", inserList.get(i).giroNo());
        //                        regMap.put("fntDt", inserList.get(i).fntDt());
        //                        regMap.put("giroDpMtrDvCd", inserList.get(i).giroDpMtrDvCd());
        //                        regMap.put("giroDpSn", inserList.get(i).giroDpSn());
        //                        mapper.updateGiroDepositItemization(regMap); //이거도 수정해야함
        //                        ecnt += 1; //오류 건수
        //                    } else {
        //                        regMap = new HashMap<String, Object>();
        //                        regMap.put("giroOcrBndlYm", fntDt.substring(0, 6)); // 발행년
        //                        regMap.put("dgCntrNo", inserList.get(i).cntrNo()); // 
        //                        regMap.put("dgCntrSn", inserList.get(i).cntrSn()); // 대표고객년도
        //                        List<SearchDepositSettingRes> selectGiroDepositSettingList = mapper
        //                            .selectGiroDepositSettingList(regMap);
        //
        //                        if (selectGiroDepositSettingList.size() > 0) {
        //                            for (int j = 0; j < selectGiroDepositSettingList.size(); j++) {
        //                                int dpSeAmt = Integer.parseInt(selectGiroDepositSettingList.get(j).dpSeAmt()); //설정금액
        //
        //                                if (dpSeAmt > pyAmt) { // 설정금액 > 납입금액
        //                                    workAmt1 = pyAmt;
        //                                    pyAmt = 0;
        //                                } else {
        //                                    workAmt1 = dpSeAmt;
        //                                    pyAmt = pyAmt - dpSeAmt;
        //                                }
        //                                saveSamt = saveSamt + workAmt1;
        //                                //-----------------------------------------------------------------------------------
        //
        //                                //수납상세 INSERT 문이랑 히스토리 INSERT문 짜야함
        //
        //                            }
        //
        //                        }
        //                    }
        //
        //                }
        //
        //                //                if (StringUtil.isEquals("UP", ConvertUtil.castString(insertList.get(i).get("CWFLG3")))) {
        //                //                    acnt = acnt + 1; //기　처리건
        //                //                    continue;
        //                //                }
        //
        //            }
        //
        //        }
        return 0;
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

}
