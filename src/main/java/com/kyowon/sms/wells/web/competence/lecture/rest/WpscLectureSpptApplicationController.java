package com.kyowon.sms.wells.web.competence.lecture.rest;

import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptApplicationDto.*;
import com.kyowon.sms.wells.web.competence.lecture.service.WpscLectureSpptApplicationService;
import com.kyowon.sms.wells.web.competence.zcommon.psCompetenceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
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

@Api(tags = "[PSC] 강의지원 신청")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(psCompetenceConst.REST_URL_V1 + "/lecture-sppt-application")
public class WpscLectureSpptApplicationController {

    private final WpscLectureSpptApplicationService service;

    @ApiOperation(value = "강의지원 신청 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "lectrSpptOgTpCd", value = "조직유형코드", required = true),
        @ApiImplicitParam(name = "lectrYm", value = "강의년월"),
        @ApiImplicitParam(name = "ogLevlDvCd1", value = "조직레벨1"),
        @ApiImplicitParam(name = "ogLevlDvCd2", value = "조직레벨2"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getLectureSpptApplicationPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getLectureSpptApplicationPages(dto, pageInfo);
    }

   @ApiOperation(value = "강의지원 강의관리 - 저장 ", notes = "강의 저장")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "lectrSpptOgTpCd", value = "조직유형코드", required = true),
        @ApiImplicitParam(name = "lectrYm", value = "강의년월", required = true),
        @ApiImplicitParam(name = "lectrTcnt", value = "우선순위"),
        @ApiImplicitParam(name = "ogId", value = "조직ID"),
        @ApiImplicitParam(name = "bldCd", value = "빌딩코드"),
        @ApiImplicitParam(name = "lectrSpptLectrCd", value = "강의지원강의코드"),
        @ApiImplicitParam(name = "lectrSpptLectCd", value = "강의지원강사코드"),
        @ApiImplicitParam(name = "lectrSpptAplcId", value = "강의지원신청ID"),
    })
    @PostMapping
    public SaveResponse saveLectureSpptApplication(
        @Valid
        @RequestBody
        List<SaveReq> reqs
    ) throws  Exception {
        return SaveResponse.builder().processCount(service.saveLectureSpptApplication(reqs)).build();
    }

    @ApiOperation(value = "빌딩 정보 조회", notes = "하위레벨 조직 정보를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", required = true),
        @ApiImplicitParam(name = "baseYm", value = "기준년월")
    })
    @GetMapping("/bldCode")
    public List<SearchLevelRes> getOrganizationBuildingCode(SearchLevelReq req){
        return service.getOrganizationBuildingCode(req);
    }

    @ApiOperation(value = "강의지원 신청 - 삭제", notes = "강의 지원 신청 삭제")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "lectrSpptAplcId", value = "강의지원신청ID", required = true),
    })
    @DeleteMapping
    public SaveResponse removeLectureSpptApplicationList(
        @Valid
        @RequestBody List<RemoveReq> reqs
    ) throws  Exception {
        return SaveResponse.builder().processCount(service.removeLectureSpptApplicationList(reqs)).build();
    }
}
