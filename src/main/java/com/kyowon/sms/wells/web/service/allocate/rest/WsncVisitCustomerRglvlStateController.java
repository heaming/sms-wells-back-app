package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitCustomerRglvlStateDto.FindOrganizationRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitCustomerRglvlStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitCustomerRglvlStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.service.WsncVisitCustomerRglvlStateService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNC] 방문고객 급지현황")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/visit-customer-rglvl-state")
@Validated
public class WsncVisitCustomerRglvlStateController {
    private final WsncVisitCustomerRglvlStateService service;

    @ApiOperation(value = "방문고객 급지현황 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "visitYm", value = "방문년월", paramType = "query", example = "202305"),
        @ApiImplicitParam(name = "rcgvpDiv", value = "고객구분", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "executiveGroup", value = "총괄단", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "localGroupCd", value = "지역단", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "branchOfficeCd", value = "지점", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "partnerNo", value = "매니저", paramType = "query", example = "ALL"),
    })
    @GetMapping
    public List<SearchRes> getVisitCustomerRglvlState(
        @Valid
        SearchReq dto
    ) {
        return service.getVisitCustomerRglvlState(dto);
    }

    @ApiOperation(value = "방문고객 급지현황 조회 (엑셀 다운로드)", notes = "방문고객 급지현황을 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getVisitCustomerRglvlStateForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getVisitCustomerRglvlStateForExcelDownload(dto);
    }

    @ApiOperation(value = "사용자 조직 정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogId", value = "조직 아이디", paramType = "path", example = ""),
    })
    @GetMapping("/organization-info/{ogId}")
    public FindOrganizationRes getOrganizationInfo(
        @PathVariable
        String ogId
    ) {
        return service.getOrganizationInfo(ogId);
    }
}
