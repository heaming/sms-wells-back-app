package com.kyowon.sms.wells.web.closing.sales.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchRentalAgrgRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchRentalPdRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchSingleAgrgRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchSinglePdRes;
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

    @ApiOperation(value = "상품별 매출 현황(일시불, 리스, 정기배송/집계)", notes = "조회조건에 따른 상품별 매출 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtmnFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtmnTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "taskDiv", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTp", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellDv", value = "판매구분", paramType = "query"),
    })
    @GetMapping("/single-payment-aggregates")
    public List<SearchSingleAgrgRes> getSinglePaymentAgrgs(
        @Valid
        SearchReq dto
    ) {
        return service.getSinglePaymentAgrgs(dto);
    }

    @ApiOperation(value = "상품별 매출 현황(일시불, 리스, 정기배송/상품)", notes = "조회조건에 따른 상품별 매출 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtmnFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtmnTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "taskDiv", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTp", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellDv", value = "판매구분", paramType = "query"),
    })
    @GetMapping("/single-payment-products")
    public List<SearchSinglePdRes> getSinglePaymentProducts(
        @Valid
        SearchReq dto
    ) {
        return service.getSinglePaymentProducts(dto);
    }

    @ApiOperation(value = "상품별 매출 현황(렌탈/집계)", notes = "조회조건에 따른 상품별 매출 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtmnFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtmnTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "taskDiv", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTp", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellDv", value = "판매구분", paramType = "query"),
    })
    @GetMapping("/rental-aggregates")
    public List<SearchRentalAgrgRes> getRentalAgrgs(
        @Valid
        SearchReq dto
    ) {
        return service.getRentalAgrgs(dto);
    }

    @ApiOperation(value = "상품별 매출 현황(렌탈/상품)", notes = "조회조건에 따른 상품별 매출 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtmnFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtmnTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "taskDiv", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTp", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellDv", value = "판매구분", paramType = "query"),
    })
    @GetMapping("/rental-products")
    public List<SearchRentalPdRes> getRentalProducts(
        @Valid
        SearchReq dto
    ) {
        return service.getRentalProducts(dto);
    }

    @ApiOperation(value = "상품별 매출 현황(멤버십)", notes = "조회조건에 따른 상품별 매출 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtmnFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtmnTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "taskDiv", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTp", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellDv", value = "판매구분", paramType = "query"),
    })
    @GetMapping("/memberships")
    public List<SearchMembershipRes> getMemberships(
        @Valid
        SearchReq dto
    ) {
        return service.getMemberships(dto);
    }
}
