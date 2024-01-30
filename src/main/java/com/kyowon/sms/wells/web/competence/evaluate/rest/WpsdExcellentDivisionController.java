package com.kyowon.sms.wells.web.competence.evaluate.rest;

import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExcellentDivisionDto.*;
import com.kyowon.sms.wells.web.competence.evaluate.service.WpsdExcellentDivisionService;
import com.kyowon.sms.wells.web.competence.zcommon.constants.PsCompetenceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "[WPSD] 우수사업부 현황")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(PsCompetenceConst.REST_URL_V1 + "/excellent-division")
public class WpsdExcellentDivisionController {

    private final WpsdExcellentDivisionService service;

    @ApiOperation(value = "우수사업부 현황 - 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "관리년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "evlOgTpCd", value = "조직유형코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "awdEvlId", value = "시상평가ID", paramType = "query" ),
        @ApiImplicitParam(name = "cntrPerfDvCd", value = "실적구분", paramType = "query" ),
        @ApiImplicitParam(name = "ctstGrpCd", value = "당월그룹코드", paramType = "query"),
    })
    @GetMapping("/paging")
    public Map<String, Object> getExcellentDivisionPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getExcellentDivisionPages(dto, pageInfo);
    }

    @ApiOperation(value = "우수사업부 현황 - 엑셀 다운로드", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "관리년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "evlOgTpCd", value = "조직유형코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "awdEvlId", value = "시상평가ID", paramType = "query" ),
        @ApiImplicitParam(name = "cntrPerfDvCd", value = "실적구분", paramType = "query" ),
        @ApiImplicitParam(name = "ctstGrpCd", value = "당월그룹코드", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<HashMap<String, Object>> getExcellentDivisionsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getExcellentDivisionsForExcelDownload(dto);
    }

    @ApiOperation(value = "우수사업부 현황 - 목표 저장", notes = "목표 저장")
    @PostMapping
    public SaveResponse saveExcellentDivision(
        @Valid
        @RequestBody
        List<SaveReq> reqs
    ){
        return SaveResponse.builder().processCount(service.saveExcellentDivision(reqs)).build();

    }

    @ApiOperation(value = "우수사업부 현황 - 경진조 조회", notes = "경진조 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "관리년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "evlDvCd", value = "평가구분코드", paramType = "query", required = true),
    })
    @GetMapping("/contest")
    public List<SearchContestRes> getContestGroupList(
        @Valid
        SearchContestReq req
    ){
        return service.getContestGroupList(req);
    }
    @ApiOperation(value = "우수사업부 현황 - 평가직책 조회", notes = "경진조 등록 팝업 내에 평가 직책 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "evlDvCd", value = "평가구분코드", paramType = "query", required = true),
    })
    @GetMapping("/evaluation-responsibility")
    public List<SearchEvlRsbRes> getEvaluationResponsibility(
        @Valid
        SearchEvlRsbReq req
    ){
        return service.getEvaluationResponsibility(req);
    }

    @ApiOperation(value = "우수사업부 현황 - 조직별 경진 그룹 조회", notes = "경진조 등록 팝업 내에 조직별 경진그룹 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "awdEvlId", value = "시상평가ID", paramType = "query", required = true),
        @ApiImplicitParam(name = "evlAtcDvCd", value = "시상평가ID", paramType = "query", required = true),
        @ApiImplicitParam(name = "evlRsbDvCd", value = "평가직책구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "ctstGrpCd", value = "경진그룹코드", paramType = "query" ),
    })
    @GetMapping("/contest-responsibility")
    public List<SearchContestPartnerRes> getContestResponsibilityGroupList(
        @Valid
        SearchContestPartnerReq req
    ){
        return service.getContestResponsibilityGroupList(req);
    }

    @ApiOperation(value = "우수사업부 현황 - 조직별 경진 저장", notes = "경진조 등록 팝업 내에 조직별 경진그룹 저장")
    @PostMapping("/contest-responsibility")
    public SaveResponse saveContestResponsibilityGroup(
        @Valid
        @RequestBody
        List<SaveContestPartnerReq> reqs
    ){
        return SaveResponse.builder().processCount(service.saveContestResponsibilityGroup(reqs))
            .build();
    }

}
