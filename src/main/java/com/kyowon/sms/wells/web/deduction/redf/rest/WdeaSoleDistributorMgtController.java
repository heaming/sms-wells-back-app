package com.kyowon.sms.wells.web.deduction.redf.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaSoleDistributorMgtDto.SaveReq;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaSoleDistributorMgtDto.SearchBusinessToBusinessPrtnrRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaSoleDistributorMgtDto.SearchSoleDistributorContractRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaSoleDistributorMgtDto.SearchSoleDistributorCreateReq;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaSoleDistributorMgtDto.SearchSoleDistributorMgtReq;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaSoleDistributorMgtDto.SearchSoleDistributorMgtRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaSoleDistributorMgtDto.SearchSoleDistributorPrtnrRes;
import com.kyowon.sms.wells.web.deduction.redf.service.WdeaSoleDistributorMgtService;
import com.kyowon.sms.wells.web.deduction.zcommon.constants.DeDeductionConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WEDA] Wells 총판 가지급금/되물림 관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(DeDeductionConst.REST_URL_V1 + "/sole-distributors")
public class WdeaSoleDistributorMgtController {

    private final WdeaSoleDistributorMgtService wdeaSoleDistributorMgtService;

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "redfAdsbOcYmFrom", value = "발생년월(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "redfAdsbOcYmTo", value = "발생년월(To)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "perfYmFrom", value = "실적년월(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "perfYmTo", value = "실적년월(To)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "ogCdFrom", value = "소속코드(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "ogCdTo", value = "소속코드(To)", paramType = "query", required = false, example = ""),
    })
    @ApiOperation(value = "총판/B2B 되물림 생성 - 1번탭 그리드 1번", notes = " 검색조건을 받아 총판/B2B 되물림 생성 목록을 조회한다.")
    @GetMapping("/partner/paging")
    public PagingResult<SearchSoleDistributorPrtnrRes> getSoleDistributorPrtnrPages(
        SearchSoleDistributorCreateReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return wdeaSoleDistributorMgtService.getSoleDistributorPrtnrPages(dto, pageInfo);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "redfAdsbOcYmFrom", value = "발생년월(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "redfAdsbOcYmTo", value = "발생년월(To)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "perfYmFrom", value = "실적년월(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "perfYmTo", value = "실적년월(To)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "ogCdFrom", value = "소속코드(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "ogCdTo", value = "소속코드(To)", paramType = "query", required = false, example = ""),
    })
    @ApiOperation(value = "총판/B2B 되물림 생성 - 1번탭 그리드 1번", notes = " 검색조건을 받아 총판/B2B 되물림 생성 목록을 조회한다.")
    @GetMapping("/partner/excel-download")
    public List<SearchSoleDistributorPrtnrRes> getSoleDistributorPrtnrForExcelDownload(
        SearchSoleDistributorCreateReq dto
    ) {
        return wdeaSoleDistributorMgtService.getSoleDistributorPrtnrForExcelDownload(dto);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "redfAdsbOcYmFrom", value = "발생년월(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "redfAdsbOcYmTo", value = "발생년월(To)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "perfYmFrom", value = "실적년월(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "perfYmTo", value = "실적년월(To)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "ogCdFrom", value = "소속코드(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "ogCdTo", value = "소속코드(To)", paramType = "query", required = false, example = ""),
    })
    @ApiOperation(value = "총판/B2B 되물림 생성 - 1번탭 그리드 2번", notes = " 검색조건을 받아 총판/B2B 되물림 생성 목록을 조회한다.")
    @GetMapping("/contract/paging")
    public PagingResult<SearchSoleDistributorContractRes> getSoleDistributorContractPages(
        SearchSoleDistributorCreateReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return wdeaSoleDistributorMgtService.getSoleDistributorContractPages(dto, pageInfo);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "redfAdsbOcYmFrom", value = "발생년월(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "redfAdsbOcYmTo", value = "발생년월(To)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "perfYmFrom", value = "실적년월(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "perfYmTo", value = "실적년월(To)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "ogCdFrom", value = "소속코드(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "ogCdTo", value = "소속코드(To)", paramType = "query", required = false, example = ""),
    })
    @ApiOperation(value = "총판/B2B 되물림 생성 - 1번탭 그리드 2번", notes = " 검색조건을 받아 총판/B2B 되물림 생성 목록을 조회한다.")
    @GetMapping("/contract/excel-download")
    public List<SearchSoleDistributorContractRes> getSoleDistributorContractForExcelDownload(
        SearchSoleDistributorCreateReq dto
    ) {
        return wdeaSoleDistributorMgtService.getSoleDistributorContractForExcelDownload(dto);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "redfAdsbOcYmFrom", value = "발생년월(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "redfAdsbOcYmTo", value = "발생년월(To)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "perfYmFrom", value = "실적년월(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "perfYmTo", value = "실적년월(To)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "ogCdFrom", value = "소속코드(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "ogCdTo", value = "소속코드(To)", paramType = "query", required = false, example = ""),
    })
    @ApiOperation(value = "총판/B2B 되물림 생성 - 1번탭 그리드 3번", notes = " 검색조건을 받아 총판/B2B 되물림 생성 목록을 조회한다.")
    @GetMapping("/b2b/paging")
    public PagingResult<SearchBusinessToBusinessPrtnrRes> getBusinessToBusinessPrtnrPages(
        SearchSoleDistributorCreateReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return wdeaSoleDistributorMgtService.getBusinessToBusinessPrtnrPages(dto, pageInfo);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "redfAdsbOcYmFrom", value = "발생년월(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "redfAdsbOcYmTo", value = "발생년월(To)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "perfYmFrom", value = "실적년월(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "perfYmTo", value = "실적년월(To)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "ogCdFrom", value = "소속코드(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "ogCdTo", value = "소속코드(To)", paramType = "query", required = false, example = ""),
    })
    @ApiOperation(value = "총판/B2B 되물림 생성 - 1번탭 그리드 3번", notes = " 검색조건을 받아 총판/B2B 되물림 생성 목록을 조회한다.")
    @GetMapping("/b2b/excel-download")
    public List<SearchBusinessToBusinessPrtnrRes> getBusinessToBusinessPrtnrForExcelDownload(
        SearchSoleDistributorCreateReq dto
    ) {
        return wdeaSoleDistributorMgtService.getBusinessToBusinessPrtnrForExcelDownload(dto);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "redfAdsbOcYmFrom", value = "관리년월(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "redfAdsbOcYmTo", value = "관리년월(To)", paramType = "query", required = false, example = ""),
    })
    @ApiOperation(value = "총판/B2B 되물림 관리 - 2번탭", notes = " 검색조건을 받아 총판/B2B 되물림 관리 목록을 조회한다.")
    @GetMapping("/management/paging")
    public PagingResult<SearchSoleDistributorMgtRes> getSoleDistributorB2bPages(
        SearchSoleDistributorMgtReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return wdeaSoleDistributorMgtService.getSoleDistributorB2bPages(dto, pageInfo);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "redfAdsbOcYmFrom", value = "관리년월(From)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "redfAdsbOcYmTo", value = "관리년월(To)", paramType = "query", required = false, example = ""),
    })
    @ApiOperation(value = "총판/B2B 되물림 관리 - 2번탭", notes = " 검색조건을 받아 총판/B2B 되물림 관리 목록을 조회한다.")
    @GetMapping("/management/excel-download")
    public List<SearchSoleDistributorMgtRes> getSoleDistributorB2bForExcelDownload(
        SearchSoleDistributorMgtReq dto
    ) {
        return wdeaSoleDistributorMgtService.getSoleDistributorB2bForExcelDownload(dto);
    }

    @ApiOperation(value = "총판/B2B 되물림 관리 - 수정", notes = "되물림 공제금액을 수정한다")
    @PostMapping("/management")
    public SaveResponse editSoleDistributorB2b(
        @Valid
        @RequestBody
        List<SaveReq> dtos
    ) throws Exception {

        return SaveResponse.builder().processCount(wdeaSoleDistributorMgtService.editSoleDistributorB2b(dtos))
            .build();

    }
}
