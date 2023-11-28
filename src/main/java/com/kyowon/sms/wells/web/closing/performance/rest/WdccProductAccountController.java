package com.kyowon.sms.wells.web.closing.performance.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchProductRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchTotalRes;
import com.kyowon.sms.wells.web.closing.performance.service.WdccProductAccountService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.common.common.dto.ExcelBulkDownloadDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCC] 상품별 계정 현황 - W-CL-U-0032M01")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/product-account")
public class WdccProductAccountController {
    private final WdccProductAccountService service;

    /**
     * 상품별 계정 현황(집계)
     * @param dto
     * @return
     */
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

    /**
     * 상품별 계정 현황(상품)
     * @param dto
     * @return
     */
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

    /**
     * 상품별 계정 현황 상세내역 다운로드
     * @param req
     * @return
     */
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
    @PostMapping("/bulk-excel-download")
    public void getProductAccountsExcelDownload(
        @RequestBody
        ExcelBulkDownloadDto.DownloadReq req,
        HttpServletResponse response
    ) throws IOException {
        service.getProductAccountsExcelDownload(req, response);
    }

    /**
     * 상품별 계정 현황 상세내역 파일 생성
     * @param dto
     * @return
     */
    @ApiOperation(value = "상품별 계정 현황 상세내역 파일 생성", notes = "상품별 계정 현황 상세내역 파일 생성")
    @PostMapping("/make-file")
    public String createdetailItemizationFile(
        @Valid
        @RequestBody
        SearchReq dto
    ) throws Exception {
        return service.createdetailItemizationFile(dto);
    }

}
