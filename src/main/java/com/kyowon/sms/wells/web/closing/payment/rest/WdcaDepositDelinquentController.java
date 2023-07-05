package com.kyowon.sms.wells.web.closing.payment.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDepositDelinquentDto.SearchReq;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDepositDelinquentDto.SearchRes;
import com.kyowon.sms.wells.web.closing.payment.service.WdcaDepositDelinquentService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[WDCA] 입금 연체 현황")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = DcClosingConst.COMMON_URL_V1 + "/deposit-delinquents")
public class WdcaDepositDelinquentController {
    private final WdcaDepositDelinquentService service;

    @ApiOperation(value = "입금 연체 현황", notes = "조회조건에 따른 입금 연체 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query"),
        @ApiImplicitParam(name = "copnDvCd", value = "개인/법인구분", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "sellChnl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getDepositDelinquents(
        @Valid
        SearchReq dto
    ) {
        return service.getDepositDelinquents(dto);
    }

    @ApiOperation(value = "입금 연체 현황", notes = "조회조건에 따른 입금 연체 현황을 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query"),
        @ApiImplicitParam(name = "copnDvCd", value = "개인/법인구분", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "sellChnl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getDepositDelinquentForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getDepositDelinquentForExcelDownload(dto);
    }
}
