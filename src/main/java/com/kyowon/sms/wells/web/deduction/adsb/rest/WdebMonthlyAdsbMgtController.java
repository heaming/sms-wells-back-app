package com.kyowon.sms.wells.web.deduction.adsb.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.deduction.adsb.dto.WdebMonthlyAdsbMgtDto.SearchContractRes;
import com.kyowon.sms.wells.web.deduction.adsb.dto.WdebMonthlyAdsbMgtDto.SearchPartnerhRes;
import com.kyowon.sms.wells.web.deduction.adsb.dto.WdebMonthlyAdsbMgtDto.SearchReq;
import com.kyowon.sms.wells.web.deduction.adsb.service.WdebMonthlyAdsbMgtService;
import com.kyowon.sms.wells.web.deduction.zcommon.constants.DeDeductionConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WDEB] 월별 재지급 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(DeDeductionConst.REST_URL_V1 + "/adsb")
public class WdebMonthlyAdsbMgtController {
    private final WdebMonthlyAdsbMgtService service;

    @ApiOperation(value = "wells 월별 재지급 관리 목록 파트너별 조회", notes = "wells 월별 재지급 관리 목록을 파트너별 조회한다")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "redfAdsbOcYm", value = "발생년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "pstnDvCd", value = "직급구분", paramType = "query"),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query"),
        @ApiImplicitParam(name = "cltnYn", value = "해약여부", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
    })
    @GetMapping("/prtnr/paging")
    public PagingResult<SearchPartnerhRes> getMcbyAdsbMgtPartnerPages(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getMcbyAdsbMgtPartnerPages(dto, pageInfo);
    }

    @ApiOperation(value = "wells 월별 재지급 관리 목록 파트너별 엑셀다운로드", notes = "wells 월별 재지급 관리 목록 파트너별 엑셀다운로드를 조회한다")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "redfAdsbOcYm", value = "발생년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "pstnDvCd", value = "직급구분", paramType = "query"),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query"),
        @ApiImplicitParam(name = "cltnYn", value = "해약여부", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
    })
    @GetMapping("/prtnr/excel-download")
    public List<SearchPartnerhRes> getMcbyAdsbMgtPartnerForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getMcbyAdsbMgtPartnerForExcelDownload(dto);
    }

    @ApiOperation(value = "wells 월별 재지급 관리 목록 계약별 조회", notes = "wells 월별 재지급 관리 목록을 계약별 조회한다")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "redfAdsbOcYm", value = "발생년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "pstnDvCd", value = "직급구분", paramType = "query"),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query"),
        @ApiImplicitParam(name = "cltnYn", value = "해약여부", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
    })
    @GetMapping("/contract/paging")
    public PagingResult<SearchContractRes> getMcbyAdsbMgtContractPages(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getMcbyAdsbMgtContractPages(dto, pageInfo);
    }

    @ApiOperation(value = "wells 월별 재지급 관리 목록 계약별 엑셀다운로드", notes = "wells 월별 재지급 관리 목록 계약별 엑셀다운로드를 조회한다")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "redfAdsbOcYm", value = "발생년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "pstnDvCd", value = "직급구분", paramType = "query"),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query"),
        @ApiImplicitParam(name = "cltnYn", value = "해약여부", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
    })
    @GetMapping("/contract/excel-download")
    public List<SearchContractRes> getMcbyAdsbMgtContractForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getMcbyAdsbMgtContractForExcelDownload(dto);
    }
}
