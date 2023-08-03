package com.kyowon.sms.wells.web.competence.lecture.rest;

import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptLecturerDto.SaveReq;
import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptLecturerDto.SearchReq;
import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptLecturerDto.SearchRes;
import com.kyowon.sms.wells.web.competence.lecture.service.WpscLectureSpptLecturerService;
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

@Api(tags = "[PSC] 강의지원 강사관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(psCompetenceConst.REST_URL_V1 + "/lecture-sppt-lecturer")
public class WpscLectureSpptLecturerController {

    private final WpscLectureSpptLecturerService service;

    @ApiOperation(value = "강의지원 강사관리 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", required = true),
        @ApiImplicitParam(name = "lectNm", value = "강사명"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getLectureSpptLecturerPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getLectureSpptLecturerPages(dto, pageInfo);
    }

    @ApiOperation(value = "강의지원 강사관리 - 저장 ", notes = "강사 저장")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "lectrSpptOgTpCd", value = "조직유형코드", required = true),
        @ApiImplicitParam(name = "lectNm", value = "강사명", required = true),
        @ApiImplicitParam(name = "lectrSpptLectCd", value = "강사코드"),
        @ApiImplicitParam(name = "useYn", value = "사용여부"),
    })
    @PostMapping
    public SaveResponse saveLectureSpptLecturer(
        @Valid
        @RequestBody
        List<SaveReq> reqs
    ) throws  Exception {
        return SaveResponse.builder().processCount(service.saveLectureSpptLecturer(reqs)).build();
    }
}
