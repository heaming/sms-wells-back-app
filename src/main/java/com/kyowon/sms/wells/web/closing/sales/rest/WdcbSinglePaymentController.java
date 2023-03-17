package com.kyowon.sms.wells.web.closing.sales.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSinglePaymentDto.BaseSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSinglePaymentDto.DepositSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSinglePaymentDto.SalesSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSinglePaymentDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.service.WdcbSinglePaymentService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCB] 매출 실적 현황 - 일시불 ")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1)
public class WdcbSinglePaymentController {
    private final WdcbSinglePaymentService service;

    @ApiOperation(value = "매출 실적 현황 - 일시불(기본정보)", notes = "조회조건에 따른 매출 실적 현황 - 일시불을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약상세번호", paramType = "query"),
    })
    @GetMapping("/single-payment-base")
    public BaseSearchRes getBaseInformation(
        @Valid
        SearchReq dto
    ) {
        return service.getBaseInformation(dto);
    }

    @ApiOperation(value = "매출 실적 현황 - 일시불(매출실적)", notes = "조회조건에 따른 매출 실적 현황 - 일시불을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약상세번호", paramType = "query"),
    })
    @GetMapping("/single-payment-sales")
    public PagingResult<SalesSearchRes> getSalesPerformancePages(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getSalesPerformances(dto, pageInfo);
    }

    @ApiOperation(value = "매출 실적 현황 - 일시불(입금내역)", notes = "조회조건에 따른 매출 실적 현황 - 일시불을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약상세번호", paramType = "query"),
    })
    @GetMapping("/single-payment-deposits")
    public PagingResult<DepositSearchRes> getDepositItemizationPages(
        @Valid
        SearchReq dto,
        PageInfo depositPageInfo
    ) {
        return service.getDepositItemizations(dto, depositPageInfo);
    }

    @ApiOperation(value = "매출 실적 현황 - 일시불(매출실적) 엑셀 다운로드", notes = "조회조건에 따른 매출 실적 - 일시불을 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약상세번호", paramType = "query"),
    })
    @GetMapping("/single-payment-sales/excel-download")
    public List<SalesSearchRes> getSalesPerformanceExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getSalesPerformanceExcelDownload(dto);
    }

    @ApiOperation(value = "매출 실적 현황 - 일시불(입금내역) 엑셀 다운로드", notes = "조회조건에 따른 매출 실적 - 일시불을 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약상세번호", paramType = "query"),
    })
    @GetMapping("/single-payment-deposits/excel-download")
    public List<DepositSearchRes> getDepositItemizationExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getDepositItemizationExcelDownload(dto);
    }
}
