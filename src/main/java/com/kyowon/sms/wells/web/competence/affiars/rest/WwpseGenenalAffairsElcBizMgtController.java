package com.kyowon.sms.wells.web.competence.affiars.rest;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizMgtDto;
import com.kyowon.sms.wells.web.competence.affiars.service.WwpseGenenalAffairsElcBizMgtService;
import com.kyowon.sms.wells.web.competence.zcommon.constants.PsCompetenceConst;
import com.sds.sflex.system.config.response.SaveResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizMgtDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[] 총무전자업무관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(PsCompetenceConst.REST_URL_V1 + "/genenal-affairs-elc-biz")
public class WwpseGenenalAffairsElcBizMgtController {

    private final WwpseGenenalAffairsElcBizMgtService service;

    @ApiOperation(value = "총무전자업무관리 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getGenenalAffairsElcBizMgtPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getGenenalAffairsElcBizMgtPages(dto, pageInfo);
    }

    @ApiOperation(value = "총무전자업무관리 업무유형 내용", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/business-type-content")
    public List<businessTypeRes> getBusinessTypeContent(
        @Valid
        SearchReq dto
    ) {
        return service.getBusinessTypeContent(dto);
    }

    @ApiOperation(value = "총무전자업무관리 업무유형", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/business-type")
    public Map<String, List> getBusinessType(
        @Valid
        WwpseGenenalAffairsElcBizMgtDto.SearchReq dto
    ) {
        return service.getBusinessType(dto);
    }

    @ApiOperation(value = "총무전자업무관리 파트너 연락처", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/prtnr-info")
    public List<WwpseGenenalAffairsElcBizMgtDto.PrtnrRes> getPrtnrInfo(
        @Valid
        WwpseGenenalAffairsElcBizMgtDto.SearchReq dto
    ) {
        return service.getPrtnrInfo(dto);
    }

    @ApiOperation(value = "총무전자업무관리 저장", notes = "")
    @PostMapping("/business-manager-report")
    public SaveResponse saveBusinessManagerReport(
        @RequestBody
        @Valid
        List<WwpseGenenalAffairsElcBizMgtDto.SaveReq> dtos
    ) {
        return SaveResponse.builder().processCount(this.service.saveBusinessManagerReport(dtos)).build();
    }

    @ApiOperation(value = "총무전자업무관리 업무 유형 저장", notes = "")
    @PostMapping("/report-business-type")
    public SaveResponse saveReportBusinessType(
        @RequestBody
        @Valid
        WwpseGenenalAffairsElcBizMgtDto.SaveReq dto
    ) {
        return SaveResponse.builder().processCount(this.service.saveReportBusinessType(dto)).build();
    }

    @ApiOperation(value = "총무전자업무관리 삭제", notes = "")
    @DeleteMapping("/")
    public SaveResponse removeSpectxBlkPrnts(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) {
        return SaveResponse.builder().processCount(this.service.removeBusinessManagerReport(dtos)).build();
    }

}
