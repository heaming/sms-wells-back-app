package com.kyowon.sms.wells.web.competence.educations.rest;

import com.kyowon.sms.wells.web.competence.educations.dto.WpsbZoomMgtDto;
import com.kyowon.sms.wells.web.competence.educations.dto.WpsbZoomMgtDto.SaveReq;
import com.kyowon.sms.wells.web.competence.educations.dto.WpsbZoomMgtDto.SearchReq;
import com.kyowon.sms.wells.web.competence.educations.dto.WpsbZoomMgtDto.SearchRes;
import com.kyowon.sms.wells.web.competence.educations.service.WpsbZoomMgtService;
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

@Api(tags = "[WPSB]  wells 교육관리 알려zoom 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(PsCompetenceConst.REST_URL_V1 + "/zoom-counsel")
public class WpsbZoomMgtController {

    private final WpsbZoomMgtService service;

    @ApiOperation(value = " wells 교육관리 알려zoom menu 조회", notes = "wells 교육관리 알려zoom menu를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "rsbDvCd", value = "", paramType = "query", required = true),
    })
    @GetMapping("/trees")
    public List<SearchRes> getZooms(
        @Valid
        SearchReq dto
    ) {
        return service.getZooms(dto);
    }

    @ApiOperation(value = "wells 교육관리 알려zoom menu 저장", notes = "wells 교육관리 알려zoom menu를 저장한다.")
    @PostMapping
    public SaveResponse saveZoom(
        @RequestBody
        @Valid
        WpsbZoomMgtDto.SaveReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.saveZoom(dto))
            .build();
    }

    @ApiOperation(value = "wells 교육관리 알려zoom menu 저장", notes = "wells 교육관리 알려zoom menu를 저장한다.")
    @PostMapping("/zooms")
    public SaveResponse saveZoomList(
        @RequestBody
        @Valid
        List<SaveReq> req
    ) {
        return SaveResponse.builder()
            .processCount(service.saveZoomList(req))
            .build();
    }

    @ApiOperation(value = "wells 교육관리 알려zoom menu 삭제", notes = "wells 교육관리 알려zoom menu를 삭제한다.")
    @DeleteMapping
    public SaveResponse removeZoom(
        @RequestBody
        @Valid
        WpsbZoomMgtDto.RemoveReq dto
    ) {

        return SaveResponse.builder().processCount(service.removeZoom(dto)).build();

    }
}
