package com.kyowon.sms.wells.web.closing.sales.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchSingleRes;
import com.kyowon.sms.wells.web.closing.sales.service.WdcbSalesDetailService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCB] 매출 상세정보(W-CL-U-0028P02)")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/sales-detail")
public class WdcbSalesDetailController {
    private final WdcbSalesDetailService service;

    /**
     * 매출 상세정보 조회
     * @param dto
     * @return
     */
    @ApiOperation(value = "매출 상세정보", notes = "조회조건에 따른 계약상세번호 별 매출상세정보를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "slRcogDt", value = "매출인식일자", paramType = "query"),
    })
    @GetMapping("/rental")
    public SearchRentalRes getSalesDetailRental(
        @Valid
        SearchReq dto
    ) {
        return service.getSalesDetailRental(dto);
    }

    /**
     * 매출 상세정보 조회
     * @param dto
     * @return
     */
    @ApiOperation(value = "매출 상세정보", notes = "조회조건에 따른 계약상세번호 별 매출상세정보를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "slRcogDt", value = "매출인식일자", paramType = "query"),
    })
    @GetMapping("/membership")
    public SearchMembershipRes getSalesDetailMembership(
        @Valid
        SearchReq dto
    ) {
        return service.getSalesDetailMembership(dto);
    }

    /**
     * 매출 상세정보 조회
     * @param dto
     * @return
     */
    @ApiOperation(value = "매출 상세정보", notes = "조회조건에 따른 계약상세번호 별 매출상세정보를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "slRcogDt", value = "매출인식일자", paramType = "query"),
    })
    @GetMapping("/single-payment")
    public SearchSingleRes getSalesDetailSinglePayment(
        @Valid
        SearchReq dto
    ) {
        return service.getSalesDetailSinglePayment(dto);
    }

}
