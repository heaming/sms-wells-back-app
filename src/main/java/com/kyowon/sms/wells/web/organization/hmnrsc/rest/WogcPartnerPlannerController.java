package com.kyowon.sms.wells.web.organization.hmnrsc.rest;

import java.util.List;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto;
import com.sds.sflex.system.config.response.SaveResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseRes;
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
@RequestMapping(OgConst.REST_PREFIX_SMS_WELLS + "/partner")
public class WogcPartnerPlannerController {

    private final WogcPartnerPlannerService service;

    @ApiOperation(value = "수석플래너 신청관리 페이징 조회", notes = "조회 조건에 일치하는 수석플래너 신청관리를 페이징 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "prntrNo", value = "", paramType = "query", required = false),
        @ApiImplicitParam(name = "prntrKnm", value = "", paramType = "query", required = false),
        @ApiImplicitParam(name = "mOgYn", value = "", paramType = "query", required = false),

    })
    @GetMapping("/paging")
    public PagingResult<WogcPartnerPlannerDto.SearchRes> getTopPlannerPages(
        WogcPartnerPlannerDto.SearchReq dto,
        @Valid
        PageInfo pageinfo
    ) {
        return service.getTopPlannerPages(dto, pageinfo);
    }

    @ApiOperation(value = "수석플래너 신청관리 엑셀다운로드", notes = "검색조건을 입력 받아 엑셀다운로드용 수석플래너 신청관리를 조회한다.")
    @GetMapping("/excel-download")
    public List<WogcPartnerPlannerDto.SearchRes> getTopPlannerForExcelDownload(WogcPartnerPlannerDto.SearchReq dto) {
        return service.getTopPlannerForExcelDownload(dto);
    }

    @ApiOperation(value = "수석플래너 신청관리 삭제", notes = "수석플래너 신청관리를 삭제한다.")
    @DeleteMapping
    public SaveResponse removeTopPlanner(
        @RequestBody
        WogcPartnerPlannerDto.DeleteReq dto
    ) throws Exception {
        this.service.removeTopPlanner(dto);
        return SaveResponse.builder().processCount(1).build();
    }

    @ApiOperation(value = "자격생성", notes = "자격생성을 통해 자격을 변경한다.")
    @PutMapping
    public SaveResponse saveTopPlanner(
        @Valid
        @RequestBody
        WogcPartnerPlannerDto.SaveReq dto
    ) throws Exception {
        this.service.saveTopPlanner(dto);
        return SaveResponse.builder().processCount(1).build();
    }

    @ApiOperation(value = "수석자격조정 팝업 조회", notes = "수석자격조정 팝업을 조회한다.")
    @GetMapping("/{bldCd}/{gridOgTpCd}")
    public WogcPartnerPlannerDto.FindRes getTopPlanner(@PathVariable
    String bldCd, @PathVariable
    String gridOgTpCd) {
        return this.service.getTopPlanner(bldCd, gridOgTpCd);
    }

    @ApiOperation(value = "자격조정", notes = "자격생성을 통해 자격을 변경한다.")
    @PutMapping("/{attOjsn}")
    public SaveResponse savePlanner(
        @Valid
        @RequestBody
        WogcPartnerPlannerDto.EditReq dto
    ) throws Exception {
        this.service.saveBuilding(dto);
        return SaveResponse.builder().processCount(1).build();
    }

    @ApiOperation(value = "플래너 자격관리 페이징 조회", notes = "조회 조건에 일치하는 플래너 자격관리를 페이징 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "prntrNo", value = "", paramType = "query", required = false),
        @ApiImplicitParam(name = "prntrKnm", value = "", paramType = "query", required = false),
        @ApiImplicitParam(name = "olfDvCd", value = "", paramType = "query", required = false),

    })
    @GetMapping("/planner-license/paging")
    public PagingResult<SearchLicenseRes> getLicensePages(
        SearchLicenseReq dto,
        @Valid
        PageInfo pageinfo
    ) {
        return service.getPlannerLicensePages(dto, pageinfo);
    }

    @ApiOperation(value = "플래너자격관리 엑셀다운로드", notes = "검색조건을 입력 받아 엑셀다운로드용 플래너 자격관리를 조회한다.")
    @GetMapping("/planner-license/excel-download")
    public List<SearchLicenseRes> getPlannerLicenseForExcelDownload(SearchLicenseReq dto) {
        return service.getPlannerLicenseForExcelDownload(dto);
    }
}
