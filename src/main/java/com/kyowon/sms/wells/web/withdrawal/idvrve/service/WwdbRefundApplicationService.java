package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbRefundApplicationConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.App;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.Ctract;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.EditRefundReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.RefundBasic;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.RefundDetail;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SaveRefundReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchBankRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchCardRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationConnectHistoryRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailDepositReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailDepositRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailPartnerRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailPerformanceReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailPerformanceRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailPossibleReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailPossibleRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailReceiptRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationInfoRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundContractDetailReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundContractDetailRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundPossibleAmountReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundPossibleAmountRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundApplicationDetailDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundApplicationDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundApplicationExcelUploadDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundApplicationInfoDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbRefundApplicationMapper;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WwdbRefundApplicationService {

    private final WwdbRefundApplicationMapper mapper;
    private final WwdbRefundApplicationConverter converter;
    private final ExcelReadService excelReadService;
    private final MessageResourceService messageService;

    /**
     * 환불 신청 현황 목록
     * @param pageInfo 
     * @param SearchRefundApplicationReq
     * @return PagingResult<SearchRefundApplicationRes>
     */
    public PagingResult<SearchRefundApplicationRes> getRefundApplicationPages(
        SearchRefundApplicationReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectRefundApplication(req, pageInfo);
    }

    /**
     * 환불 신청 현황 목록 엑셀 다운로드
     * @param List 
     * @param SearchRefundApplicationReq
     * @return List<SearchRefundApplicationRes>
     */
    public List<SearchRefundApplicationRes> getRefundApplicationExcels(
        SearchRefundApplicationReq req
    ) {
        return mapper.selectRefundApplication(req);
    }

    @Transactional
    public ExcelUploadDto.UploadRes saveRefundApplicationExcelUpload(MultipartFile file) throws Exception {
        // 업로드 엑셀 헤더 읽기
        // 업로드 기능 임시 작성. 
        // 업로드한 내용이 화면에서 보이기 위해서는 계약기본, 계약상세, 상품기본, 고객기본, 수납상세 데이터 들이 일치해야함. 
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("cntrNo", messageService.getMessage("MSG_TXT_CNTR_NO")); // 계약번호
        headerTitle.put("cntrSn", messageService.getMessage("MSG_TXT_CNTR_SN")); // 계약일련번호
        headerTitle.put("cstNo", messageService.getMessage("MSG_TXT_CST_NO")); // 고객번호
        headerTitle.put("exRfndRsonCn", messageService.getMessage("MSG_TXT_EX_RFND_RSON")); // 예외환불사유
        headerTitle.put("rfndCshAmt", messageService.getMessage("MSG_TXT_CSH_RFND_AMT")); // 현금 환불 금액
        headerTitle.put("rfndCardAmt", messageService.getMessage("MSG_TXT_CARD_RFND_AMT")); // 카드 환불금액
        headerTitle.put("rfndBltfAmt", messageService.getMessage("MSG_TXT_BLTF_AMT")); // 전금금액
        headerTitle.put("rfndPsbResAmt", messageService.getMessage("MSG_TXT_RFND_PSB_BLAM")); // 환불가능잔액
        headerTitle.put("totRfndEtAmt", messageService.getMessage("MSG_TXT_TOT_RFND_PSB_BLAM")); // 총 환불 가능 잔액

        headerTitle.put("rveNo", messageService.getMessage("MSG_TXT_RVE_NO")); // 수납번호
        headerTitle.put("rfndDvCd", messageService.getMessage("MSG_TXT_CLSF_REFUND")); // 환불구분 : 01.전체환불, 02.부분환불
        headerTitle.put("rfndDsbDvCd", messageService.getMessage("MSG_TXT_RFND_DSB_DV_CD")); // 환불지급구분코드 01.현금환불, 02.카드환불, 03.전금
        headerTitle.put("cshRfndDvCd", messageService.getMessage("MSG_TXT_CSH_RFND_DV")); // 현금환불구분. 현금환불구분코드 01.선환불, 02.일반환불; 03.카드현금
        headerTitle.put("rfndAcDvCd", messageService.getMessage("MSG_TXT_RFND_AC_DV")); // 환불계좌 구분. 환불계좌구분코드 01.기입금 계좌, 02.신규 계좌
        headerTitle.put("cshRfndFnitCd", messageService.getMessage("MSG_TXT_DSB_BNK")); // 지급은행
        headerTitle.put("cshRfndAcnoEncr", messageService.getMessage("MSG_TXT_AC_NO")); // 계좌번호
        headerTitle.put("cshRfndAcownNm", messageService.getMessage("MSG_TXT_ACHLDR")); // 예금주
        headerTitle.put("cshRfndAdrsDvCd", messageService.getMessage("MSG_TXT_ADRS")); // 수취인
        headerTitle.put("cshRfndDlgpsNm", messageService.getMessage("MSG_TXT_RPRS_NAME")); // 대표자
        headerTitle.put("startDay", messageService.getMessage("MSG_TXT_APR_D_STRT")); // 승인일 시작
        headerTitle.put("endDay", messageService.getMessage("MSG_TXT_APR_D_END")); // 승인일 종료
        headerTitle.put("rfndAkAmt", messageService.getMessage("MSG_TXT_RFND_APLC_AMT")); // 환불신청금액, 전금 요청금액(원)
        headerTitle.put("rfndDsbAmt", messageService.getMessage("MSG_TXT_ACL_DSB_AMT_WON")); // 실지급액(원)
        headerTitle.put("rfndRsonCd", messageService.getMessage("MSG_TXT_RFND_RSON_CD")); // 환불사유코드
        headerTitle.put("cardRfndFnitCd", messageService.getMessage("MSG_TXT_CARD_DV")); // 카드구분
        headerTitle.put("cardRfndCrcdnoEncr", messageService.getMessage("MSG_TXT_CARD_NO")); // 카드번호
        headerTitle.put("cardRfndFer", messageService.getMessage("MSG_TXT_CARD_DDTN_AMT")); // 카드 공제액
        headerTitle.put("rfndRsonCn", messageService.getMessage("MSG_TXT_ETC_RFND_RSON")); // 기타환불사유
        headerTitle.put("bltfRfndDvCd", messageService.getMessage("MSG_TXT_BLTF_DV")); // 전금구분
        headerTitle.put("bltfRfndMbDvCd", messageService.getMessage("MSG_TXT_MB_DV")); // 회원구분
        headerTitle.put("bltfOjCntrSn", messageService.getMessage("MSG_TXT_CST_NO")); // 고객번호
        headerTitle.put("bltfBfVacNoEncr", messageService.getMessage("MSG_TXT_BLTF_BF_VAC")); // 전금전 가상계좌
        headerTitle.put("bltfAfVacNoEncr", messageService.getMessage("MSG_TXT_BLTF_AF_VAC")); // 전금후 가상계좌

        // 유효성검사에 필요한 문자열 패턴들
        String specialPattern = "^[0-9a-zA-Zㄱ-ㅎ가-힣]*$"; //특수문자 정규식
        String pattern = "^[0-9]*$"; //숫자 정규식
        String status = "S";

        // 중복검사에 필요한 내용. 일단 제외.
        // List<Map<String, Object>> checks = new ArrayList<>();

        // 읽힌 엑셀 헤더 불러들이기
        ExcelMetaDvo meta = new ExcelMetaDvo(1, headerTitle);

        // 업로드 엑셀 파일 읽기
        List<WwdbRefundApplicationExcelUploadDvo> refundApplications = excelReadService
            .readExcel(file, meta, WwdbRefundApplicationExcelUploadDvo.class);

        // 엑셀 업로드 에러 반환 값들
        List<ExcelUploadErrorDvo> errorDvos = new ArrayList<>();

        int row = 2;
        // 유효성검사 시작
        for (WwdbRefundApplicationExcelUploadDvo refundApplication : refundApplications) {
            ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
            // Map<String, Object> check = new HashMap<>(); // 중복체크. 일단 제외. validation은 WwdcSalesControlService를 따라함
            int finalRow = row;
            // int cntrExistCnt = 0; // 기존 데이터와 체크. 일단 제외
            // int partnerCnt = 0;
            if (StringUtils.isEmpty(refundApplication.getCntrNo())) { // 유효성
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("cntrNo"));
                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            } else {
                if (!Pattern.matches(specialPattern, refundApplication.getCntrNo())) {
                    errorDvo.setErrorRow(finalRow);
                    errorDvo.setHeaderName(headerTitle.get("cntrNo"));
                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_CHK_SPECL_CHAR")); // 특수 문자는 허용되지 않습니다.
                } else {
                    if (StringUtils.isEmpty(refundApplication.getCntrSn())) { // 유효성
                        errorDvo.setErrorRow(finalRow);
                        errorDvo.setHeaderName(headerTitle.get("cntrSn"));
                        errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
                    } else {
                        if (!Pattern.matches(pattern, refundApplication.getCntrSn())) {
                            errorDvo.setErrorRow(finalRow);
                            errorDvo.setHeaderName(headerTitle.get("cntrSn"));
                            errorDvo.setErrorData(messageService.getMessage("MSG_ALT_ONLY_NUMBER")); // 숫자만 입력 가능합니다
                        }
                    }
                }
            }
            if (null != errorDvo && (errorDvo.getErrorRow() != 0)) {
                errorDvos.add(errorDvo);
            }
            row++;
        }

        if (CollectionUtils.isNotEmpty(errorDvos)) {
            status = "E";
        }

        // 엑셀 업로드 값 입력
        if (status.equals("S")) {
            for (WwdbRefundApplicationExcelUploadDvo refundApplication : refundApplications) {

                mapper.insertRefundApplicationDetail(refundApplication);
                mapper.insertRefundApplicationDetailHistory(refundApplication);
                mapper.insertRefundApplication(refundApplication);
                mapper.insertRefundApplicationHistory(refundApplication);
            }

        }
        ExcelUploadDto.UploadRes result = ExcelUploadDto.UploadRes.builder()
            .status(status)
            .excelData(refundApplications)
            .errorInfo(errorDvos)
            .build();
        return result;
    }

    /**
     * 환불 신청 현황 상세 계약 조회
     * @param List 
     * @param SearchRefundContractDetailReq
     * @return PagingResult<SearchRefundContractDetailRes> 
     */
    public PagingResult<SearchRefundContractDetailRes> getRefundContractDetailPages(

        SearchRefundContractDetailReq req, PageInfo pageInfo
    ) {
        return mapper.selectRefundContractDetail(req, pageInfo);
    }

    /**
     * 환불 신청 현황 상세 계약 목록 엑셀 다운로드
     * @param List 
     * @param SearchRefundContractDetailReq
     * @return PagingResult<SearchRefundContractDetailRes> 
     */
    public List<SearchRefundContractDetailRes> getRefundContractDetailExcels(
        SearchRefundContractDetailReq req
    ) {
        return mapper.selectRefundContractDetail(req);
    }

    /**
     * 환불 신청 팝업 환불가능금액 조회
     * @param SearchRefundPossibleAmountReq
     * @return SearchRefundPossibleAmountRes 
     */
    public SearchRefundPossibleAmountRes getRefundPossibleAmount(
        SearchRefundPossibleAmountReq req
    ) {
        return mapper.selectRefundPossibleAmount(req);
    }

    /**
     * 카드사 이름과 코드 조회
     * @return List<SearchCardBankRes>
     */
    public List<SearchCardRes> getRefundApplicationCard() {
        return mapper.selectRefundApplicationCard();
    }

    /**
     * 은행사 이름과 코드 조회
     * @param rfndDsbDvCd 지급구분
     * @return List<SearchCardBankRes>
     */
    public List<SearchBankRes> getRefundApplicationBank() {
        return mapper.selectRefundApplicationBank();
    }

    /**
     * 환불 신청 팝업 환불 등록
     * @param SaveReq req
     * @return processCount
     */
    @Transactional
    public int createRefundApplication(SaveRefundReq req) throws Exception {

        log.info("Service SaveReq: " + req);
        int processCount = 0;

        WwdbRefundApplicationDvo vo = converter.mapSaveWwdbRefundApplicationDvo(req);

        // 환불접수번호 seq 때문에 상세 입력 후 상세히스토리, 기본 입력 후 기본히스토리 입력함.
        // 환불접수기본은 1번만, 환불접수상세는 vo.getDetails의 길이 만큼 반복한다.

        for (WwdbRefundApplicationDetailDvo details : vo.getDetails()) {
            log.info("===========================WwdbRefundApplicationDvo.details=================================");
            log.info("WwdbRefundApplicationDvo.details", details);
            log.info("===========================WwdbRefundApplicationDvo.details=================================");
            processCount += mapper.insertRefundApplicationDetail(details);
            processCount += mapper.insertRefundApplicationDetailHistory(details);
        }

        processCount += mapper.insertRefundApplication(vo);
        processCount += mapper.insertRefundApplicationHistory(vo);

        // 수납상세에 수납구분코드? 환불로 insert 쿼리 작성해야함 

        return processCount;
    }

    /**
     * 환불 신청 팝업 환불신청, 예외환불사유, 환불접수총액, 처리정보 조회
     * @param String rfndRcpNo
     * @return List<SearchRefundApplicationInfoRes>
     */
    public SearchRefundApplicationInfoRes getRefundApplicationInfo(
        String rfndRcpNo
    ) {
        //    RefundDetail 에 조회된 내용을 저장
        List<RefundDetail> RefundDetail = mapper.selectRefundApplicationDetailInfo(rfndRcpNo);
        RefundBasic basic = mapper.selectRefundApplicationInfo(rfndRcpNo);
        log.info("====================================================20230515basic=====");
        log.info("RefundDetail", RefundDetail);
        log.info("basic", basic);
        log.info("====================================================20230515basic=====");
        return new SearchRefundApplicationInfoRes(basic, RefundDetail);
    }

    /**
     * 환불 신청 팝업 환불 수정
     * @param SaveReq req
     * @return processCount
     */
    @Transactional
    public int editRefundApplication(EditRefundReq req) throws Exception {

        int processCount = 0;

        WwdbRefundApplicationInfoDvo vo = converter.mapEditWwdbRefundApplicationDvo(req);

        for (WwdbRefundApplicationDetailDvo details : vo.getDetails()) {

            processCount += mapper.updateRefundApplicationDetail(details);
            processCount += mapper.insertRefundApplicationDetailHistory(details);

        }

        processCount += mapper.updateRefundApplication(vo.getBasic());
        processCount += mapper.insertRefundApplicationHistory(vo);

        return processCount;
    }

    /**
     * 환불 신청 팝업 환불 삭제
     * @param SaveReq req
     * @return processCount
     */
    @Transactional
    public int removeRefundApplication(String rfndRcpNo) throws Exception {

        int processCount = 0;

        processCount += mapper.insertRefundApplicationHistory(rfndRcpNo);
        processCount += mapper.deleteRefundApplication(rfndRcpNo);

        return processCount;
    }

    /**
     * 환불 신청 상세 내역 신청정보 조회 
     * @param pageInfo 
     * @param String rfndRcpNo,
     * @return PagingResult<SearchRefundApplicationDetailPartnerRes>
     */
    public List<SearchRefundApplicationDetailPartnerRes> getRefundApplicationDetailPartner(
        String rfndRcpNo
    ) {
        return mapper.selectRefundApplicationDetailPartner(rfndRcpNo);
    }

    /**
     * 환불 신청 상세 내역 환불가능금액 조회 
     * @param pageInfo 
     * @param SearchRefundApplicationDetailPossibleReq req,
     * @return PagingResult<SearchRefundApplicationDetailPartnerRes>
     */
    public List<SearchRefundApplicationDetailPossibleRes> getRefundApplicationDetailPossible(
        SearchRefundApplicationDetailPossibleReq req
    ) {
        return mapper.selectRefundApplicationDetailPossible(req);
    }

    /**
     * 환불 신청 상세 내역 상세입금 조회 
     * @param pageInfo 
     * @param SearchRefundApplicationDetailPossibleReq req,
     * @return PagingResult<SearchRefundApplicationDetailPartnerRes>
     */
    public PagingResult<SearchRefundApplicationDetailDepositRes> getRefundApplicationDetailDepositPages(
        SearchRefundApplicationDetailDepositReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectRefundApplicationDetailDeposit(req, pageInfo);
    }

    public List<SearchRefundApplicationDetailDepositRes> getRefundApplicationDetailDepositExcels(
        SearchRefundApplicationDetailDepositReq req
    ) {
        return mapper.selectRefundApplicationDetailDeposit(req);
    }

    /**
     * 환불 신청 상세 내역 매출실적조회 
     * @param pageInfo 
     * @param SearchRefundApplicationDetailPossibleReq req,
     * @return PagingResult<SearchRefundApplicationDetailPartnerRes>
     */
    public PagingResult<SearchRefundApplicationDetailPerformanceRes> getRefundApplicationDetailPerformancePages(
        SearchRefundApplicationDetailPerformanceReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectRefundApplicationDetailPerformance(req, pageInfo);
    }

    public List<SearchRefundApplicationDetailPerformanceRes> getRefundApplicationDetailPerformanceExcels(
        SearchRefundApplicationDetailPerformanceReq req
    ) {
        return mapper.selectRefundApplicationDetailPerformance(req);
    }

    /**
     * 환불 신청 팝업 환불신청, 예외환불사유, 환불접수총액, 처리정보 조회
     * @param String rfndRcpNo
     * @return List<SearchRefundApplicationInfoRes>
     */
    public SearchRefundApplicationDetailRes getRefundApplicationDetail(
        SearchRefundApplicationDetailReq req
    ) {
        // 계약정보
        Ctract ctract = mapper.selectRefundApplicationDetailContract(req);

        // 환불신청 테이블
        App app = mapper.selectRefundApplicationDetailApplication(req);

        // 환불신청 // 추가 버튼 누르면 추가로 생성되는 부분
        List<RefundDetail> refundDetail = mapper.selectRefundApplicationDetailInfo2(req);

        // 예외사유, 환불접수총액, 처리정보 출력
        RefundBasic basic = mapper.selectRefundApplicationInfo(req);

        return new SearchRefundApplicationDetailRes(
            ctract, // 계약정보
            app, // 환불신청
            basic, // 예외사유, 환불접수총액, 처리정보 출력
            refundDetail // 환불신청 // 추가 버튼 누르면 추가로 생성되는 부분
        );
    }

    public List<SearchRefundApplicationDetailReceiptRes> getRefundApplicationDetailReceipt(
        String rfndRcpNo
    ) {
        return mapper.selectRefundApplicationDetailReceipt(rfndRcpNo);
    }

    /**
     * 환불 신청 컨텍 이력 사항
     * @param List 
     * @param SearchRefundApplicationConnectHistoryReq 
     * @return PagingResult<SearchRefundApplicationConnectHistoryRes> 
     */
    public PagingResult<SearchRefundApplicationConnectHistoryRes> getRefundApplicationConnectHistoryPages(

        //        SearchRefundApplicationConnectHistoryReq req, PageInfo pageInfo

        String cntrNo, PageInfo pageInfo
    ) {
        return mapper.selectRefundApplicationConnectHistory(cntrNo, pageInfo);
    }

    /**
     * 환불 신청 컨텍 이력 사항
     * @param List 
     * @param SearchRefundApplicationConnectHistoryReq
     * @return PagingResult<SearchRefundApplicationConnectHistoryRes> 
     */
    public List<SearchRefundApplicationConnectHistoryRes> getRefundApplicationConnectHistoryExcels(
        String cntrNo
    ) {
        return mapper.selectRefundApplicationConnectHistory(cntrNo);
    }

}
