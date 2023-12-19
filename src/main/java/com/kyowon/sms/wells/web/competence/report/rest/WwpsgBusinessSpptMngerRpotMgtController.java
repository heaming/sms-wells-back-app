package com.kyowon.sms.wells.web.competence.report.rest;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessSpptMngerRpotMgtDto;
import com.kyowon.sms.wells.web.competence.report.service.WwpsgBusinessSpptMngerRpotMgtService;
import com.kyowon.sms.wells.web.competence.zcommon.constants.PsCompetenceConst;
import com.sds.sflex.system.config.response.SaveResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessSpptMngerRpotMgtDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[] 업무지원매니저 보고서 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(PsCompetenceConst.REST_URL_V1 + "/business-support-mnger")
public class WwpsgBusinessSpptMngerRpotMgtController {

    private final WwpsgBusinessSpptMngerRpotMgtService service;

    @ApiOperation(value = "업무지원매니저 보고서 관리 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getBusinessSpptMngerRpotMgtPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getBusinessSpptMngerRpotMgtPages(dto, pageInfo);
    }

    @ApiOperation(value = "업무지원매니저 보고서 관리 업무유형 내용", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/business-type-content")
    public List<WwpsgBusinessSpptMngerRpotMgtDto.businessTypeRes> getBusinessTypeContent(
        @Valid
        WwpsgBusinessSpptMngerRpotMgtDto.SearchReq dto
    ) {
        return service.getBusinessTypeContent(dto);
    }

    @ApiOperation(value = "업무지원매니저 보고서 관리 업무유형", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/business-type")
    public Map<String, List> getBusinessType(
        @Valid
        WwpsgBusinessSpptMngerRpotMgtDto.SearchReq dto
    ) {
        return service.getBusinessType(dto);
    }

    @ApiOperation(value = "업무지원매니저 보고서 관리 파트너 연락처", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/prtnr-info")
    public List<WwpsgBusinessSpptMngerRpotMgtDto.PrtnrRes> getPrtnrInfo(
        @Valid
        WwpsgBusinessSpptMngerRpotMgtDto.SearchReq dto
    ) {
        return service.getPrtnrInfo(dto);
    }

    @ApiOperation(value = "업무지원매니저 보고서 관리 저장", notes = "")
    @PostMapping("/business-manager-report")
    public SaveResponse saveBusinessManagerReport(
        @RequestBody
        @Valid
        List<WwpsgBusinessSpptMngerRpotMgtDto.SaveReq> dtos
    ) {
        return SaveResponse.builder().processCount(this.service.saveBusinessManagerReport(dtos)).build();
    }

    @ApiOperation(value = "업무지원매니저 보고서 관리 업무 유형 저장", notes = "")
    @PostMapping("/report-business-type")
    public SaveResponse saveReportBusinessType(
        @RequestBody
        @Valid
        WwpsgBusinessSpptMngerRpotMgtDto.SaveReq dto
    ) {
        return SaveResponse.builder().processCount(this.service.saveReportBusinessType(dto)).build();
    }

    @ApiOperation(value = "업무지원매니저 보고서 관리 삭제", notes = "")
    @DeleteMapping("/")
    public SaveResponse removeSpectxBlkPrnts(
        @RequestBody
        @Valid
        @NotEmpty
        List<WwpsgBusinessSpptMngerRpotMgtDto.SaveReq> dtos
    ) {
        return SaveResponse.builder().processCount(this.service.removeBusinessManagerReport(dtos)).build();
    }
}
