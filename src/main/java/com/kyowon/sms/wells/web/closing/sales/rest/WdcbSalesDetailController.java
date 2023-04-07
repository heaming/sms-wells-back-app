package com.kyowon.sms.wells.web.closing.sales.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchSingleRes;
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
    })
    @GetMapping("/rental")
    public SearchRentalRes getSalesDetailRental(
        @RequestParam
        String cntrDtlNo
    ) {
        return service.getSalesDetailRental(cntrDtlNo);
    }

    @ApiOperation(value = "매출 상세정보", notes = "조회조건에 따른 계약상세번호 별 매출상세정보를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약상세번호", paramType = "query", required = true),
    })
    @GetMapping("/membership")
    public SearchMembershipRes getSalesDetailMembership(
        @RequestParam
        String cntrDtlNo
    ) {
        return service.getSalesDetailMembership(cntrDtlNo);
    }

    @ApiOperation(value = "매출 상세정보", notes = "조회조건에 따른 계약상세번호 별 매출상세정보를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약상세번호", paramType = "query", required = true),
    })
    @GetMapping("/single-payment")
    public SearchSingleRes getSalesDetailSinglePayment(
        @RequestParam
        String cntrDtlNo
    ) {
        return service.getSalesDetailSinglePayment(cntrDtlNo);
    }

}
