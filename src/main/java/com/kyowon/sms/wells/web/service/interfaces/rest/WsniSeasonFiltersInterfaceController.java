package com.kyowon.sms.wells.web.service.interfaces.rest;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSeasonFiltersInterfaceDto;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniSeasonFiltersInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1)
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/season-filters")
@RequiredArgsConstructor
@Validated
public class WsniSeasonFiltersInterfaceController {

    private final WsniSeasonFiltersInterfaceService service;

    @ApiOperation(value = "W-SV-I-0007 웰스홈페이지 필터정보 조회", notes = "W-SV-I-0007 웰스홈페이지 필터정보 조회")
    @GetMapping
    public EaiWrapper getBsServiceHistories(
        @Valid
        @RequestBody
        EaiWrapper<WsniSeasonFiltersInterfaceDto.SearchReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<List<WsniSeasonFiltersInterfaceDto.SearchRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.selectSeasonFilter(reqWrapper.getBody()));
        return resWrapper;
    }
}
