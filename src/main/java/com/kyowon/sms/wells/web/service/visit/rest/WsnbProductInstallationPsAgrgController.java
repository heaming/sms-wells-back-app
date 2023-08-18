package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbProductInstallationPsAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbProductInstallationPsAgrgDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbProductInstallationPsAgrgService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNB] 제품별 설치 현황 집계 조회 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/product-installation-ps-agrg")
public class WsnbProductInstallationPsAgrgController {

    private final WsnbProductInstallationPsAgrgService service;

    @ApiOperation(value = "제품별 설치 현황 집계", notes = "조회조건에 일치하는 제품별 설치 현황 집계(설치/교체/철거) 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYy", value = "기준년도", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹코드", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getPersonInChargeVisitAgrgs(
        @Valid
        SearchReq dto
    ) {
        return service.getProductInstallationPsAgrgs(dto);
    }
}
