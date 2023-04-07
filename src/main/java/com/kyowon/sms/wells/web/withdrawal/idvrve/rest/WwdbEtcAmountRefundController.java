package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAmountRefundDto.SearchEtcAmountRefundAggregateReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAmountRefundDto.SearchEtcAmountRefundAggregateRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAmountRefundDto.SearchEtcAmountRefundReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAmountRefundDto.SearchEtcAmountRefundRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbEtcAmountRefundService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[입금관리 - 개별수납] 기타 선수금 환불 목록")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/etc-amount-refunds")
public class WwdbEtcAmountRefundController {

    private final WwdbEtcAmountRefundService service;

    @ApiOperation(value = "기타 선수금 환불 목록 조회", notes = "기타 선수금 환불 목록 조회")
    @GetMapping("/paging")
    public PagingResult<SearchEtcAmountRefundRes> getEtcAmountRefundPages(
        @ApiParam
        @Valid
        SearchEtcAmountRefundReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getEtcAmountRefundPages(req, pageInfo);
    }

    @ApiOperation(value = "기타 선수금 환불 목록 조회", notes = "기타 선수금 환불 엑셀 다운로드")
    @GetMapping("/excel-download")
    public List<SearchEtcAmountRefundRes> getEtcAmountRefundExcels(
        SearchEtcAmountRefundReq req
    ) {
        return service.getEtcAmountRefundExcels(req);
    }

    @ApiOperation(value = "기타 선수금 환불 집계", notes = "기타 선수금 환불 집계")
    @GetMapping("/aggregate")
    public SearchEtcAmountRefundAggregateRes getEtcAmountRefundAggregates(
        SearchEtcAmountRefundAggregateReq req
    ) {
        return service.getEtcAmountRefundAggregates(req);
    }
}
