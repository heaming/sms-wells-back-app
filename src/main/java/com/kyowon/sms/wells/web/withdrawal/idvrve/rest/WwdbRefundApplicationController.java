package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.EditRefundReq;
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
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbRefundApplicationService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[입금관리 - 개별수납] 환불 신청 현황")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/refund-applications")
public class WwdbRefundApplicationController {

    private final WwdbRefundApplicationService service;

    @ApiOperation(value = "환불 신청 현황 목록", notes = "환불 신청 현황 목록 조회")
    @GetMapping("/paging")
    public PagingResult<SearchRefundApplicationRes> getRefundApplicationPages(
        @ApiParam
        @Valid
        SearchRefundApplicationReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getRefundApplicationPages(req, pageInfo);
    }

    @ApiOperation(value = "환불 신청 현황 목록 엑셀 다운로드", notes = "환불 신청 현황 목록 엑셀 다운로드")
    @GetMapping("/excel-download")
    public List<SearchRefundApplicationRes> getRefundApplicationExcels(
        SearchRefundApplicationReq req
    ) {
        return service.getRefundApplicationExcels(req);
    }

    @ApiOperation(value = "환불 신청 현황 목록 엑셀 업로드", notes = "환불 신청 현황 목록 엑셀 업로드")
    @PostMapping("/excel-upload")
    public UploadRes saveRefundApplicationExcelUpload(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {
        return service.saveRefundApplicationExcelUpload(file);
    }

    @ApiOperation(value = "환불 신청 팝업 (계약상세)", notes = "환불 신청 팝업 (계약상세) 목록 조회")
    @GetMapping("/reg/paging")
    public PagingResult<SearchRefundContractDetailRes> getRefundContractDetailPages(
        @ApiParam
        @Valid
        SearchRefundContractDetailReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getRefundContractDetailPages(req, pageInfo);
    }

    @ApiOperation(value = "환불 신청 팝업 (계약상세) 목록 엑셀 다운로드", notes = "환불 신청 팝업 (계약상세) 목록 엑셀 다운로드")
    @GetMapping("/reg/excel-download")
    public List<SearchRefundContractDetailRes> getRefundContractDetailExcels(
        SearchRefundContractDetailReq req
    ) {
        return service.getRefundContractDetailExcels(req);
    }

    @ApiOperation(value = "환불 신청 팝업 환불가능금액 조회", notes = "환불 신청 팝업 환불가능금액 조회")
    @GetMapping("/reg/refund-possible-amount")
    public SearchRefundPossibleAmountRes getRefundPossibleAmount(
        @ApiParam
        @Valid
        SearchRefundPossibleAmountReq req
    ) {
        return service.getRefundPossibleAmount(req);
    }

    @ApiOperation(value = "환불 은행사 조회", notes = "지급 구분에 따른 환불 카드사 조회")
    @GetMapping("/reg/card")
    public List<SearchCardRes> getRefundApplicationCard() {
        return service.getRefundApplicationCard();
    }

    @ApiOperation(value = "환불 카드사 조회", notes = "지급 구분에 따른 환불 은행사 조회")
    @GetMapping("/reg/bank")
    public List<SearchBankRes> getRefundApplicationBank() {
        return service.getRefundApplicationBank();
    }

    @ApiOperation(value = "환불 신청 팝업 환불 등록", notes = "환불 신청 팝업 환불 등록")
    @PostMapping("/reg/refund-possible-amount")
    public SaveResponse createRefundApplication(
        @RequestBody
        @Valid
        SaveRefundReq req
    ) throws Exception {
        log.info("req:" + req);
        return SaveResponse
            .builder()
            .processCount(service.createRefundApplication(req))
            .build();
    }

    @ApiOperation(value = "환불 신청 팝업 환불신청, 예외환불사유, 환불접수총액, 처리정보 조회", notes = "환불 신청 팝업 환불신청, 예외환불사유, 환불접수총액, 처리정보 조회")
    @GetMapping("/status/refund-application-info")
    public SearchRefundApplicationInfoRes getRefundApplicationInfo(
        @RequestParam
        String rfndRcpNo
    ) {
        return service.getRefundApplicationInfo(rfndRcpNo);
    }

    @ApiOperation(value = "환불 신청 팝업 환불 수정", notes = "환불 신청 팝업 환불 수정")
    @PostMapping("/status/refund-application-info")
    public SaveResponse editRefundApplication(
        @RequestBody
        @Valid
        EditRefundReq req
    ) throws Exception {
        log.info("EditRefundReq:" + req);
        return SaveResponse
            .builder()
            .processCount(service.editRefundApplication(req))
            .build();
    }

    @ApiOperation(value = "환불 신청 팝업 환불 삭제   ", notes = "환불 신청 팝업 환불 삭제")
    @DeleteMapping("/status/refund-application-info")
    public SaveResponse removeRefundApplication(
        @RequestParam
        String rfndRcpNo
    ) throws Exception {
        return SaveResponse
            .builder()
            .processCount(service.removeRefundApplication(rfndRcpNo))
            .build();
    }

    @ApiOperation(value = "환불 신청 상세 내역 신청정보 조회", notes = "환불 신청 상세 내역 신청정보 조회")
    @GetMapping("/detail/partner")
    public List<SearchRefundApplicationDetailPartnerRes> getRefundApplicationDetailPartner(
        @RequestParam
        String rfndRcpNo
    ) {
        return service.getRefundApplicationDetailPartner(rfndRcpNo);
    }

    @ApiOperation(value = "계약정보, 환불신청, 처리정보", notes = "계약정보, 환불신청, 처리정보")
    @GetMapping("/detail/basic")
    public SearchRefundApplicationDetailRes getRefundApplicationDetail(
        @ApiParam
        @Valid
        SearchRefundApplicationDetailReq req
    ) {
        return service.getRefundApplicationDetail(req);
    }

    @ApiOperation(value = "환불 신청 상세 내역 환불가능금액 조회", notes = "환불 신청 상세 내역 환불가능금액 조회")
    @GetMapping("/detail/possible")
    public List<SearchRefundApplicationDetailPossibleRes> getRefundApplicationDetailPossible(
        @ApiParam
        @Valid
        SearchRefundApplicationDetailPossibleReq req
    ) {
        return service.getRefundApplicationDetailPossible(req);
    }

    @ApiOperation(value = "환불 신청 상세 내역 상세입금 조회", notes = "환불 신청 상세 내역 상세입금 조회")
    @GetMapping("/detail/deposit")
    public PagingResult<SearchRefundApplicationDetailDepositRes> getRefundApplicationDetailDepositPages(
        @ApiParam
        @Valid
        SearchRefundApplicationDetailDepositReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getRefundApplicationDetailDepositPages(req, pageInfo);
    }

    @ApiOperation(value = "환불 신청 상세 내역 상세입금 조회 엑셀 다운로드", notes = "환불 신청 상세 내역 상세입금 조회 엑셀 다운로드")
    @GetMapping("/detail/deposit/excel-download")
    public List<SearchRefundApplicationDetailDepositRes> getRefundApplicationDetailDepositExcels(
        SearchRefundApplicationDetailDepositReq req
    ) {
        return service.getRefundApplicationDetailDepositExcels(req);
    }

    @ApiOperation(value = "환불 신청 상세 내역 매출실적조회", notes = "환불 신청 상세 내역 매출실적조회")
    @GetMapping("/detail/performance")
    public PagingResult<SearchRefundApplicationDetailPerformanceRes> getRefundApplicationDetailPerformancePages(
        @ApiParam
        @Valid
        SearchRefundApplicationDetailPerformanceReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getRefundApplicationDetailPerformancePages(req, pageInfo);
    }

    @ApiOperation(value = "환불 신청 상세 내역 매출실적조회 엑셀 다운로드", notes = "환불 신청 상세 내역 매출실적조회 엑셀 다운로드")
    @GetMapping("/detail/performance/excel-download")
    public List<SearchRefundApplicationDetailPerformanceRes> getRefundApplicationDetailPerformanceExcels(
        SearchRefundApplicationDetailPerformanceReq req
    ) {
        return service.getRefundApplicationDetailPerformanceExcels(req);
    }

    @ApiOperation(value = "환불 신청 상세 내역 환불접수총액 조회", notes = "환불 신청 상세 내역 환불접수총액 조회")
    @GetMapping("/detail/receipt")
    public List<SearchRefundApplicationDetailReceiptRes> getRefundApplicationDetailReceipt(
        @RequestParam
        String rfndRcpNo
    ) {
        return service.getRefundApplicationDetailReceipt(rfndRcpNo);
    }

    @ApiOperation(value = "환불 신청 컨텍 이력 사항", notes = "환불 신청 컨텍 이력 사항 조회")
    @GetMapping("/connect-history/paging")
    public PagingResult<SearchRefundApplicationConnectHistoryRes> getRefundApplicationConnectHistoryPages(
        @RequestParam
        String cntrNo,
        PageInfo pageInfo
    ) {
        return service.getRefundApplicationConnectHistoryPages(cntrNo, pageInfo);
    }

    @ApiOperation(value = "환불 신청 컨텍 이력 사항 엑셀 다운로드", notes = "환불 신청 컨텍 이력 사항 엑셀 다운로드")
    @GetMapping("/connect-history/excel-download")
    public List<SearchRefundApplicationConnectHistoryRes> getRefundApplicationConnectHistoryExcels(
        @RequestParam
        String cntrNo
    ) {
        return service.getRefundApplicationConnectHistoryExcels(cntrNo);
    }

}
