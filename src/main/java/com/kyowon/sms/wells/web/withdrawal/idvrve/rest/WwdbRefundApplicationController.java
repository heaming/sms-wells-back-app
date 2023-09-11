package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.*;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbRefundApplicationService;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaAutoTransferInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
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
        @ApiParam @Valid
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

    @ApiOperation(value = "환불 신청 팝업 (환불기본)", notes = "환불 신청 팝업 (환불기본)")
    @GetMapping("/reg/refund")
    public SearchRefundRes getRefundApplication(
        SearchRefundReq req
    ) {
        return service.getRefundApplication(req);
    }

    /* 환불상세 */
    @ApiOperation(value = "환불상세 목록 조회", notes = "환불상세 목록 조회")
    @GetMapping("/reg/refund-detail")
    public PagingResult<SearchRefundDetailRes> getRefundDetailPages(
        @ApiParam @Valid
        SearchRefundDetailReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getRefundDetailPages(req, pageInfo);
    }

    //-------- 환불신청현황 화면

    //    @ApiOperation(value = "환불 신청 현황 목록 엑셀 업로드", notes = "환불 신청 현황 목록 엑셀 업로드")
    //    @PostMapping("/excel-upload")
    //    public UploadRes saveRefundApplicationExcelUpload(
    //        @RequestParam("file")
    //        MultipartFile file
    //    ) throws Exception {
    //        return service.saveRefundApplicationExcelUpload(file);
    //    }

    @ApiOperation(value = "환불 신청 팝업 (계약상세)", notes = "환불 신청 팝업 (계약상세) 목록 조회")
    @GetMapping("/reg/paging")
    public PagingResult<SearchRefundContractDetailRes> getRefundContractDetailPages(
        @ApiParam @Valid
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
    ////////////////////////////////////////

    /* TODO: 환불 팝업 저장 */
    @ApiOperation(value = "환불 신청 팝업 저장", notes = "환불 신청 팝업 저장")
    @PostMapping("/reg/save")
    public SaveResponse getRefundTempSave(
        @RequestBody @Valid
        SaveReq req
    ) throws Exception {
        System.out.println("=========================1===================");
        System.out.println("=========================1===================");
        return SaveResponse
            .builder()
            .processCount(service.getRefundTempSave(req))
            .build();
    }

    @ApiOperation(value = "환불 신청 팝업 삭제", notes = "환불 신청 팝업 삭제")
    @DeleteMapping("/reg/delete")
    public SaveResponse getRefundDelete(
        @Valid
        removeReq req
    ) throws Exception {
        System.out.println("============");
        System.out.println(req.rfndAkNo());
        System.out.println("============");
        return SaveResponse
            .builder()
            .processCount(service.getRefundDelete(req))
            .build();
    }

    /* TODO: 환불저장 END */

    /* TODO: 메인그리드에서 호출 */
    /* TODO: 환불신청팝업 - 계약상세(메인에서조회) */
    @ApiOperation(value = "환불 신청 팝업 (계약상세)", notes = "환불 신청 팝업 (계약상세) 목록 조회")
    @GetMapping("/reg/base-paging")
    public PagingResult<SearchRefundBaseRes> getRefundBasePages(
        @ApiParam @Valid
        SearchRefundBaseReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getRefundBasePages(req, pageInfo);
    }

    //    /* TODO: 환불신청팝업 - 환불상세(메인에서조회) */
    //    @ApiOperation(value = "환불 신청 팝업 (환불상세)", notes = "환불 신청 팝업 (계약상세) 목록 조회")
    //    @GetMapping("/reg/refund-paging")
    //    public PagingResult<SearchRefundCallDetailRes> getRefundBasePages(
    //        @ApiParam
    //        @Valid
    //        SearchRefundCallDetailReq req,
    //        @Valid
    //        PageInfo pageInfo
    //    ) {
    //        return service.getRefundCallDetailPages(req, pageInfo);
    //    }

    /* TODO: 환불신청팝업 - 전금상세(메인에서조회) */
    @ApiOperation(value = "환불 신청 팝업 (전금상세)", notes = "환불 신청 팝업 (전금상세) 목록 조회")
    @GetMapping("/reg/balance-transfer")
    public PagingResult<SearchRefundBalanceTransferRes> getRefundBalanceTransferPages(
        @ApiParam @Valid
        SearchRefundBalanceTransferReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getRefundBalanceTransferPages(req, pageInfo);
    }

    // TODO: 미사용데이터 //////////////////////////////////////
    //    @ApiOperation(value = "환불 신청 팝업 환불가능금액 조회", notes = "환불 신청 팝업 환불가능금액 조회")
    //    @GetMapping("/reg/refund-possible-amount")
    //    public SearchRefundPossibleAmountRes getRefundPossibleAmount(
    //        @ApiParam
    //        @Valid
    //        SearchRefundPossibleAmountReq req
    //    ) {
    //        return service.getRefundPossibleAmount(req);
    //    }
    //
    //    @ApiOperation(value = "환불 은행사 조회", notes = "지급 구분에 따른 환불 카드사 조회")
    //    @GetMapping("/reg/card")
    //    public List<SearchCardRes> getRefundApplicationCard() {
    //        return service.getRefundApplicationCard();
    //    }
    //
    //    @ApiOperation(value = "환불 카드사 조회", notes = "지급 구분에 따른 환불 은행사 조회")
    //    @GetMapping("/reg/bank")
    //    public List<SearchBankRes> getRefundApplicationBank() {
    //        return service.getRefundApplicationBank();
    //    }
    //
    //    @ApiOperation(value = "환불 신청 팝업 환불 등록", notes = "환불 신청 팝업 환불 등록")
    //    @PostMapping("/reg/refund-possible-amount")
    //    public SaveResponse createRefundApplication(
    //        @RequestBody
    //        @Valid
    //        SaveRefundReq req
    //    ) throws Exception {
    //        log.info("req:" + req);
    //        return SaveResponse
    //            .builder()
    //            .processCount(service.createRefundApplication(req))
    //            .build();
    //    }
    //
    //    @ApiOperation(value = "환불 신청 팝업 환불신청, 예외환불사유, 환불접수총액, 처리정보 조회", notes = "환불 신청 팝업 환불신청, 예외환불사유, 환불접수총액, 처리정보 조회")
    //    @GetMapping("/status/refund-application-info")
    //    public SearchRefundApplicationInfoRes getRefundApplicationInfo(
    //        @RequestParam
    //        String rfndRcpNo
    //    ) {
    //        return service.getRefundApplicationInfo(rfndRcpNo);
    //    }
    //
    //    @ApiOperation(value = "환불 신청 팝업 환불 수정", notes = "환불 신청 팝업 환불 수정")
    //    @PostMapping("/status/refund-application-info")
    //    public SaveResponse editRefundApplication(
    //        @RequestBody
    //        @Valid
    //        EditRefundReq req
    //    ) throws Exception {
    //        log.info("EditRefundReq:" + req);
    //        return SaveResponse
    //            .builder()
    //            .processCount(service.editRefundApplication(req))
    //            .build();
    //    }
    //
    //    @ApiOperation(value = "환불 신청 팝업 환불 삭제   ", notes = "환불 신청 팝업 환불 삭제")
    //    @DeleteMapping("/status/refund-application-info")
    //    public SaveResponse removeRefundApplication(
    //        @RequestParam
    //        String rfndRcpNo
    //    ) throws Exception {
    //        return SaveResponse
    //            .builder()
    //            .processCount(service.removeRefundApplication(rfndRcpNo))
    //            .build();
    //    }
    //
    //    @ApiOperation(value = "환불 신청 상세 내역 신청정보 조회", notes = "환불 신청 상세 내역 신청정보 조회")
    //    @GetMapping("/detail/partner")
    //    public List<SearchRefundApplicationDetailPartnerRes> getRefundApplicationDetailPartner(
    //        @RequestParam
    //        String rfndRcpNo
    //    ) {
    //        return service.getRefundApplicationDetailPartner(rfndRcpNo);
    //    }
    //
    //    @ApiOperation(value = "계약정보, 환불신청, 처리정보", notes = "계약정보, 환불신청, 처리정보")
    //    @GetMapping("/detail/basic")
    //    public SearchRefundApplicationDetailRes getRefundApplicationDetail(
    //        @ApiParam
    //        @Valid
    //        SearchRefundApplicationDetailReq req
    //    ) {
    //        return service.getRefundApplicationDetail(req);
    //    }
    //
    //    @ApiOperation(value = "환불 신청 상세 내역 환불가능금액 조회", notes = "환불 신청 상세 내역 환불가능금액 조회")
    //    @GetMapping("/detail/possible")
    //    public List<SearchRefundApplicationDetailPossibleRes> getRefundApplicationDetailPossible(
    //        @ApiParam
    //        @Valid
    //        SearchRefundApplicationDetailPossibleReq req
    //    ) {
    //        return service.getRefundApplicationDetailPossible(req);
    //    }
    //
    //    @ApiOperation(value = "환불 신청 상세 내역 상세입금 조회", notes = "환불 신청 상세 내역 상세입금 조회")
    //    @GetMapping("/detail/deposit")
    //    public PagingResult<SearchRefundApplicationDetailDepositRes> getRefundApplicationDetailDepositPages(
    //        @ApiParam
    //        @Valid
    //        SearchRefundApplicationDetailDepositReq req,
    //        @Valid
    //        PageInfo pageInfo
    //    ) {
    //        return service.getRefundApplicationDetailDepositPages(req, pageInfo);
    //    }
    //
    //    @ApiOperation(value = "환불 신청 상세 내역 상세입금 조회 엑셀 다운로드", notes = "환불 신청 상세 내역 상세입금 조회 엑셀 다운로드")
    //    @GetMapping("/detail/deposit/excel-download")
    //    public List<SearchRefundApplicationDetailDepositRes> getRefundApplicationDetailDepositExcels(
    //        SearchRefundApplicationDetailDepositReq req
    //    ) {
    //        return service.getRefundApplicationDetailDepositExcels(req);
    //    }
    //
    //    @ApiOperation(value = "환불 신청 상세 내역 매출실적조회", notes = "환불 신청 상세 내역 매출실적조회")
    //    @GetMapping("/detail/performance")
    //    public PagingResult<SearchRefundApplicationDetailPerformanceRes> getRefundApplicationDetailPerformancePages(
    //        @ApiParam
    //        @Valid
    //        SearchRefundApplicationDetailPerformanceReq req,
    //        @Valid
    //        PageInfo pageInfo
    //    ) {
    //        return service.getRefundApplicationDetailPerformancePages(req, pageInfo);
    //    }
    //
    //    @ApiOperation(value = "환불 신청 상세 내역 매출실적조회 엑셀 다운로드", notes = "환불 신청 상세 내역 매출실적조회 엑셀 다운로드")
    //    @GetMapping("/detail/performance/excel-download")
    //    public List<SearchRefundApplicationDetailPerformanceRes> getRefundApplicationDetailPerformanceExcels(
    //        SearchRefundApplicationDetailPerformanceReq req
    //    ) {
    //        return service.getRefundApplicationDetailPerformanceExcels(req);
    //    }
    //
    //    @ApiOperation(value = "환불 신청 상세 내역 환불접수총액 조회", notes = "환불 신청 상세 내역 환불접수총액 조회")
    //    @GetMapping("/detail/receipt")
    //    public List<SearchRefundApplicationDetailReceiptRes> getRefundApplicationDetailReceipt(
    //        @RequestParam
    //        String rfndRcpNo
    //    ) {
    //        return service.getRefundApplicationDetailReceipt(rfndRcpNo);
    //    }
    //    // TODO: 미사용데이터 END

    /* 재사용 */
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
    public List<SearchRefundApplicationConnectHistoryRes> getRefundApplicationConnectHistorysForExcelDownload(
        @RequestParam
        String cntrNo
    ) {
        return service.getRefundApplicationConnectHistorysForExcelDownload(cntrNo);
    }

    ////////////////////////////////////////////
    @ApiOperation(value = "환불 신청 환불계좌 유효성체크", notes = "환불 신청 환불계좌 유효성체크")
    @GetMapping("/bank-effective")
    public WwdaAutoTransferInterfaceDto.SearchBankEffectivenessCheckRes getRefundBankEffectivenessCheck(
        WwdaAutoTransferInterfaceDto.SearchBankEffectivenessCheckReq dto
    ) {
        return service.getBankEffectivenessCheck(dto);
    }

    /* TODO: 환불 승인 저장 */
    @ApiOperation(value = "환불 신청 승인 저장", notes = "환불 신청 팝업 승인 및 반려 처리를 한다.")
    @PostMapping("/reg/approval")
    public SaveResponse getRefundApprovalSave(
        @RequestBody @Valid
        SaveApprovalReq req
    ) throws Exception {
        System.out.println("=========================1===================");
        System.out.println("=========================1===================");
        return SaveResponse
            .builder()
            .processCount(service.getRefundApprovalSave(req))
            .build();
    }

}
