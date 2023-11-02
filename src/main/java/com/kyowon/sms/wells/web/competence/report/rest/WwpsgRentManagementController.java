package com.kyowon.sms.wells.web.competence.report.rest;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.competence.report.service.WwpsgRentManagementService;
import com.kyowon.sms.wells.web.competence.zcommon.constants.PsCompetenceConst;
import com.sds.sflex.system.config.response.SaveResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.kyowon.sms.wells.web.competence.report.dto.WwpsgRentManagementDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[] 임차관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(PsCompetenceConst.REST_URL_V1 + "/rent-management")
public class WwpsgRentManagementController {

    private final WwpsgRentManagementService service;

    @ApiOperation(value = "임차관리 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getRentManagementPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getRentManagementPages(dto, pageInfo);
    }

    @ApiOperation(value = "임차관리 팝업", notes = "")
    @GetMapping("/rent-popup")
    public PopupSearchRes getRentManagementPopup(
        @Valid
        SearchReq dto
    ) {
        return service.getRentManagementPopup(dto);
    }

    @ApiOperation(value = "임차관리 기본정보", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/base-information")
    public Map<String, List> getBusinessType(
        @Valid
        BaseSearchReq dto
    ) {
        return service.getBaseInfo(dto);
    }

    @ApiOperation(value = "임차관리 엑셀 다운로드", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getRentManagementsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getRentManagementsForExcelDownload(dto);
    }

    @ApiOperation(value = "임차 관리 저장", notes = "")
    @PostMapping("/rent-management")
    public SaveResponse saveRentManagement(
        @RequestBody
        @Valid
        SaveReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveRentManagement(dto)).build();
    }

    @ApiOperation(value = "임차 관리 저장", notes = "")
    @PutMapping("/rent-procs")
    public SaveResponse saveRentProcs(
        @RequestBody
        @Valid
        SaveReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveRentProcs(dto)).build();
    }
}
