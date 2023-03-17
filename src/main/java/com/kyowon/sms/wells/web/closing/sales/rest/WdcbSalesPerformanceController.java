package com.kyowon.sms.wells.web.closing.sales.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.BaseSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SalesSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.service.WdcbSalesPerformanceService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCB] 매출 실적 현황 ")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/sales-performance")
public class WdcbSalesPerformanceController {
    private final WdcbSalesPerformanceService service;

    @ApiOperation(value = "매출 실적 현황 - 기본정보", notes = "조회조건에 따른 매출 실적 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrDtlNo", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "baseYearFrom", value = "매출시작년도", paramType = "query"),
        @ApiImplicitParam(name = "baseYearTo", value = "매출종료년도", paramType = "query"),
    })
    @GetMapping("/base-information")
    public BaseSearchRes getBaseInformation(
        @Valid
        SearchReq dto
    ) {
        return service.getBaseInformation(dto);
    }

    @ApiOperation(value = "매출 실적 현황 - 매출실적(월별)", notes = "조회조건에 따른 매출 실적 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrDtlNo", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "baseYearFrom", value = "매출시작년도", paramType = "query"),
        @ApiImplicitParam(name = "baseYearTo", value = "매출종료년도", paramType = "query"),
    })
    @GetMapping("/sales-performance-mcby/paging")
    public PagingResult<SalesSearchRes> getSalesPerformancePages(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getSalesPerformancePages(dto, pageInfo);
    }

    @ApiOperation(value = "매출 실적 현황 - 매출실적(월별) 엑셀 다운로드", notes = "조회조건에 따른 매출 실적을 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrDtlNo", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "baseYearFrom", value = "매출시작년도", paramType = "query"),
        @ApiImplicitParam(name = "baseYearTo", value = "매출종료년도", paramType = "query"),
    })
    @GetMapping("/sales-performance-mcby/excel-download")
    public List<SalesSearchRes> getSalesPerformanceExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getSalesPerformanceExcelDownload(dto);
    }
}
