package com.kyowon.sms.wells.web.closing.sales.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbPrepaidDepositRentalDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbPrepaidDepositRentalDto.SearchRes;
import com.kyowon.sms.wells.web.closing.sales.service.WdcbPrepaidDepositRentalService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCB] 매출 실적 현황 - 선입금 렌탈 조회 ")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/prepaid-deposit-rental")
public class WdcbPrepaidDepositRentalController {
    private final WdcbPrepaidDepositRentalService service;

    @ApiOperation(value = "매출 실적 현황 - 선입금 렌탈 조회", notes = "조회조건에 따른 선입금 렌탈 내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "slClYmFrom", value = "조회년월시작", paramType = "query"),
        @ApiImplicitParam(name = "slClYmTo", value = "조회년월종료", paramType = "query"),
    })
    @GetMapping
    public SearchRes getPrepaidDepositRental(
        @Valid
        SearchReq dto
    ) {
        return service.getPrepaidDepositRental(dto);
    }
}
