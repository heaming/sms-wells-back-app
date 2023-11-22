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

    /**
     * 환불 신청 현황 목록 조회 / 페이징
     * @param req 환불 신청 현황 목록 조회 DTO
     * @param pageInfo 페이징
     * @return PagingResult<SearchRefundApplicationRes>
     */
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

    /**
     * 환불 신청 현황 목록 조회 / 엑셀 다운로드
     * @param req 환불 신청 현황 목록 조회 DTO
     * @return ist<SearchRefundApplicationRes>
     */
    @ApiOperation(value = "환불 신청 현황 목록 엑셀 다운로드", notes = "환불 신청 현황 목록 엑셀 다운로드")
    @GetMapping("/excel-download")
    public List<SearchRefundApplicationRes> getRefundApplicationExcels(
        SearchRefundApplicationReq req
    ) {
        return service.getRefundApplicationExcels(req);
    }

    /**
     * 환불 신청 팝업 (환불기본)
     * @param req 환불 신청 기본 DTO
     * @return SearchRefundRes
     */
    @ApiOperation(value = "환불 신청 팝업 (환불기본)", notes = "환불 신청 팝업 (환불기본)")
    @GetMapping("/reg/refund")
    public SearchRefundRes getRefundApplication(
        SearchRefundReq req
    ) {
        return service.getRefundApplication(req);
    }

    /**
     * 환불상세 목록 조회
     * @param req 환불상세 목록 조회 DTO
     * @return List<SearchRefundDetailRes>
     */
    @ApiOperation(value = "환불상세 목록 조회", notes = "환불상세 목록 조회")
    @GetMapping("/reg/refund-detail")
    public List<SearchRefundDetailRes> getRefundDetailPages(
        @ApiParam @Valid
        SearchRefundDetailReq req
    ) {
        return service.getRefundDetailPages(req);
    }

    /**
     * 환불신청현황 화면 - 계약 상세 목록 조회
     * @param req 계약 상세 목록 조회 DTO
     * @return List<SearchRefundContractDetailRes>
     */
    @ApiOperation(value = "환불 신청 팝업 (계약상세)", notes = "환불 신청 팝업 (계약상세) 목록 조회")
    @GetMapping("/reg/paging")
    public List<SearchRefundContractDetailRes> getRefundContractDetailPages(
        @ApiParam @Valid
        SearchRefundContractDetailReq req
    ) {
        return service.getRefundContractDetailPages(req);
    }

    /**
     * 환불신청현황 화면 - 계약 상세 목록 조회 / 엑셀 다운로드
     * @param req 계약 상세 목록 조회 DTO
     * @return List<SearchRefundContractDetailRes>
     */
    @ApiOperation(value = "환불 신청 팝업 (계약상세) 목록 엑셀 다운로드", notes = "환불 신청 팝업 (계약상세) 목록 엑셀 다운로드")
    @GetMapping("/reg/excel-download")
    public List<SearchRefundContractDetailRes> getRefundContractDetailExcels(
        SearchRefundContractDetailReq req
    ) {
        return service.getRefundContractDetailExcels(req);
    }

    /**
     * 환불 신청 팝업 저장
     * @param req 환불 신청 팝업 저장 DTO
     * @return SaveResponse
     * @throws Exception
     */
    @ApiOperation(value = "환불 신청 팝업 저장", notes = "환불 신청 팝업 저장")
    @PostMapping("/reg/save")
    public SaveResponse getRefundTempSave(
        @RequestBody @Valid
        SaveReq req
    ) throws Exception {
        return SaveResponse
            .builder()
            .processCount(service.getRefundTempSave(req))
            .build();
    }

    /**
     * 환불 신청 팝업 삭제
     * @param req 환불 신청 팝업 삭제 DTO
     * @return SaveResponse
     * @throws Exception
     */
    @ApiOperation(value = "환불 신청 팝업 삭제", notes = "환불 신청 팝업 삭제")
    @DeleteMapping("/reg/delete")
    public SaveResponse getRefundDelete(
        @Valid
        removeReq req
    ) throws Exception {
        return SaveResponse
            .builder()
            .processCount(service.getRefundDelete(req))
            .build();
    }
    /* 환불저장 END */

    /**
     * 환불 신청 팝업 (계약상세) 목록 조회 / 페이징
     * @param req 계약 상세 목록 조회 DTO
     * @param pageInfo 페이징
     * @return PagingResult<SearchRefundBaseRes>
     */
    /* 메인그리드에서 호출 */
    /* 환불신청팝업 - 계약상세(메인에서조회) */
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

    /**
     * 전금 상세 목록 조회
     * @param req 전금 상세 목록 조회 DTO
     * @return List<SearchRefundBalanceTransferRes>
     */
    /* 환불신청팝업 - 전금상세(메인에서조회) */
    @ApiOperation(value = "환불 신청 팝업 (전금상세)", notes = "환불 신청 팝업 (전금상세) 목록 조회")
    @GetMapping("/reg/balance-transfer")
    public List<SearchRefundBalanceTransferRes> getRefundBalanceTransferPages(
        @ApiParam @Valid
        SearchRefundBalanceTransferReq req
    ) {
        return service.getRefundBalanceTransferPages(req);
    }

    /**
     * 환불 신청 컨텍 이력 사항 조회 / 페이징
     * @param cntrNo 계약 번호
     * @param pageInfo 페이징
     * @return PagingResult<SearchRefundApplicationConnectHistoryRes>
     */
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

    /**
     * 환불 신청 컨텍 이력 사항 조회 / 엑셀 다운로드
     * @param cntrNo 계약 번호
     * @return List<SearchRefundApplicationConnectHistoryRes>
     */
    @ApiOperation(value = "환불 신청 컨텍 이력 사항 엑셀 다운로드", notes = "환불 신청 컨텍 이력 사항 엑셀 다운로드")
    @GetMapping("/connect-history/excel-download")
    public List<SearchRefundApplicationConnectHistoryRes> getRefundApplicationConnectHistorysForExcelDownload(
        @RequestParam
        String cntrNo
    ) {
        return service.getRefundApplicationConnectHistorysForExcelDownload(cntrNo);
    }

    /**
     * 환불 신청 환불 계좌 유효성 체크
     * @param dto 환불 계좌 유효성 체크 DTO
     * @return SearchBankEffectivenessCheckRes
     */
    @ApiOperation(value = "환불 신청 환불계좌 유효성체크", notes = "환불 신청 환불계좌 유효성체크")
    @GetMapping("/bank-effective")
    public WwdaAutoTransferInterfaceDto.SearchBankEffectivenessCheckRes getRefundBankEffectivenessCheck(
        WwdaAutoTransferInterfaceDto.SearchBankEffectivenessCheckReq dto
    ) {
        return service.getBankEffectivenessCheck(dto);
    }

    /**
     * 환불 승인 저장 및 반려 처리
     * @param req 환불 승인 저장 DTO
     * @return SaveResponse
     * @throws Exception
     */
    @ApiOperation(value = "환불 신청 승인 저장", notes = "환불 신청 팝업 승인 및 반려 처리를 한다.")
    @PostMapping("/reg/approval")
    public SaveResponse getRefundApprovalSave(
        @RequestBody @Valid
        SaveApprovalReq req
    ) throws Exception {
        return SaveResponse
            .builder()
            .processCount(service.getRefundApprovalSave(req))
            .build();
    }

}
