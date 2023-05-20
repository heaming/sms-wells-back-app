package com.kyowon.sms.wells.web.closing.sales.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchLeaseRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchRegularRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchRentalRes;
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
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "slDt", value = "매출년월", paramType = "query"),
    })
    @GetMapping("/membership-sales-detail")
    public SearchMembershipRes getMembershipSalesDetail(
        @RequestParam
        String cntrDtlNo,
        @RequestParam
        String slClYm
    ) {
        return service.getMembershipSalesDetail(cntrDtlNo, slClYm);
    }

    @ApiOperation(value = "매출 실적 현황 - 리스매출 상세내역", notes = "조회조건에 따른 리스매출 상세내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "slDt", value = "매출년월", paramType = "query"),
    })
    @GetMapping("/lease-sales-detail")
    public SearchLeaseRes getLeaseSalesDetail(
        @RequestParam
        String cntrDtlNo,
        @RequestParam
        String slClYm
    ) {
        return service.getLeaseSalesDetail(cntrDtlNo, slClYm);
    }

    @ApiOperation(value = "매출 실적 현황 - 렌탈매출 상세내역", notes = "조회조건에 따른 렌탈매출 상세내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "slClYm", value = "매출년월", paramType = "query"),
    })
    @GetMapping("/rental-sales-detail")
    public SearchRentalRes getRentalSalesDetail(
        @RequestParam
        String cntrDtlNo,
        @RequestParam
        String slClYm
    ) {
        return service.getRentalSalesDetail(cntrDtlNo, slClYm);
    }

    @ApiOperation(value = "매출 실적 현황 - 정기배송매출 상세내역", notes = "조회조건에 따른 렌탈매출 상세내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "slDt", value = "매출년월", paramType = "query"),
    })
    @GetMapping("/regular-shipping-detail")
    public SearchRegularRes getRegularShippingDetail(
        @RequestParam
        String cntrDtlNo,
        @RequestParam
        String slClYm
    ) {
        return service.getRegularShippingDetail(cntrDtlNo, slClYm);
    }
}
