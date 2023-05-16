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

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.EditRefundReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SaveRefundReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchBankRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchCardRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationInfoRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundContractDetailReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundContractDetailRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundPossibleAmountReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundPossibleAmountRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbRefundApplicationService;
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

}
