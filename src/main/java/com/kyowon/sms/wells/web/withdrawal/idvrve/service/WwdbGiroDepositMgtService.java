package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.*;
import java.util.stream.Collectors;

import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalDepositCprDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalReceiveAskDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalReceiveDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.service.ZwdzWithdrawalService;
import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaBusinessAnticipationAmtDvo;
import com.kyowon.sms.wells.web.closing.payment.service.WdcaBusinessAnticipationAmtService;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
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
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class WwdbGiroDepositMgtService {

    private final WwdbGiroDepositMgtMapper mapper;
    private final WwdbGiroDepositMgtConverter convert;

    private final ZwdzWithdrawalService zwdzWithdrawalService;

    private final WdcaBusinessAnticipationAmtService wdcaBusinessAnticipationAmtService;

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
            //            List<SaveReq> duplicates = dtos.stream().distinct().collect(Collectors.toList());

            //            String[] fntDt = new String[dtos.size()];
            //
            //            for (int i = 0; i < duplicates.size(); i++) {
            //                if (duplicates.get(i).giroDpMtrDvCd().equals("22")) {
            //                    fntDt[i] = duplicates.get(i).rveDt();
            //                }
            //            }

            deleteDvo.setFntDt(dtos.get(1).fntDt());
            deleteDvo.setRveDt(dtos.get(1).rveDt());

            processCount += mapper.deleteGiroDepositItemization(deleteDvo);

            List<WwdbGiroDepositSaveInfoDvo> dvos = mapper.selectGiroDepositItemizationInfo(); //원장내역 데이터를 가공한다.

            for (WwdbGiroDepositSaveInfoDvo infoDvo : dvos) {

                SearchDtlStateRes selectDtlState = mapper.selectDtlState(infoDvo);

                if (Objects.isNull(selectDtlState)) { //자료가없으면 주문 x
                    errorChk = "1";
                } else if (selectDtlState.cntrDtlStatCd().equals("303")) { //정상처리가 아니면 2
                    errorChk = "2";
                } else if (selectDtlState.cntrDtlStatCd().equals("101")) {
                    errorChk = "3"; //정상처리
                } else {
                    errorChk = "4"; //정상처리
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

        String rveCd = "70550"; //수납코드

        /*입금등록 해야하는 데이터 조회 */
        //        List<WwdbGiroDepositMgtDto.SearchDepositRes> inserList = mapper.selectGiroDepositList(dto);

        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession(); //세션정보

        int tcnt = 0; // 자료　건수
        int rcnt = 0; // 제외　건수
        int acnt = 0; // 기　처리건
        int ecnt = 0; // 오류　건수
        int wcnt = 0; // 처리　건수
        int jcnt = 0; // 묶음건수
        int dcnt = 0; // 단일건수

        long saveWamt = 0; // 처리총금액
        long workAmt1 = 0;
        long saveQamt = 0;
        long saveSamt = 0;

        //오늘 날짜
        String sysDate = DateUtil.getNowString();
        String sysDateYmd = DateUtil.getNowDayString();

        //입금등록 할 데이터 조회
        SearchReq giroDto = new SearchReq(dto.rveDt(), dto.fntDt(), null, null, null, null);
        List<SearchRes> insertList = mapper.selectGiroDepositMgt(giroDto);

        //영업선수금 DVO
        List<WdcaBusinessAnticipationAmtDvo> wdcaBusinessAnticipationAmtDvos = new ArrayList<WdcaBusinessAnticipationAmtDvo>();

        /*입금등록 해야하는 데이터 조회 후 생성*/
        for (SearchRes list : insertList) {

            tcnt += 1; //자료건수

            //제외 건수 만약 R(Reject분) 거나 A(A장표)
            if ("R".equals(list.giroRveDvCd()) ||
                "A".equals(list.giroRveDvCd())) {
                rcnt = rcnt + 1; //제외 건수
                continue;
            }

            //기 처리건
            if ("Y".equals(list.itgDpProcsYn())) {
                acnt = acnt + 1;
                continue;
            }

            //지료OCR묶음 대표조회
            Map<String, Object> regMap = new HashMap<String, Object>();
            regMap.put("giroOcrBndlYm", fntDt.substring(0, 6)); //발행년월
            regMap.put("dgCntrNo", list.cntrNo()); //대표계약번호
            regMap.put("dgCntrSn", list.cntrSn()); //대표일련번호
            regMap.put("fntDt", dto.fntDt()); //실적일자
            regMap.put("rveDt", dto.rveDt()); //수납일자

            Map<String, Object> errorRegMap = new HashMap<String, Object>(); //오류건 처리 데이터 입력

            int selectGiroDepositCount = mapper.selectGiroDepositCount(regMap); //지로OCR묶음기본 대표고객 조회

            // 일반건과 통합건 구분 처리 만약 대표고객 번호가 0이면 일반건
            if (selectGiroDepositCount == 0) {
                regMap = new HashMap<String, Object>();
                regMap.put("cntrNo", list.cntrNo());
                regMap.put("cntrSn", list.cntrSn());

                WwdbGiroDepositMgtDto.SearchContractDetailRes selectContractDetail = mapper
                    .selectContractDetail(regMap); //렌탈고객 조회

                // 만약 렌탈고객 조회 결과가 없을 경우
                if (ObjectUtils.isEmpty(selectContractDetail)) {
                    ecnt += 1; //오류건수 증가

                    errorRegMap = new HashMap<String, Object>();

                    errorRegMap.put("procsErrTpCd", "1"); //PROCS_ERR_TP_CD (주문정보없음(1))
                    errorRegMap.put("giroNo", list.giroNo()); //GIRO_NO 지로번호
                    errorRegMap.put("fntDt", dto.fntDt()); //FNT_DT 실적일자
                    errorRegMap.put("rveDt", dto.rveDt()); //RVE_DT 수납일자
                    errorRegMap.put("giroDpMtrDvCd", list.giroDpMtrDvCd()); //GIRO_DP_MTR_DV_CD 구분코드
                    errorRegMap.put("giroDpSn", list.giroDpSn()); //GIRO_DP_SN 일련번호

                    processCount += mapper.updateGiroDepositItemization(errorRegMap);//PROCS_ERR_TP_CD (통합발행-오류포함(1))

                } else if (StringUtil.isNotEmpty(selectContractDetail.cntrCanDtm())
                    || "303".equals(selectContractDetail.cntrDtlStatCd())) { //만약 취소일자가 있으면 오류
                    ecnt += 1; //오류건수 증가

                    errorRegMap = new HashMap<String, Object>();

                    errorRegMap.put("procsErrTpCd", "2"); //PROCS_ERR_TP_CD (주문취소(2))
                    errorRegMap.put("giroNo", list.giroNo()); //GIRO_NO 지로번호
                    errorRegMap.put("fntDt", dto.fntDt()); //FNT_DT 실적일자
                    errorRegMap.put("rveDt", dto.rveDt()); //RVE_DT 수납일자
                    errorRegMap.put("giroDpMtrDvCd", list.giroDpMtrDvCd()); //GIRO_DP_MTR_DV_CD 구분코드
                    errorRegMap.put("giroDpSn", list.giroDpSn()); //GIRO_DP_SN 일련번호

                    processCount += mapper.updateGiroDepositItemization(errorRegMap);//PROCS_ERR_TP_CD (주문취소)
                } else if (!"101".equals(selectContractDetail.cntrDtlStatCd())) {
                    ecnt += 1; //오류건수 증가

                    errorRegMap = new HashMap<String, Object>();

                    errorRegMap.put("procsErrTpCd", "4"); //PROCS_ERR_TP_CD (오류(2))
                    errorRegMap.put("giroNo", list.giroNo()); //GIRO_NO 지로번호
                    errorRegMap.put("fntDt", dto.fntDt()); //FNT_DT 실적일자
                    errorRegMap.put("rveDt", dto.rveDt()); //RVE_DT 수납일자
                    errorRegMap.put("giroDpMtrDvCd", list.giroDpMtrDvCd()); //GIRO_DP_MTR_DV_CD 구분코드
                    errorRegMap.put("giroDpSn", list.giroDpSn()); //GIRO_DP_SN 일련번호

                    processCount += mapper.updateGiroDepositItemization(errorRegMap);//PROCS_ERR_TP_CD (오류)

                } else {
                    //수납시작
                    Map<String, Object> insertMap = new HashMap<String, Object>();
                    insertMap.put("ogTpCd", session.getOgTpCd());
                    insertMap.put("prtnrNo", session.getEmployeeIDNumber());
                    insertMap.put("ogId", session.getOgId());
                    insertMap.put(
                        "dpAmt", Integer.toString(Integer.parseInt(list.rveAmt()) + Integer.parseInt(list.giroFee()))
                    );
                    insertMap.put("itgDpNo", list.itgDpNo());
                    insertMap.put("cstNo", selectContractDetail.cntrCstNo());

                    /*통합입금기본 데이터 생성 */
                    processCount += mapper.inertIntegrationItemization(insertMap); //통합입금기본 INSERT

                    //수납요청기본 데이터 셋팅
                    ZwdzWithdrawalReceiveAskDvo askDvo = new ZwdzWithdrawalReceiveAskDvo();

                    askDvo.setKyowonGroupCompanyCd(session.getCompanyCode()); // 교원그룹회사코드
                    askDvo.setCustomNumber(selectContractDetail.cntrCstNo()); // 고객번호
                    askDvo.setRveAkMthdCd("01"); // 수납요청방식코드 대면
                    askDvo.setRveAkPhCd("18"); //수납요청경로코드(일단 영업부)
                    askDvo.setRvePrtnrOgTpCd(session.getOgTpCd()); // 수납요청파트너조직유형코드
                    askDvo.setRvePrtnrNo(session.getEmployeeIDNumber()); // 수납요청파트너번호
                    askDvo.setReceiveAskAmount(
                        Integer.toString(Integer.parseInt(list.rveAmt()) + Integer.parseInt(list.giroFee()))
                    ); // 수납요청금액
                    askDvo.setReceiveAskDate(sysDateYmd); // 수납요청일자
                    askDvo.setReceiveAskStatusCode("03"); //수납요청상태코드
                    askDvo.setReceiveCompanyCode(session.getCompanyCode()); // 수납회사코드

                    /*수납요청기본 데이터 생성*/
                    String receiveAskNumber = zwdzWithdrawalService.createReceiveAskBase(askDvo);
                    askDvo.setReceiveAskNumber(receiveAskNumber);

                    //수납요청상세 데이터 셋팅
                    /*수납요청상세 데이터 입력*/
                    askDvo.setDepositDivideCode("1");//입금구분코드(입금)
                    askDvo.setDepositMeansCode("04");//입금수단코드(지로)
                    askDvo.setDepositTypeCode("0401");//입금유형코드()
                    askDvo.setReceiveDivideCode("03");//수납구분코드
                    //                        zwdzWithdrawalReceiveAskDvo.set//수납업무구분코드
                    askDvo.setOrganizationId(session.getOgId());//조직ID
                    askDvo.setOrganizationTypeCode(session.getOgTpCd());//조직유형코드
                    askDvo.setPartnerNumber(session.getEmployeeIDNumber());//파트너번호
                    askDvo.setContractNumber(list.cntrNo());//계약번호
                    askDvo.setContractSerialNumber(list.cntrSn()); //계약일련번호
                    askDvo.setProductCode(selectContractDetail.basePdCd());//상품코드
                    askDvo.setReceiveAskAmount(
                        Integer.toString(Integer.parseInt(list.rveAmt()) + Integer.parseInt(list.giroFee()))
                    );//수납요청금액
                    askDvo.setReceiveAmount(
                        Integer.toString(Integer.parseInt(list.rveAmt()) + Integer.parseInt(list.giroFee()))
                    );//수납금액
                    askDvo.setReceiveStatusCode("02"); //수납상태코드 수납완료(02)
                    askDvo.setIncmdcYn("N"); //소득공제여부

                    // 수납요청상세 데이터 생성
                    processCount += zwdzWithdrawalService.createReceiveAskDetail(askDvo);

                    //수납요청상세 이력 생성
                    processCount += zwdzWithdrawalService.createReceiveAskDetailHistory(askDvo);

                    /*수납기본 데이터 입력*/
                    ZwdzWithdrawalReceiveDvo zwdzWithdrawalReceiveDvo = new ZwdzWithdrawalReceiveDvo();
                    zwdzWithdrawalReceiveDvo.setKwGrpCoCd(session.getCompanyCode()); //        KW_GRP_CO_CD	교원그룹회사코드
                    zwdzWithdrawalReceiveDvo.setCstNo(selectContractDetail.cntrCstNo()); //        CST_NO	고객번호
                    zwdzWithdrawalReceiveDvo.setRveAkNo(receiveAskNumber); //        RVE_AK_NO	수납요청번호
                    zwdzWithdrawalReceiveDvo.setRvePhCd("18"); //        RVE_PH_CD	수납경로코드
                    zwdzWithdrawalReceiveDvo.setRveDt(sysDateYmd); //        RVE_DT	수납일자
                    zwdzWithdrawalReceiveDvo.setRveAmt(
                        Integer.toString(Integer.parseInt(list.rveAmt()) + Integer.parseInt(list.giroFee()))
                    ); //        RVE_AMT	수납금액

                    //수납기본 데이터 생성 (수납번호 리턴)
                    String rveNo = zwdzWithdrawalService.createReceive(zwdzWithdrawalReceiveDvo);
                    zwdzWithdrawalReceiveDvo.setRveNo(rveNo);

                    /*입금대사 인설트 데이터 생성*/
                    ZwdzWithdrawalDepositCprDvo depositCprDvo = new ZwdzWithdrawalDepositCprDvo();

                    depositCprDvo.setKwGrpCoCd(session.getCompanyCode()); /*교원그룹회사코드*/ // KW_GRP_CO_CD	교원그룹회사코드
                    depositCprDvo.setRveCoCd(session.getCompanyCode()); /*수납회사코드*/ // RVE_CO_CD	수납회사코드
                    depositCprDvo.setRveCd(rveCd); /*수납코드*/ // RVE_CD	수납코드
                    depositCprDvo.setProcsDvCd("1"); /*처리구분코드*/ // PROCS_DV_CD	처리구분코드(1 정상)
                    depositCprDvo.setDpDvCd(askDvo.getDepositDivideCode()); /*입금구분코드*/ // DP_DV_CD	입금구분코드(1 입금)
                    depositCprDvo.setDpMesCd(askDvo.getDepositMeansCode()); /*입금수단코드*/ // DP_MES_CD	입금수단코드
                    depositCprDvo.setDpTpCd(askDvo.getDepositTypeCode()); /*입금유형코드*/ // DP_TP_CD	입금유형코드
                    depositCprDvo.setRveDvCd(askDvo.getReceiveDivideCode()); /*수납구분코드*/ // RVE_DV_CD	수납구분코드 (03 월납입액)
                    depositCprDvo.setDpCprcnfBizCd("03"); /*입금대사업무코드*/ // DP_CPRCNF_BIZ_CD	입금대사업무코드(03 입금등록)
                    //            depositCprDvo.setDpCprcnfPdClsfCd(); /*입금대사상품분류코드*/                               // DP_CPRCNF_PD_CLSF_CD	입금대사상품분류코드
                    //            depositCprDvo.setDpCprcnfPdClsfId(); /*입금대사상품분류id*/                               // DP_CPRCNF_PD_CLSF_ID	입금대사상품분류ID
                    //            depositCprDvo.setDpCprcnfSellTpCd(); /*입금대사판매유형코드*/                               // DP_CPRCNF_SELL_TP_CD	입금대사판매유형코드
                    depositCprDvo.setDpCprcnfDtm(sysDate); /*입금대사일시*/ // DP_CPRCNF_DTM	입금대사일시
                    depositCprDvo.setDpCprcnfPerfDt(dto.fntDt()); /*입금대사실적일자*/ // DP_CPRCNF_PERF_DT	입금대사실적일자
                    depositCprDvo.setDpCprcnfCanYn("N"); /*입금대사취소여부*/ // DP_CPRCNF_CAN_YN	입금대사취소여부
                    depositCprDvo.setDpCprcnfCnfmYn("Y"); /*입금대사확정여부*/ // DP_CPRCNF_CNFM_YN	입금대사확정여부
                    depositCprDvo.setDpCprcnfCnfmDtm(sysDate); /*입금대사확정일시*/ // DP_CPRCNF_CNFM_DTM	입금대사확정일시
                    depositCprDvo.setDpCprcnfAmt(
                        Integer.toString(Integer.parseInt(list.rveAmt()) + Integer.parseInt(list.giroFee()))
                    ); /*입금대사금액*/ // DP_CPRCNF_AMT	입금대사금액 ??
                    depositCprDvo.setDpCprcnfEtcFeeAmt(list.giroFee()); /*입금대사기타수수료금액*/ // DP_CPRCNF_ETC_FEE_AMT	입금대사기타수수료금액 ??
                    depositCprDvo.setDpCprcnfProcsAmt(
                        Integer.toString(Integer.parseInt(list.rveAmt()) + Integer.parseInt(list.giroFee()))
                    ); /*입금대사처리금액*/ // DP_CPRCNF_PROCS_AMT	입금대사처리금액 ??
                    depositCprDvo.setDpCprcnfDstApyYn("N"); /*입금대사배분적용여부*/ // DP_CPRCNF_DST_APY_YN	입금대사배분적용여부 ??
                    depositCprDvo.setItgDpNo(list.itgDpNo()); /*통합입금번호*/ // ITG_DP_NO	통합입금번호
                    depositCprDvo.setCntrNo(list.cntrNo()); /*계약번호*/ // CNTR_NO	계약번호
                    depositCprDvo.setCntrSn(list.cntrSn()); /*계약일련번호*/ // CNTR_SN	계약일련번호
                    depositCprDvo.setPdCd(selectContractDetail.basePdCd()); /*상품코드*/ // PD_CD	상품코드
                    depositCprDvo.setIncmdcYn("N"); /*소득공제여부*/ // INCMDC_YN	소득공제여부
                    // RVE_BIZ_DV_CD	수납업무구분코드

                    //입금대사 데이터 생성(리턴 입금대사번호)
                    String depositComparisonPk = zwdzWithdrawalService.createDepositComparison(depositCprDvo);

                    /*수납상세 인설트 데이터 입력*/
                    zwdzWithdrawalReceiveDvo.setKwGrpCoCd(session.getCompanyCode());//            KW_GRP_CO_CD	교원그룹회사코드
                    //            RVE_NO	수납번호
                    zwdzWithdrawalReceiveDvo.setRveSn(Integer.toString(1));//                        RVE_SN	수납일련번호
                    zwdzWithdrawalReceiveDvo.setDpDvCd(askDvo.getDepositDivideCode());//            DP_DV_CD	입금구분코드(1 입금)
                    zwdzWithdrawalReceiveDvo.setDpMesCd(askDvo.getDepositMeansCode());//            DP_MES_CD	입금수단코드()
                    zwdzWithdrawalReceiveDvo.setDpTpCd(askDvo.getDepositTypeCode());//            DP_TP_CD	입금유형코드()
                    zwdzWithdrawalReceiveDvo.setRveDvCd(askDvo.getReceiveDivideCode());//            RVE_DV_CD	수납구분코드
                    //            RVE_BIZ_DV_CD	수납업무구분코드
                    zwdzWithdrawalReceiveDvo.setRveCd(rveCd);//            RVE_CD	수납코드
                    zwdzWithdrawalReceiveDvo.setOgTpCd(session.getOgTpCd());//            OG_TP_CD	조직유형코드
                    zwdzWithdrawalReceiveDvo.setPrtnrNo(session.getEmployeeIDNumber());//            PRTNR_NO	파트너번호
                    zwdzWithdrawalReceiveDvo.setProcsDvCd("1");//            PROCS_DV_CD	처리구분코드(1 정상)
                    zwdzWithdrawalReceiveDvo.setDpDt(sysDateYmd);//            DP_DT	입금일자
                    zwdzWithdrawalReceiveDvo
                        .setDpAmt(Integer.toString(Integer.parseInt(list.rveAmt()) + Integer.parseInt(list.giroFee())));//            DP_AMT	입금금액
                    zwdzWithdrawalReceiveDvo.setAlncFee(list.giroFee());//            ALNC_FEE	제휴수수료
                    zwdzWithdrawalReceiveDvo.setRveDt(rveDt);//            RVE_DT	수납일자
                    zwdzWithdrawalReceiveDvo.setRveAmt(
                        Integer.toString(Integer.parseInt(list.rveAmt()) + Integer.parseInt(list.giroFee()))
                    );//            RVE_AMT	수납금액
                    zwdzWithdrawalReceiveDvo.setRveProcsYn("Y");//            RVE_PROCS_YN	수납처리여부
                    zwdzWithdrawalReceiveDvo.setPerfDt(fntDt);//            PERF_DT	실적일자
                    zwdzWithdrawalReceiveDvo.setRveAkNo(receiveAskNumber);//            RVE_AK_NO	수납요청번호
                    zwdzWithdrawalReceiveDvo.setRveAkSn(Integer.toString(1));//            RVE_AK_SN	수납요청일련번호
                    zwdzWithdrawalReceiveDvo.setItgDpNo(list.itgDpNo());//            ITG_DP_NO	통합입금번호
                    zwdzWithdrawalReceiveDvo.setDpCprcnfNo(depositComparisonPk);//            DP_CPRCNF_NO	입금대사번호
                    zwdzWithdrawalReceiveDvo.setCntrNo(list.cntrNo());//            CNTR_NO	계약번호
                    zwdzWithdrawalReceiveDvo.setCntrSn(list.cntrSn());//            CNTR_SN	계약일련번호
                    zwdzWithdrawalReceiveDvo.setPdCd(selectContractDetail.basePdCd());//            PD_CD	상품코드
                    zwdzWithdrawalReceiveDvo.setIncmdcYn("N");//            INCMDC_YN	소득공제여부
                    zwdzWithdrawalReceiveDvo.setRveCoCd(session.getCompanyCode());//            RVE_CO_CD	수납회사코드

                    //수납상세 데이터 생성
                    processCount += zwdzWithdrawalService.createReceiveDetail(zwdzWithdrawalReceiveDvo);

                    //지로입금내역 처리
                    processCount += mapper.updateIntegrationItemization(list.itgDpNo());

                    //영업선수금
                    WdcaBusinessAnticipationAmtDvo edcaBusinessAnticipationAmtDvo = new WdcaBusinessAnticipationAmtDvo();
                    // 영업선수금 데이터 생성
                    edcaBusinessAnticipationAmtDvo.setInputGubun("1"); //입력구분-입출금
                    edcaBusinessAnticipationAmtDvo.setRveNo(zwdzWithdrawalReceiveDvo.getRveNo()); //수납번호
                    edcaBusinessAnticipationAmtDvo.setRveSn(Integer.parseInt(zwdzWithdrawalReceiveDvo.getRveSn())); //수납일련번호
                    edcaBusinessAnticipationAmtDvo.setDpClDt(sysDateYmd); //입금마감일자
                    edcaBusinessAnticipationAmtDvo.setCntrNo(zwdzWithdrawalReceiveDvo.getCntrNo()); //계약번호
                    edcaBusinessAnticipationAmtDvo.setCntrSn(Integer.parseInt(zwdzWithdrawalReceiveDvo.getCntrSn())); //계약일련번호
                    edcaBusinessAnticipationAmtDvo.setKwGrpCoCd(zwdzWithdrawalReceiveDvo.getKwGrpCoCd()); //교원그룹회사코드
                    edcaBusinessAnticipationAmtDvo.setRveCd(zwdzWithdrawalReceiveDvo.getRveCd()); //수납코드
                    edcaBusinessAnticipationAmtDvo.setOgTpCd(zwdzWithdrawalReceiveDvo.getOgTpCd()); //조직유형코드
                    edcaBusinessAnticipationAmtDvo.setIchrPrtnrNo(zwdzWithdrawalReceiveDvo.getPrtnrNo()); //담당파트너번호
                    edcaBusinessAnticipationAmtDvo.setRveAmt(Integer.parseInt(zwdzWithdrawalReceiveDvo.getRveAmt())); //수납금액
                    edcaBusinessAnticipationAmtDvo.setCstNo(zwdzWithdrawalReceiveDvo.getCstNo()); //고객번호
                    edcaBusinessAnticipationAmtDvo.setDpCprcnfNo(depositComparisonPk); //입금대사번호
                    edcaBusinessAnticipationAmtDvo.setPdCd(zwdzWithdrawalReceiveDvo.getPdCd()); //상품코드
                    edcaBusinessAnticipationAmtDvo.setPdHclsfId(selectContractDetail.pdHclsfId()); //상품대분류ID
                    edcaBusinessAnticipationAmtDvo.setPdMclsfId(selectContractDetail.pdMclsfId()); //상품중분류ID
                    edcaBusinessAnticipationAmtDvo.setPdLclsfId(selectContractDetail.pdLclsfId()); //상품소분류ID
                    edcaBusinessAnticipationAmtDvo.setRveDt(zwdzWithdrawalReceiveDvo.getRveDt()); //수납일자
                    edcaBusinessAnticipationAmtDvo.setPerfDt(zwdzWithdrawalReceiveDvo.getPerfDt()); //실적일자
                    //            zwdbBusinessAnticipationDvo.setBilTn(); //청구회차
                    edcaBusinessAnticipationAmtDvo.setProcsDvCd(zwdzWithdrawalReceiveDvo.getProcsDvCd()); //처리구분코드
                    edcaBusinessAnticipationAmtDvo.setDpMesCd(zwdzWithdrawalReceiveDvo.getDpMesCd()); //입금수단코드
                    edcaBusinessAnticipationAmtDvo.setDpTpCd(zwdzWithdrawalReceiveDvo.getDpTpCd()); //입금유형코드
                    edcaBusinessAnticipationAmtDvo.setRveDvCd(zwdzWithdrawalReceiveDvo.getRveDvCd()); //수납구분코드
                    edcaBusinessAnticipationAmtDvo.setRvePhCd(zwdzWithdrawalReceiveDvo.getRvePhCd()); //수납경로코드
                    //            zwdbBusinessAnticipationDvo.setRveplcDvCd(zwdzWithdrawalReceiveDvo.); //수납처구분코드

                    edcaBusinessAnticipationAmtDvo.setDpDvCd(zwdzWithdrawalReceiveDvo.getDpDvCd()); //입금구분코드
                    edcaBusinessAnticipationAmtDvo.setSellTpCd(selectContractDetail.sellTpCd()); //판매유형코드
                    edcaBusinessAnticipationAmtDvo.setSellTpDtlCd(selectContractDetail.sellTpDtlCd()); //판매유형상세코드
                    edcaBusinessAnticipationAmtDvo.setEtcAtamNo(""); //기타선수금

                    wdcaBusinessAnticipationAmtDvos.add(edcaBusinessAnticipationAmtDvo);

                    //통합입금기본 데이터 수정
                    insertMap = new HashMap<String, Object>();
                    insertMap.put("rveAkNo", receiveAskNumber);
                    insertMap.put("itgDpNo", list.itgDpNo());
                    insertMap.put("dpAmt", list.rveAmt());

                    processCount += mapper.updateIntegrationDeposit(insertMap);

                }

            }
            //            else { //통합 건 데이터 입력
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
            //                    errorRegMap = new HashMap<String, Object>(); //오류건 처리 데이터 입력
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
            //                    regMap = new HashMap<String, Object>();
            //                    regMap.put("giroOcrBndlYm", fntDt.substring(0, 6));
            //                    regMap.put("dgCntrNo", list.cntrNo());
            //                    regMap.put("dgCntrSn", list.cntrSn());
            //                    List<WwdbGiroDepositMgtDto.SearchDepositSettingRes> selectGiroDepositSettings = mapper
            //                        .selectGiroDepositSettingList(regMap); //지로 통합발행 목록 조회
            //
            //                    for (WwdbGiroDepositMgtDto.SearchDepositSettingRes settingRes : selectGiroDepositSettings) {
            //
            //                        long dpseAmt = Long.parseLong(settingRes.dpseAmt()); //설정금액
            //
            //                        if (dpseAmt > payAmount) { //만약 설정금액이 납입금액보다 크면
            //                            workAmt1 = payAmount; //납입금액
            //                            saveQamt = 0;
            //                        } else {
            //                            workAmt1 = dpseAmt; //설정금액
            //                            saveQamt = payAmount - dpseAmt; //납입금액 + 설정금액
            //                        }
            //                        saveSamt = saveSamt + workAmt1;
            //
            //                        regMap = new HashMap<String, Object>();
            //                        regMap.put("cntrNo", settingRes.cntrNo()); //계약번호
            //                        regMap.put("cntrSn", settingRes.cntrSn()); //계약일련번호
            //
            //                        /*여기서부터는 대사 처리시작*/
            //
            //                    }
            //                }
            //
            //            }

        }

        //
        //        giroDto = new SearchReq(dto.rveDt(), dto.fntDt(), null, "2", null, null);
        //        List<SearchRes> giroDeposits = mapper.selectGiroDepositMgt(giroDto);
        //
        //        /*통합입금기본 데이터 생성*/
        //        dvo.setOgTpCd(session.getOgTpCd());/*조직유형코드*/
        //        dvo.setPrtnrNo(session.getEmployeeIDNumber());/*파트너번호*/
        //        dvo.setOgId(session.getOgId());/*조직ID*/
        //        //        dvo.setDpAmt(); /*입금금액*/
        //        //        dvo.setDpCprcnfAmt();/*입금대사금액*/
        //        /*통합입금기본 데이터 생성 */
        //        processCount += mapper.inertIntegrationItemization(dvo); //통합입금기본 INSERT
        //
        //        processCount += mapper.updateIntegrationItemization(dvo);

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

        if (wdcaBusinessAnticipationAmtDvos.size() > 0) {
            //영업선수금 데이터 생성
            processCount += wdcaBusinessAnticipationAmtService
                .createBusinessAnticipationAmt(wdcaBusinessAnticipationAmtDvos);
        }

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
