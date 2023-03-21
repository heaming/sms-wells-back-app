package com.kyowon.sms.wells.web.closing.sales.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.LeaseSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.MembershipSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.service.WdcbSalesPerformanceDetailService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCB] 매출 실적 현황 - 상세내역 ")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1)
public class WdcbSalesPerformanceDetailController {
    private final WdcbSalesPerformanceDetailService service;

    @ApiOperation(value = "매출 실적 현황 - 멤버십매출 상세내역", notes = "조회조건에 따른 멤버십매출 상세내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slDt", value = "매출년월", paramType = "query"),
    })
    @GetMapping("/membership-sales-detail")
    public MembershipSearchRes getMembershipSalesDetail(
        @Valid
        SearchReq dto
    ) {
        return service.getMembershipSalesDetail(dto);
    }

    @ApiOperation(value = "매출 실적 현황 - 리스매출 상세내역", notes = "조회조건에 따른 리스매출 상세내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slDt", value = "매출년월", paramType = "query"),
    })
    @GetMapping("/lease-sales-detail")
    public LeaseSearchRes getLeaseSalesDetail(
        @Valid
        SearchReq dto
    ) {
        return service.getLeaseSalesDetail(dto);
    }
}
