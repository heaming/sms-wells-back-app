package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaRentalAccountMgtDto.SearchBpdRentalAccountReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaRentalAccountMgtDto.SearchBpdRentalAccountRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaRentalAccountMgtDto.SearchByoRentalAccountReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaRentalAccountMgtDto.SearchByoRentalAccountRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaRentalAccountMgtService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "렌탈 계정 관리 현황")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/rental-accounts")
public class WctaRentalAccountMgtController {
    private final WctaRentalAccountMgtService service;

    @ApiOperation(value = "렌탈 계정 관리 현황 - 상품별 조회", notes = "렌탈 계정 관리 현황 - 상품별 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "srchGbn", value = "조회구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "istStartDt", value = "설치시작년월", paramType = "query", example = "20220101"),
        @ApiImplicitParam(name = "istEndDt", value = "설치끝년월", paramType = "query", example = "20221231"),
        @ApiImplicitParam(name = "pdMclsfId", value = "상품군", paramType = "query"),
        @ApiImplicitParam(name = "basePdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "copnDvCd", value = "고객구분", paramType = "query"),
    })
    @GetMapping("/rental-accounts/products")
    public List<SearchBpdRentalAccountRes> getBpdRentalAccount(
        SearchBpdRentalAccountReq dto
    ) {
        return service.getBpdRentalAccount(dto);
    }

    @ApiOperation(value = "렌탈 계정 관리 현황 - 조직별 조회", notes = "렌탈 계정 관리 현황 - 조직별 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "srchGbn", value = "조회구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "istStartDt", value = "설치시작년월", paramType = "query", example = "20220101"),
        @ApiImplicitParam(name = "istEndDt", value = "설치끝년월", paramType = "query", example = "20221231"),
        @ApiImplicitParam(name = "dgr1LevlOgNm", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgNm", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "copnDvCd", value = "고객구분", paramType = "query"),
    })
    @GetMapping("/rental-accounts/organizations")
    public List<SearchByoRentalAccountRes> getByoRentalAccount(
        SearchByoRentalAccountReq dto
    ) {
        return service.getByoRentalAccount(dto);
    }
}
