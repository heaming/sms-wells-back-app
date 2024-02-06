package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.util.StringUtils;

import com.kyowon.sms.common.web.withdrawal.idvrve.service.ZwdbDepositComparisonComfirmationService;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalReceiveAskDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.service.ZwdzWithdrawalService;
import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbGiroDepositMgtConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SaveIntegrationReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.*;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroDepositErrorSaveDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroDepositSaveDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroDepositSaveInfoDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbGiroDepositMgtMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.response.SaveResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 지로입금관리 Service
 * </pre>
 *
 * @author jaeha.yeon
 * @since 2023-10-20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WwdbGiroDepositMgtService {

    private final WwdbGiroDepositMgtMapper mapper;
    private final WwdbGiroDepositMgtConverter convert;

    private final ZwdzWithdrawalService zwdzWithdrawalService;

    private final ZwdbDepositComparisonComfirmationService depositComparisonComfirmationService;

    private final MessageResourceService msgService;

    private static final String CNTR_NO = "cntrNo";
    private static final String CNTR_SN = "cntrSn";
    private static final String PROCS_ERR_TP_CD = "procsErrTpCd";
    private static final String ITG_DP_NO = "itgDpNo";

    /**
     * 지로입금 목록조회
     * @param dto
     * @param pageInfo
     * @return PagingResult
     */
    @Transactional
    public PagingResult<SearchRes> getBillingDocumentMgtPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectGiroDepositMgt(dto, pageInfo);
    }

    /**
     * 지로입금 합계 조회
     * @param dto
     * @return
     */
    @Transactional
    public SearchSumRes getGiroDepositSum(SearchReq dto) {
        return mapper.selectGiroDepositSum(dto);
    }

    /**
     * 지로입금 목록 엑셀다운르도용
     *
     * @param dto
     * @return
     */
    @Transactional
    public List<SearchRes> getBillingDocumentMgtExcels(SearchReq dto) {
        return mapper.selectGiroDepositMgt(dto);
    }

    /**
     * 지로 입금관리 업로드
     * @param dtos
     * @return
     * @throws Exception
     */
    @Transactional
    public int saveBillingDocumentMgt(List<SaveReq> dtos, String date) {
        int processCount = 0;

        processCount += mapper.deleteGiroDepositItemization(date); // 지로입금내역 삭제
        processCount += mapper.deleteGiroDepositItemLedgization(date); // 지로입금원장내역 삭제

        String errorChk; //에러체크

        if (dtos.size() > 0) {
            String toDay = mapper.selectGiroDepositDate();

            for (SaveReq dto : dtos) {
                WwdbGiroDepositSaveDvo dvo = convert.mapSaveWwwdbGiroDepositSaveDvo(dto);
                dvo.setGiroDpUldDtm(toDay);
                processCount += mapper.inertGiroDeposit(dvo); //원장내역 저장
            }

            List<WwdbGiroDepositSaveInfoDvo> dvos = mapper.selectGiroDepositItemizationInfo(); //원장내역 데이터를 가공한다.

            for (WwdbGiroDepositSaveInfoDvo infoDvo : dvos) {
                SearchDtlStateRes selectDtlState = mapper.selectDtlState(infoDvo);

                if (Objects.isNull(selectDtlState)) { //자료가없으면 주문 x
                    errorChk = "1";
                } else if (StringUtil.isNotEmpty(selectDtlState.cntrCanDtm())) { //정상처리가 아니면 2
                    errorChk = "2";
                } else {
                    errorChk = "4"; //정상처리
                }
                infoDvo.setProcsErrTpCd(errorChk);

                processCount += mapper.inertGiroDepositItemization(infoDvo); //지로입금내역 저장
            }
            processCount += mapper.updateGiroDeposit(date);
        }
        return processCount;
    }

    /**
     * 지로 입금관리 생성
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    public SaveResponse saveBillingCreateDocument(SaveIntegrationReq dto) {
        int processCount = 0;

        int TCNT = 0; // 자료　건수
        int RCNT = 0; // 제외　건수
        int ACNT = 0; // 기　처리건
        int ECNT = 0; // 오류　건수
        int WCNT = 0; // 처리　건수
        int JCNT = 0; // 묶음건수
        int DCNT; // 단일건수
        long saveWamt = 0; // 처리총금액

        String rveCd = "70550"; //수납코드

        //오늘 날짜
        String sysDateYmd = DateUtil.getNowDayString();

        //입금등록 할 데이터 조회
        SearchReq giroDto = SearchReq.builder().fntDt(dto.fntDt()).rveDt(dto.rveDt()).build();
        List<SearchRes> insertList = mapper.selectGiroDepositMgt(giroDto);

        Map<String, Object> regMap;
        Map<String, Object> errorRegMap; //오류건 처리 데이터 입력
        List<Map<String, Object>> insertGiros = new ArrayList<>(); /*입금등록 할 데이터 리스트*/
        Map<String, Object> insertGiro;

        for (SearchRes list : insertList) {

            TCNT = TCNT + 1; //자료　건수

            /*입금등록에서 제외해야하는 건수들*/
            if ("R".equals(list.giroRveDvCd()) || "A".equals(list.giroRveDvCd())) {
                RCNT = RCNT + 1; //제외　건수
                continue;
            }

            if ("Y".equals(list.itgDpProcsYn())) { /*이미 대사처리완료*/
                ACNT = ACNT + 1; //기　처리건
                continue;
            }

            if (StringUtil.isEmpty(list.dgCntrNo()) || StringUtil.isNull(list.dgCntrNo())) { /*지로입금 일반건*/

                regMap = new HashMap<>();

                regMap.put(CNTR_NO, list.cntrNo());
                regMap.put(CNTR_SN, list.cntrSn());

                WwdbGiroDepositMgtDto.SearchContractDetailRes selectContractDetail = mapper
                    .selectContractDetail(regMap); //렌탈고객 조회

                /*만약 조회된 결과가 없을 시 주문정보 없음*/
                if (ObjectUtils.isEmpty(selectContractDetail)) {
                    ECNT = ECNT + 1; // 오류　건수

                    errorRegMap = new HashMap<>();
                    errorRegMap.put(PROCS_ERR_TP_CD, "1"); //PROCS_ERR_TP_CD (주문정보없음(1))
                    errorRegMap.put(ITG_DP_NO, list.itgDpNo()); //통합입금번호

                    processCount += mapper.updateGiroDepositItemization(errorRegMap);//PROCS_ERR_TP_CD (통합발행-오류포함(1))

                } else if (StringUtil.isNotEmpty(selectContractDetail.cntrCanDtm())) { /*만약 취소 건일 경우*/
                    ECNT = ECNT + 1; // 오류　건수

                    errorRegMap = new HashMap<>();
                    errorRegMap.put(PROCS_ERR_TP_CD, "2"); // PROCS_ERR_TP_CD (취소주문(2))
                    errorRegMap.put(ITG_DP_NO, list.itgDpNo()); //통합입금번호

                    processCount += mapper.updateGiroDepositItemization(errorRegMap); // PROCS_ERR_TP_CD (취소주문(2))
                } else { /*정산 건 */
                    insertGiro = new HashMap<>();

                    insertGiro.put(ITG_DP_NO, list.itgDpNo());
                    insertGiro.put("cstNo", selectContractDetail.cntrCstNo());
                    insertGiro.put("rveAmt", list.rveAmt());
                    insertGiro.put(CNTR_NO, list.cntrNo());
                    insertGiro.put(CNTR_SN, list.cntrSn());
                    insertGiro.put("pdCd", list.pdCd());
                    insertGiro.put("dpDt", list.dpDt());

                    insertGiros.add(insertGiro);

                    saveWamt = saveWamt + Long.parseLong(list.rveAmt()); // 처리총금액
                    WCNT = WCNT + 1; // 처리　건수
                }
            } else { /*지로입금 통합건*/

                long rveAmt = Long.parseLong(list.pyAmt());
                long giroSeAmt = Long.parseLong(list.giroAmt());

                /*통합청구쪽 지로 설정금액이랑  렌탈금액이 다른경우*/
                if (rveAmt != giroSeAmt) {

                    ECNT = ECNT + 1; // 오류　건수
                    errorRegMap = new HashMap<>();
                    errorRegMap.put(PROCS_ERR_TP_CD, "4"); //PROCS_ERR_TP_CD (통합청구 오류)
                    errorRegMap.put(ITG_DP_NO, list.itgDpNo()); //통합입금번호

                    processCount += mapper.updateGiroDepositItemization(errorRegMap);//PROCS_ERR_TP_CD (통합발행-오류포함(1))

                } else {

                    insertGiro = new HashMap<>();

                    insertGiro.put(ITG_DP_NO, list.itgDpNo());
                    insertGiro.put("cstNo", list.cstNo());
                    insertGiro.put("rveAmt", list.rveAmt());
                    insertGiro.put(CNTR_NO, list.cntrNo());
                    insertGiro.put(CNTR_SN, list.cntrSn());
                    insertGiro.put("pdCd", list.pdCd());
                    insertGiro.put("dpDt", list.dpDt());

                    insertGiros.add(insertGiro);

                    saveWamt = saveWamt + Long.parseLong(list.giroSeAmt()); // 처리총금액
                    WCNT = WCNT + 1; // 처리　건수
                    JCNT = JCNT + 1; // 묶음건수
                }

            }

        }

        if (insertGiros.size() > 0) {

            UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession(); //세션정보

            for (Map<String, Object> insertMap : insertGiros) {

                String ogTpCd = session.getOgTpCd(); /*조직유형*/
                String employeeIDNumber = session.getEmployeeIDNumber(); /*사번*/
                String ogId = session.getOgId(); /*조직ID*/
                String companyCode = session.getCompanyCode(); /*그룹회사코드*/

                String itgDpNo = insertMap.get(ITG_DP_NO).toString(); /*통합입금번호*/
                String cstNo = insertMap.get("cstNo").toString(); /*고객번호*/
                String rveAmt = insertMap.get("rveAmt").toString();/*납입금액*/
                String cntrNo = insertMap.get(CNTR_NO).toString();/*계약번호*/
                String cntrSn = insertMap.get(CNTR_SN).toString();/*계약일련번호*/
                String pdCd = insertMap.get("pdCd").toString();/*상품코드*/
                String dpDt = insertMap.get("dpDt").toString();/*실적일자*/

                insertMap.put("ogTpCd", ogTpCd);
                insertMap.put("prtnrNo", employeeIDNumber);
                insertMap.put("ogId", ogId);
                insertMap.put("dpAmt", saveWamt);
                insertMap.put(ITG_DP_NO, itgDpNo);
                insertMap.put("cstNo", cstNo);

                /*통합입금기본 데이터 생성 */
                processCount += mapper.inertIntegrationItemization(insertMap); //통합입금기본 INSERT

                /*정상건*/
                errorRegMap = new HashMap<>();
                errorRegMap.put(PROCS_ERR_TP_CD, "3"); //PROCS_ERR_TP_CD (정상(3))
                errorRegMap.put(ITG_DP_NO, itgDpNo); //통합입금번호

                processCount += mapper.updateGiroDepositItemization(errorRegMap);//PROCS_ERR_TP_CD (정상(3))

                //수납요청기본 데이터 셋팅
                ZwdzWithdrawalReceiveAskDvo askDvo = new ZwdzWithdrawalReceiveAskDvo();

                askDvo.setKyowonGroupCompanyCd(companyCode); // 교원그룹회사코드
                askDvo.setCustomNumber(cstNo); // 고객번호
                askDvo.setRveAkMthdCd("01"); // 수납요청방식코드 대면
                askDvo.setRveAkPhCd("18"); //수납요청경로코드(일단 영업부)
                askDvo.setRvePrtnrOgTpCd(ogTpCd); // 수납요청파트너조직유형코드
                askDvo.setRvePrtnrNo(employeeIDNumber); // 수납요청파트너번호
                askDvo.setReceiveAskAmount(rveAmt); // 수납요청금액
                askDvo.setReceiveAskDate(sysDateYmd); // 수납요청일자
                askDvo.setReceiveAskStatusCode("01"); //수납요청상태코드
                askDvo.setReceiveCompanyCode(companyCode); // 수납회사코드

                /*수납요청기본 데이터 생성*/
                String receiveAskNumber = zwdzWithdrawalService.createReceiveAskBase(askDvo);
                askDvo.setReceiveAskNumber(receiveAskNumber);

                //수납요청상세 데이터 셋팅
                /*수납요청상세 데이터 입력*/
                askDvo.setDepositDivideCode("1");//입금구분코드(입금)
                askDvo.setDepositMeansCode("04");//입금수단코드(지로)
                askDvo.setDepositTypeCode("0401");//입금유형코드()
                askDvo.setReceiveDivideCode("03");//수납구분코드
                askDvo.setOrganizationId(ogId);//조직ID
                askDvo.setOrganizationTypeCode(ogTpCd);//조직유형코드
                askDvo.setPartnerNumber(employeeIDNumber);//파트너번호
                askDvo.setContractNumber(cntrNo);//계약번호
                askDvo.setContractSerialNumber(cntrSn); //계약일련번호
                askDvo.setProductCode(pdCd);//상품코드
                askDvo.setReceiveAskAmount(rveAmt);//수납요청금액
                askDvo.setReceiveStatusCode("01"); //수납상태코드
                askDvo.setIncmdcYn("N"); //소득공제여부
                askDvo.setReceiveAskObjectDrmNumber1(dpDt);
                askDvo.setReceiveCode(rveCd);
                // 수납요청상세 데이터 생성
                zwdzWithdrawalService.createReceiveAskDetail(askDvo);

                //수납요청상세 이력 생성
                zwdzWithdrawalService.createReceiveAskDetailHistory(askDvo);

                //지로입금내역 처리
                mapper.updateIntegrationItemization(itgDpNo);

                //통합입금기본 데이터 수정
                insertMap = new HashMap<>();
                insertMap.put("rveAkNo", receiveAskNumber);
                insertMap.put(ITG_DP_NO, itgDpNo);
                insertMap.put("rveCd", rveCd);

                mapper.updateIntegrationDeposit(insertMap);

                if (!StringUtils.isEmpty(itgDpNo)) {
                    //입금대사 서비스 호출
                    depositComparisonComfirmationService
                        .createDepositComparisonComfirmation(itgDpNo, null, "Y");
                }
            }
        }

        DCNT = WCNT - JCNT; // 단일건수
        String resultMsgText = msgService.getMessage("MSG_TXT_MTR_CT") + TCNT + " \n" // 자료 건수    :
            + msgService.getMessage("MSG_TXT_PROCS_FSH_CT") + ACNT + " \n" // 기 처리건    :
            + msgService.getMessage("MSG_TXT_ERR_QTY") + ECNT + " \n" // 오류 건수    :
            + msgService.getMessage("MSG_TXT_EXCD_CT") + RCNT + " \n" // 제외　건수    :
            + msgService.getMessage("MSG_TXT_PROCS_QTY") + WCNT + " \n" // 처리　건수    :
            + msgService.getMessage("MSG_TXT_PROCS_SINGL_CT") + DCNT + " \n" // 처리 단일건수 :
            + msgService.getMessage("MSG_TXT_PROCS_BNDL_CT") + JCNT + " \n" // 처리 묶음건수 :
            + msgService.getMessage("MSG_TXT_PROCS_TOT_AMT") + saveWamt + " \n"; // 처리 총금액    :

        return SaveResponse.builder().data(resultMsgText).processCount(processCount).build();
    }

    /**
     * 지로입금 에러 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    @Transactional
    public PagingResult<SearchErrosRes> getBillingDocumentErrorsPages(SearchReq dto, PageInfo pageInfo) {

        return mapper.selectBillingDocumentErrors(dto, pageInfo);
    }

    /**
     * 지로입금 에러 조회 엑셀 다운로드용
     *
     * @param dto
     * @return
     */
    @Transactional
    public List<SearchErrosRes> getBillingDocumentErrorsExcels(SearchReq dto) {

        return mapper.selectBillingDocumentErrors(dto);
    }

    /**
     * 지로입금에러 저장
     * @param dtos
     * @return
     * @throws Exception
     */
    @Transactional
    public int saveBillingDocumentErrors(List<SaveErrosReq> dtos) throws Exception {
        int processCount = 0;
        String errorChk;
        for (SaveErrosReq saveReq : dtos) {
            WwdbGiroDepositErrorSaveDvo dvo = convert.mapSaveGiroDepositErrorSaveDvo(saveReq);

            if (CommConst.ROW_STATE_UPDATED.equals(saveReq.rowState())) {
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
            } else {
                throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }
        return processCount;
    }

    /**
     * 지로 입금관리 원장 내역 조회
     *
     * @param dtos
     * @return
     */
    @Transactional
    public SearchLedgerItemizationRes getBillingDocumentMgtLedgerItemization(List<SearchLedgerItemizationReq> dtos) {
        List<String> fntDts = dtos.stream().distinct()
            .filter(dto -> "22".equals(dto.giroDpMtrDvCd())).map(SearchLedgerItemizationReq::fntDt).toList();
        return mapper.selectBillingDocumentMgtLedgerItemization(fntDts);
    }

    /**
     * 지로 입금관리 실적일자 조회
     *
     * @param dtos
     * @return
     */
    @Transactional
    public List<WwdbGiroDepositSaveDvo> getGiroPerfDt(List<SaveReq> dtos) {

        List<WwdbGiroDepositSaveDvo> editPerfDt = new ArrayList<>();

        //오늘 날짜
        String sysDateYmd = DateUtil.getNowDayString();

        for (SaveReq dto : dtos) {
            WwdbGiroDepositSaveDvo wwdbGiroDepositSaveDvo = convert.mapSearchWwwdbGiroDepositSaveDvo(dto);
            String perfDt = mapper.selectGiroPerfDt(sysDateYmd);
            SearchGiroNumberRes searchGiroNumberRes = mapper.selectGiroNumberInquiry(dto.giroInqNo());

            if (ObjectUtils.isEmpty(searchGiroNumberRes)) {
                wwdbGiroDepositSaveDvo.setRveDt("");
                wwdbGiroDepositSaveDvo.setFntDt("");
                wwdbGiroDepositSaveDvo.setCntrNo("");
                wwdbGiroDepositSaveDvo.setCntrSn("");
                wwdbGiroDepositSaveDvo.setCntr("");
                wwdbGiroDepositSaveDvo.setCstNo("");
                wwdbGiroDepositSaveDvo.setCstKnm("");
                wwdbGiroDepositSaveDvo.setProcsErrTpCd("1"); //오류코드
            } else {
                wwdbGiroDepositSaveDvo.setProcsErrTpCd("3"); //정상처리
                wwdbGiroDepositSaveDvo.setCntrNo(searchGiroNumberRes.cntrNo());
                wwdbGiroDepositSaveDvo.setCntrSn(searchGiroNumberRes.cntrSn());
                wwdbGiroDepositSaveDvo.setCntr(searchGiroNumberRes.cntr());
                wwdbGiroDepositSaveDvo.setCstNo(searchGiroNumberRes.cstNo());
                wwdbGiroDepositSaveDvo.setCstKnm(searchGiroNumberRes.cstKnm());
                wwdbGiroDepositSaveDvo.setRveDt(perfDt);
                wwdbGiroDepositSaveDvo.setFntDt(sysDateYmd);
            }
            editPerfDt.add(wwdbGiroDepositSaveDvo);
        }

        return editPerfDt;
    }

    /**
     * 지로 대사여부 확인
     * @param req
     * @return int
     */
    public int getBillingFntDtChk(SearchChkReq req) {
        return mapper.selectBillingFntDtChk(req);
    }
}
