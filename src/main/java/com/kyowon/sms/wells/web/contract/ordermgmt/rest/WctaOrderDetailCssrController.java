package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailCssrDto.FindBaseRcpRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailCssrDto.SearchRcpRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaOrderDetailCssrService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 주문상세페이지 내부 팝업_현금영수증")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts")
public class WctaOrderDetailCssrController {

    private final WctaOrderDetailCssrService service;

    @ApiOperation(value = "주문상세페이지 내부 팝업 현금영수증 조회(기본발행정보)", notes = "계약상세번호의 현금영수증 기본발행정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/order-details/cash-sales-receipts")
    public FindBaseRcpRes getContractBaseInformation(
        @RequestParam
        String cntrNo,
        @RequestParam
        String cntrSn
    ) {
        return service.getContractBaseInformation(cntrNo, cntrSn);
    }

    @ApiOperation(value = "주문상세페이지 내부 팝업 현금영수증 조회(기본발행정보)", notes = "계약상세번호의 현금영수증 기본발행정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/order-details/cash-sales-receipt-pbls")
    public List<SearchRcpRes> getCashSalesReceipts(
        @RequestParam
        String cntrNo,
        @RequestParam
        String cntrSn
    ) {
        return service.getCashSalesReceipts(cntrNo, cntrSn);
    }
}
