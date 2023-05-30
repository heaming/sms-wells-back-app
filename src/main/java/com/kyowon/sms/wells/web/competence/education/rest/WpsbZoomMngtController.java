package com.kyowon.sms.wells.web.competence.education.rest;

import static com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMngtDto.SearchReq;
import static com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMngtDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMngtDto;
import com.kyowon.sms.wells.web.competence.education.service.WpsbZoomMngtService;
import com.kyowon.sms.wells.web.competence.zcommon.psCompetenceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[]  wells 교육관리 알려zoom 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(psCompetenceConst.REST_URL_V1 + "/zoom-counsel")
public class WpsbZoomMngtController {

    private final WpsbZoomMngtService service;

    @ApiOperation(value = " wells 교육관리 알려zoom menu 조회", notes = "wells 교육관리 알려zoom menu를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "rsbDvCd", value = "", paramType = "query", required = true),
    })
    @GetMapping("/trees")
    public List<SearchRes> getZooms(
        @Valid
        SearchReq dto
    ) {
        return service.selectZooms(dto);
    }

    @ApiOperation(value = "wells 교육관리 알려zoom menu 저장", notes = "wells 교육관리 알려zoom menu를 저장한다.")
    @PostMapping
    public SaveResponse saveZoom(
        @RequestBody
        @Valid
        WpsbZoomMngtDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveZoom(dto))
            .build();
    }

    @ApiOperation(value = "wells 교육관리 알려zoom menu 저장", notes = "wells 교육관리 알려zoom menu를 저장한다.")
    @PostMapping("/zooms")
    public SaveResponse saveAllZoom(
        @RequestBody
        @Valid
        WpsbZoomMngtDto.EditReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveAllZoom(dto))
            .build();
    }

    @ApiOperation(value = "wells 교육관리 알려zoom menu 삭제", notes = "wells 교육관리 알려zoom menu를 삭제한다.")
    @DeleteMapping
    public SaveResponse removeZoom(
        @RequestBody
        @Valid
        WpsbZoomMngtDto.RemoveReq dto
    ) {

        return SaveResponse.builder().processCount(service.removeZoom(dto)).build();

    }
}
