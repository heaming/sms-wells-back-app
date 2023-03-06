package com.kyowon.sms.wells.web.organization.hmnrsc.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchPlannerLicenseReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchPlannerLicenseRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.service.WogcPartnerPlannerService;
import com.kyowon.sms.wells.web.organization.zcommon.constants.OgConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "[WOGC] 플래너 관리 REST API")
@Validated
@RequiredArgsConstructor
@RequestMapping(OgConst.REST_URL_V1 + OgConst.REST_URL_PARTNER)
public class WogcPartnerPlannerController {

    private final WogcPartnerPlannerService service;

    @ApiOperation(value = "플래너 자격관리 페이징 조회", notes = "조회 조건에 일치하는 플래너 자격관리를 페이징 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "prntrNo", value = "", paramType = "query", required = false),
        @ApiImplicitParam(name = "prntrKnm", value = "", paramType = "query", required = false),
        @ApiImplicitParam(name = "olfDvCd", value = "", paramType = "query", required = false),

    })
    @GetMapping("/planner-license/paging")
    public PagingResult<SearchPlannerLicenseRes> getPlannerLicensePages(
        SearchPlannerLicenseReq dto,
        @Valid
        PageInfo pageinfo) {
        return service.getPlannerLicensePages(dto, pageinfo);
    }
    @ApiOperation(value = "플래너자격관리 엑셀다운로드", notes = "검색조건을 입력 받아 엑셀다운로드용 플래너 자격관리를 조회한다.")
    @GetMapping("/planner-license/excel-download")
    public List<SearchPlannerLicenseRes> getPlannerLicenseForExcelDownload(SearchPlannerLicenseReq dto) {
        return service.getPlannerLicenseForExcelDownload(dto);
    }
}
