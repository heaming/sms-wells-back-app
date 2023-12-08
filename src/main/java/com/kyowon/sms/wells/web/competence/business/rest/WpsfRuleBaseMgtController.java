package com.kyowon.sms.wells.web.competence.business.rest;

import com.kyowon.sms.wells.web.competence.business.dto.WpsfRuleBaseMgtDto;
import com.kyowon.sms.wells.web.competence.business.dto.WpsfRuleBaseMgtDto.SaveReq;
import com.kyowon.sms.wells.web.competence.business.dto.WpsfRuleBaseMgtDto.SearchReq;
import com.kyowon.sms.wells.web.competence.business.dto.WpsfRuleBaseMgtDto.SearchRes;
import com.kyowon.sms.wells.web.competence.business.service.WpsfRuleBaseMgtService;
import com.kyowon.sms.wells.web.competence.zcommon.constants.PsCompetenceConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WPSF] 규정 및 기준관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(PsCompetenceConst.REST_URL_V1 + "/rulebase")
public class WpsfRuleBaseMgtController {

    private final WpsfRuleBaseMgtService service;

    @ApiOperation(value = "규정 및 기준관리 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping
    public List<SearchRes> getRuleBase(
        @Valid
        SearchReq dto
    ) {
        return service.getRuleBase(dto);
    }

    @ApiOperation(value = "규정 및 기준관리 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "rsbDvCd", value = "직책", paramType = "query", required = true),
    })
    @GetMapping("/user")
    public List<SearchRes> getUserRuleBase(
        @Valid
        SearchReq dto
    ) {
        return service.getUserRuleBase(dto);
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
        List<SaveReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(service.saveRuleBaseTree(dtos))
            .build();
    }

}
