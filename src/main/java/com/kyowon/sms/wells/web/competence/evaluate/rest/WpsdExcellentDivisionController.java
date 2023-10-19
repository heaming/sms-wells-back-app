package com.kyowon.sms.wells.web.competence.evaluate.rest;

import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExcellentDivisionDto.SearchContestReq;
import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExcellentDivisionDto.SearchContestRes;
import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExcellentDivisionDto.SearchReq;
import com.kyowon.sms.wells.web.competence.evaluate.service.WpsdExcellentDivisionService;
import com.kyowon.sms.wells.web.competence.zcommon.psCompetenceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "[WPSD] 우수사업부 현황")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(psCompetenceConst.REST_URL_V1 + "/excellent-division")
public class WpsdExcellentDivisionController {

    private final WpsdExcellentDivisionService service;

    @ApiOperation(value = "우수사업부 현황 - 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "관리년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "evlOgTpCd", value = "조직유형코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "evlDvCd", value = "평가구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrPerfDvCd", value = "실적구분", paramType = "query" ),
        @ApiImplicitParam(name = "ctstGrpCd", value = "당월그룹코드", paramType = "query"),
    })
    @GetMapping("/paging")
    public Map<String, Object> getExcellentDivisionPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) throws Exception {
        return service.getExcellentDivisionPages(dto, pageInfo);
    }

    @ApiOperation(value = "우수사업부 현황 - 엑셀 다운로드", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "관리년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "evlOgTpCd", value = "조직유형코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "evlDvCd", value = "평가구분코드", paramType = "query", required = true),
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

    @ApiOperation(value = "우수사업부 현황 - 경진조 조회", notes = "")
    @ApiImplicitParams(value = {
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

}
