package com.kyowon.sms.wells.web.closing.performance.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchExcelRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchProductRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchTotalRes;
import com.kyowon.sms.wells.web.closing.performance.service.WdccProductAccountService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCC] 상품별 계정 현황")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/product-account")
public class WdccProductAccountController {
    private final WdccProductAccountService service;

    @ApiOperation(value = "상품별 계정 현황(집계)", notes = "조회조건에 따른 상품별 계정 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYmFrom", value = "기준년월시작", paramType = "query"),
        @ApiImplicitParam(name = "baseYmTo", value = "기준년월종료", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query"),
        @ApiImplicitParam(name = "prdtCateHigh", value = "상품대분류", paramType = "query"),
        @ApiImplicitParam(name = "prdtCateMid", value = "상품중분류", paramType = "query"),
    })
    @GetMapping("/total")
    public List<SearchTotalRes> getProductAccountTotals(
        @Valid
        SearchReq dto
    ) {
        return service.getProductAccountTotals(dto);
    }

    @ApiOperation(value = "상품별 계정 현황(상품)", notes = "조회조건에 따른 상품별 계정 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYmFrom", value = "기준년월시작", paramType = "query"),
        @ApiImplicitParam(name = "baseYmTo", value = "기준년월종료", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query"),
        @ApiImplicitParam(name = "prdtCateHigh", value = "상품대분류", paramType = "query"),
        @ApiImplicitParam(name = "prdtCateMid", value = "상품중분류", paramType = "query"),
    })
    @GetMapping("/product")
    public List<SearchProductRes> getProductAccounts(
        @Valid
        SearchReq dto
    ) {
        return service.getProductAccounts(dto);
    }

    /** TODO : 엑셀 다운로드 컬럼 아직 정의가 안됨 */
    @ApiOperation(value = "상품별 계정 현황 상세내역 다운로드", notes = "조회조건에 따른 상품별 계정 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYmFrom", value = "기준년월시작", paramType = "query"),
        @ApiImplicitParam(name = "baseYmTo", value = "기준년월종료", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query"),
        @ApiImplicitParam(name = "prdtCateHigh", value = "상품대분류", paramType = "query"),
        @ApiImplicitParam(name = "prdtCateMid", value = "상품중분류", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchExcelRes> getProductAccountsExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getProductAccountsExcelDownload(dto);
    }
}
