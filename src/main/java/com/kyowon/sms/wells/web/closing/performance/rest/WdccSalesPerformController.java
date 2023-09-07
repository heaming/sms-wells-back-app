package com.kyowon.sms.wells.web.closing.performance.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesPerformDto.*;
import com.kyowon.sms.wells.web.closing.performance.service.WdccSalesPerformService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WDCC] 매출실적현황")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/sales-performs")
public class WdccSalesPerformController {

    private final WdccSalesPerformService service;

    @ApiOperation(value = "매출실적현황", notes = "매출실적현황")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "fromSlClYy", value = "검색시작일", paramType = "query"),
        @ApiImplicitParam(name = "toSlClYy", value = "검색종료일", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getSalesPerformancePresentStatePages(
        @Valid
        SearchReq req,
        PageInfo pageInfo
    ) {
        return service.getSalesPerformancePresentStatePages(req, pageInfo);
    }

    @ApiOperation(value = "매출실적현황", notes = "매출실적현황")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "fromSlClYy", value = "검색시작일", paramType = "query"),
        @ApiImplicitParam(name = "toSlClYy", value = "검색종료일", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getSalesPerformancePresentStateForExcelDownload(
        @Valid
        SearchReq req
    ) {
        return service.getSalesPerformancePresentStateForExcelDownload(req);
    }

    @ApiOperation(value = "매출 실적 현황 - 일시불(기본정보)", notes = "조회조건에 따른 매출 실적 현황 - 일시불을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/single-payment/base")
    public SearchSinglePaymentBaseRes getSinglePaymentBaseInfo(
        @Valid
        SearchSinglePaymentReq dto
    ) {
        return service.getSinglePaymentBaseInfo(dto);
    }

    @ApiOperation(value = "매출 실적 현황 - 일시불(매출실적)", notes = "조회조건에 따른 매출 실적 현황 - 일시불을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/single-payment/sales/paging")
    public PagingResult<SearchSinglePaymentSalesRes> getSinglePaymentSalesPaging(
        @Valid
        SearchSinglePaymentReq dto,
        PageInfo pageInfo
    ) {
        return service.getSinglePaymentSalesPaging(dto, pageInfo);
    }

    @ApiOperation(value = "매출 실적 현황 - 일시불(입금내역)", notes = "조회조건에 따른 매출 실적 현황 - 일시불을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/single-payment/deposits/paging")
    public PagingResult<SearchSinglePaymentDepositsRes> getSinglePaymentDepositsPaging(
        @Valid
        SearchSinglePaymentReq dto,
        PageInfo depositPageInfo
    ) {
        return service.getSinglePaymentDepositsPaging(dto, depositPageInfo);
    }

    @ApiOperation(value = "매출 실적 현황 - 일시불(매출실적) 엑셀 다운로드", notes = "조회조건에 따른 매출 실적 - 일시불을 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/single-payment/sales/excel-download")
    public List<SearchSinglePaymentSalesRes> getSinglePaymentSalesExcelDownload(
        @Valid
        SearchSinglePaymentReq dto
    ) {
        return service.getSinglePaymentSalesExcelDownload(dto);
    }

    @ApiOperation(value = "매출 실적 현황 - 일시불(입금내역) 엑셀 다운로드", notes = "조회조건에 따른 매출 실적 - 일시불을 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/single-payment/deposits/excel-download")
    public List<SearchSinglePaymentDepositsRes> getSinglePaymentDepositsExcelDownload(
        @Valid
        SearchSinglePaymentReq dto
    ) {
        return service.getSinglePaymentDepositsExcelDownload(dto);
    }

}
