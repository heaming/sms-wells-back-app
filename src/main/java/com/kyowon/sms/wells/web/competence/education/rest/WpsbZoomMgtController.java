package com.kyowon.sms.wells.web.competence.education.rest;

import static com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMgtDto.SearchReq;
import static com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMgtDto.SearchRes;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMgtDto;
import com.kyowon.sms.wells.web.competence.education.service.WpsbZoomMgtService;
import com.kyowon.sms.wells.web.competence.zcommon.psCompetenceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
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
public class WpsbZoomMgtController {

    private final WpsbZoomMgtService service;

    @ApiOperation(value = " wells 교육관리 알려zoom 관리 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getZoomMgtPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getZoomMgtPages(dto, pageInfo);
    }

    @ApiOperation(value = " wells 교육관리 알려zoom menu 조회", notes = "wells 교육관리 알려zoom menu를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/tree-list")
    public List<SearchRes> getZoomMgts(
        @Valid
        SearchReq dto
    ) {
        return service.selectZoomMgtPages(dto);
    }

    @ApiOperation(value = "wells 교육관리 알려zoom menu 저장", notes = "wells 교육관리 알려zoom menu를 저장한다.")
    @PostMapping
    public SaveResponse saveZoomMgt(
        @RequestBody
        @Valid
        WpsbZoomMgtDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveZoomMgt(dto))
            .build();
    }

    @ApiOperation(value = "wells 교육관리 알려zoom menu 저장", notes = "wells 교육관리 알려zoom menu를 저장한다.")
    @PostMapping("/treeSave")
    public SaveResponse pageSaveZoomMgt(
        @RequestBody
        @Valid
        @NotEmpty
        List<WpsbZoomMgtDto.SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveAllZoomMgt(dtos))
            .build();
    }

    @ApiOperation(value = "wells 교육관리 알려zoom menu 삭제", notes = "wells 교육관리 알려zoom menu를 삭제한다.")
    @DeleteMapping
    public SaveResponse removeZoomMgt(
        @RequestBody
        @Valid
        WpsbZoomMgtDto.RemoveReq dto
    ) {

        return SaveResponse.builder().processCount(service.removeZoomMgt(dto)).build();

    }
}
