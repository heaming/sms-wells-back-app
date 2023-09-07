package com.kyowon.sms.wells.web.competence.lecture.rest;

import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptLectureDto.SaveReq;
import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptLectureDto.SearchReq;
import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptLectureDto.SearchRes;
import com.kyowon.sms.wells.web.competence.lecture.service.WpscLectureSpptLectureService;
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

@Api(tags = "[PSC] 강의지원 강의관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(psCompetenceConst.REST_URL_V1 + "/lecture-sppt-lecture")
public class WpscLectureSpptLectureController {

    private final WpscLectureSpptLectureService service;

    @ApiOperation(value = "강의지원 강의관리 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", required = true),
        @ApiImplicitParam(name = "lectrNm", value = "강의명"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getLectureSpptLecturerPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getLectureSpptLecturePages(dto, pageInfo);
    }

    @ApiOperation(value = "강의지원 강의 리스트 조회", notes = "")
    @ApiImplicitParams(value = {
         @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", required = true),
     })
    @GetMapping() List<SearchRes> getLectureSpptLecture (
        @Valid
        SearchReq dto){
        return service.getLectureSpptLecture(dto);
    }

    @ApiOperation(value = "강의지원 강의관리 - 저장 ", notes = "강의 저장")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "lectrSpptOgTpCd", value = "조직유형코드", required = true),
        @ApiImplicitParam(name = "lectrNm", value = "강의명", required = true),
        @ApiImplicitParam(name = "lectrSpptLectrCd", value = "강의코드"),
        @ApiImplicitParam(name = "useYn", value = "사용여부"),
    })
    @PostMapping
    public SaveResponse saveLectureSpptLecturer(
        @Valid
        @RequestBody
        List<SaveReq> reqs
    ) throws  Exception {
        return SaveResponse.builder().processCount(service.saveLectureSpptLecture(reqs)).build();
    }

}
