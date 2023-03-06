package com.kyowon.sms.wells.web.closing.sales.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.MembershipSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.RentalSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SingleSearchRes;
import com.kyowon.sms.wells.web.closing.sales.service.WdcbProductSalesService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCB] 상품별 매출 현황")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/product-sales")
public class WdcbProductSalesController {
    private final WdcbProductSalesService service;

    @ApiOperation(value = "상품별 매출 현황", notes = "조회조건에 따른 상품별 매출 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtmnFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtmnTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "taskDiv", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTp", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellDv", value = "판매구분", paramType = "query"),
    })
    @GetMapping("/single-payment")
    public List<SingleSearchRes> getProductSalesSinglePayment(
        @Valid
        SearchReq dto
    ) {
        return service.getProductSalesSinglePayment(dto);
    }

    @ApiOperation(value = "상품별 매출 현황", notes = "조회조건에 따른 상품별 매출 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtmnFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtmnTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "taskDiv", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTp", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellDv", value = "판매구분", paramType = "query"),
    })
    @GetMapping("/rental")
    public List<RentalSearchRes> getProductSalesRental(
        @Valid
        SearchReq dto
    ) {
        return service.getProductSalesRental(dto);
    }

    @ApiOperation(value = "상품별 매출 현황", notes = "조회조건에 따른 상품별 매출 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtmnFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtmnTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "taskDiv", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTp", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellDv", value = "판매구분", paramType = "query"),
    })
    @GetMapping("/membership")
    public List<MembershipSearchRes> getProductSalesMembership(
        @Valid
        SearchReq dto
    ) {
        return service.getProductSalesMembership(dto);
    }

    @ApiOperation(value = "상품별 매출 현황 엑셀 다운로드", notes = "조회조건에 따른 상품별 매출 목록을 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtmnFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtmnTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "taskDiv", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTp", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellDv", value = "판매구분", paramType = "query"),
    })
    @GetMapping("/single-payment/excel-download")
    public List<SingleSearchRes> getProductSalesSinglePaymentExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getProductSalesSinglePayment(dto);
    }

    @ApiOperation(value = "상품별 매출 현황 엑셀 다운로드", notes = "조회조건에 따른 상품별 매출 목록을 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtmnFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtmnTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "taskDiv", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTp", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellDv", value = "판매구분", paramType = "query"),
    })
    @GetMapping("/rental/excel-download")
    public List<RentalSearchRes> getProductSalesRentalExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getProductSalesRental(dto);
    }

    @ApiOperation(value = "상품별 매출 현황 엑셀 다운로드", notes = "조회조건에 따른 상품별 매출 목록을 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtmnFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtmnTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "taskDiv", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTp", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellDv", value = "판매구분", paramType = "query"),
    })
    @GetMapping("/membership/excel-download")
    public List<MembershipSearchRes> getProductSalesMembershipExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getProductSalesMembership(dto);
    }
}
