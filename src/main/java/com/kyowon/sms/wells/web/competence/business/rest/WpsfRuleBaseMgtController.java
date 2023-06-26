package com.kyowon.sms.wells.web.competence.business.rest;

import static com.kyowon.sms.wells.web.competence.business.dto.WpsfRuleBaseMgtDto.SearchReq;
import static com.kyowon.sms.wells.web.competence.business.dto.WpsfRuleBaseMgtDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.competence.business.dto.WpsfRuleBaseMgtDto;
import com.kyowon.sms.wells.web.competence.business.service.WpsfRuleBaseMgtService;
import com.kyowon.sms.wells.web.competence.zcommon.psCompetenceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[] 규정 및 기준관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(psCompetenceConst.REST_URL_V1 + "/business/rulebase")
public class WpsfRuleBaseMgtController {

    private final WpsfRuleBaseMgtService service;

    @ApiOperation(value = "규정 및 기준관리 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping
    public List<SearchRes> getruleBaseMgtPages(
        @Valid
        SearchReq dto
    ) {
        return service.getRuleBaseMgtsForExcelDownload(dto);
    }

    @ApiOperation(value = "규정 및 기준관리 저장", notes = "규정 및 기준관리를 저장한다.")
    @PostMapping
    public SaveResponse saveRuleBase(
        @RequestBody
        @Valid
        WpsfRuleBaseMgtDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveRuleBase(dto))
            .build();
    }

    @ApiOperation(value = "규정 및 기준관리 트리저장", notes = "규정 및 기준관리를 저장한다.")
    @PostMapping("/tree")
    public SaveResponse saveRuleBaseTree(
        @RequestBody
        @Valid
        List<WpsfRuleBaseMgtDto.SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveRuleBaseTree(dtos))
            .build();
    }

}
