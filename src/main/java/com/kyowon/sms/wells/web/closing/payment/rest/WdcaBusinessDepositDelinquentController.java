package com.kyowon.sms.wells.web.closing.payment.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.payment.dto.WdcaBusinessDepositDelinquentDto.SearchReq;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaBusinessDepositDelinquentDto.SearchRes;
import com.kyowon.sms.wells.web.closing.payment.service.WdcaBusinessDepositDelinquentService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCA] 영업부 입금/연체 현황")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/business-deposit-delinquents")
public class WdcaBusinessDepositDelinquentController {

    private final WdcaBusinessDepositDelinquentService service;

    @ApiOperation(value = "영업부 입금/연체 현황 조회", notes = "조회조건에 따른 영업부 입금/연체 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgCd", value = "조직레벨-총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgCd", value = "조직레벨-지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgCd", value = "조직레벨-지점", paramType = "query"),
    })
    @GetMapping()
    public PagingResult<SearchRes> getBusinessDepositDelinquentPages(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getBusinessDepositDelinquentPages(dto, pageInfo);
    }

    @ApiOperation(value = "영업부 입금/연체 현황 조회 엑셀다운로드", notes = "조회조건에 따른 영업부 입금/연체 현황을 조회하여 엑셀다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgCd", value = "조직레벨-총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgCd", value = "조직레벨-지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgCd", value = "조직레벨-지점", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getBusinessDepositDelinquentForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getBusinessDepositDelinquentForExcelDownload(dto);
    }
}
