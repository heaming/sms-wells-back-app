package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbContractRefundDto.*;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbContractRefundService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[입금관리 - 개별수납] 계약금 환불 목록")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/contract-refunds")
public class WwdbContractRefundController {

    private final WwdbContractRefundService service;

    @ApiOperation(value = "계약금 환불현황 목록", notes = "계약금 환불현황 목록")
    @GetMapping("/paging")
    public PagingResult<SearchContractRefundRes> getContractRefundPages(
        @ApiParam
        @Valid
        SearchContractRefundReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getContractRefundPages(req, pageInfo);
    }

    @ApiOperation(value = "계약금 환불현황 엑셀 다운로드", notes = "웰스 환불 목록(카드사별) 엑셀 다운로드")
    @GetMapping("/excel-download")
    public List<SearchContractRefundRes> getContractRefundsExcels(
        SearchContractRefundReq req
    ) {
        return service.getContractRefundExcels(req);
    }

    @ApiOperation(value = "계약금 환불 집계", notes = "계약금 환불 집계")
    @GetMapping("/aggregate")
    public SearchContractRefundAggregateRes getContractRefundAggregates(
        SearchContractRefundAggregateReq req
    ) {
        return service.getContractRefundAggregates(req);
    }

    @ApiOperation(value = "계약금 환불 목록 Summary", notes = "계약금 환불 목록의 값을 합하여 출력합니다.")
    @GetMapping("/summary")
    public SearchContractRefundSummaryRes getContractRefundSummary(
        @ApiParam
        @Valid
        SearchContractRefundReq req
    ) {
        return service.getContractRefundSummary(req);
    }

}
