package com.kyowon.sms.wells.web.closing.sales.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbKmoneySalesBondDto.SearchBondRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbKmoneySalesBondDto.SearchCancelRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbKmoneySalesBondDto.SearchDepositRes;
import com.kyowon.sms.wells.web.closing.sales.service.WdcbKmoneySalesBondService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCB] K머니 매출채권 현황 ")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/kmoney-sales-bond")
public class WdcbKmoneySalesBondController {
    private final WdcbKmoneySalesBondService service;

    @ApiOperation(value = "매출채권 현황", notes = "조회조건에 따른 매출 채권 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYr", value = "기준년", paramType = "query"),
    })
    @GetMapping("/sales-bond")
    public PagingResult<SearchBondRes> getSalesBondPages(
        @RequestParam
        String baseYr,
        PageInfo pageinfo
    ) {
        return service.getSalesBondPages(baseYr, pageinfo);
    }

    @ApiOperation(value = "매출채권 현황 엑셀 다운로드", notes = "조회조건에 따른 매출채권 현황을 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYr", value = "기준년", paramType = "query"),
    })
    @GetMapping("/sales-bond/excel-download")
    public List<SearchBondRes> getSalesBondExcelDownload(
        @RequestParam
        String baseYr
    ) {
        return service.getSalesBondExcelDownload(baseYr);
    }

    @ApiOperation(value = "월별 입금 상세내역", notes = "조회조건에 따른 월별 입금 상세내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query"),
    })
    @GetMapping("/deposit-detail")
    public List<SearchDepositRes> getDepositDetails(
        @RequestParam
        String baseYm
    ) {
        return service.getDepositDetails(baseYm);
    }

    @ApiOperation(value = "월별 적립취소 상세내역", notes = "조회조건에 따른 월별 적립취소 상세내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query"),
    })
    @GetMapping("/cancel-detail")
    public List<SearchCancelRes> getCancelDetails(
        @RequestParam
        String baseYm
    ) {
        return service.getCancelDetails(baseYm);
    }
}
