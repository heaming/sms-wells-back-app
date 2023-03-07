package com.kyowon.sms.wells.web.closing.sales.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.MembershipSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.RentalSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SingleSearchRes;
import com.kyowon.sms.wells.web.closing.sales.service.WdcbSalesDetailService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCB] 매출 상세정보")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/sales-detail")
public class WdcbSalesDetailController {
    private final WdcbSalesDetailService service;

    @ApiOperation(value = "매출 상세정보", notes = "조회조건에 따른 계약상세번호 별 매출상세정보를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약상세번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "taskDiv", value = "업무구분", paramType = "query", required = true),
    })
    @GetMapping("/rental")
    public RentalSearchRes getSalesDetailRental(
        @Valid
        SearchReq dto
    ) {
        return service.getSalesDetailRental(dto);
    }

    @ApiOperation(value = "매출 상세정보", notes = "조회조건에 따른 계약상세번호 별 매출상세정보를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약상세번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "taskDiv", value = "업무구분", paramType = "query", required = true),
    })
    @GetMapping("/membership")
    public MembershipSearchRes getSalesDetailMembership(
        @Valid
        SearchReq dto
    ) {
        return service.getSalesDetailMembership(dto);
    }

    @ApiOperation(value = "매출 상세정보", notes = "조회조건에 따른 계약상세번호 별 매출상세정보를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약상세번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "taskDiv", value = "업무구분", paramType = "query", required = true),
    })
    @GetMapping("/single-payment")
    public SingleSearchRes getSalesDetailSinglePayment(
        @Valid
        SearchReq dto
    ) {
        return service.getSalesDetailSinglePayment(dto);
    }

}
