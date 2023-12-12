package com.kyowon.sms.wells.web.competence.business.rest;

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

    @ApiOperation(value = "규정 및 기준관리 - 리스트 조회", notes = "리스트 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "urgnYn", value = "최신버전검색", paramType = "query", required = true),
    })
    @GetMapping
    public List<SearchRes> getRuleBaseList(
        SearchReq dto
    ) {
        return service.getRuleBaseList(dto);
    }

    @ApiOperation(value = "규정 및 기준관리 - 상세 조회", notes = "상세 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bznsSpptMnalId", value = "영업지원매뉴얼ID", paramType = "query"),
        @ApiImplicitParam(name = "vlStrtDtm", value = "유효시작일시", paramType = "query"),
    })
    @GetMapping("/detail")
    public SearchRes getRuleBase(
        @Valid
        SearchReq req
    ){
        return service.getRuleBaseDetail(req);
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
        SaveReq dto
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
