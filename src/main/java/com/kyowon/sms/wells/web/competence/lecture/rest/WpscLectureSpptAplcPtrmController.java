package com.kyowon.sms.wells.web.competence.lecture.rest;

import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptAplcPtrmDto.*;
import com.kyowon.sms.wells.web.competence.lecture.service.WpscLectureSpptAplcPtrmService;
import com.kyowon.sms.wells.web.competence.zcommon.psCompetenceConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "[PSC] 강의지원 신청기간등록")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(psCompetenceConst.REST_URL_V1 + "/lecture-sppt-aplc-ptrm")
public class WpscLectureSpptAplcPtrmController {

    private final WpscLectureSpptAplcPtrmService service;

    @ApiOperation(value = "강의지원 신청기간등록 - 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", required = true),
        @ApiImplicitParam(name = "lectrYm", value = "강의년월", required = true),
    })
    @GetMapping
    public SearchRes getLectureSpptAplcPtrm(
        @Valid
        SearchReq dto
    ) {
        return service.getLectureSpptAplcPtrm(dto);
    }


    @ApiOperation(value = "강의지원 신청기간등록 - 저장 ", notes = "신청기간 저장")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "lectrSpptOgTpCd", value = "강의지원조직유형코드", required = true),
        @ApiImplicitParam(name = "lectrYm", value = "강의년월", required = true),
        @ApiImplicitParam(name = "aplcStrtdt", value = "신청시작일자", required = true),
        @ApiImplicitParam(name = "aplcStrtHm", value = "신청시작시분", required = true),
        @ApiImplicitParam(name = "aplcEnddt", value = "신청종료일자", required = true),
        @ApiImplicitParam(name = "aplcEndHm", value = "신청종료시분", required = true),
    })
    @PostMapping
    public SaveResponse saveLectureSpptAplcPtrm(
        @Valid
        @RequestBody
        SaveReq req
    ) throws  Exception {
        return SaveResponse.builder().processCount(service.saveLectureSpptAplcPtrm(req)).build();
    }

    @ApiOperation(value = "강의지원 신청기간등록 - 취소 ", notes = "신청기간 취소")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "lectrSpptOgTpCd", value = "강의지원조직유형코드", required = true),
        @ApiImplicitParam(name = "lectrYm", value = "강의년월", required = true),
    })
    @PutMapping
    public SaveResponse editLectureSpptAplcPtrmStatus(
        @Valid
        @RequestBody
        deleteReq req
    ) throws  Exception {
        return SaveResponse.builder().processCount(service.editLectureSpptAplcPtrmStatus(req)).build();
    }
}
