package com.kyowon.sms.wells.web.service.common.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.common.dto.WsnyCalendarDto;
import com.kyowon.sms.wells.web.service.common.service.WsnyCalendarService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNF] Calendar 관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/calendar-management")
public class WsnyCalendarController {
    private final WsnyCalendarService wsnyCalendarService;

    @ApiOperation(value = "Calendar 관리 화면 - Calendar 관리 조회", notes = "조회조건에 따른 Calendar 관리 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "serviceCenterCd", value = "서비스센터", paramType = "query", required = false),
    })
    @GetMapping
    public List<WsnyCalendarDto.SearchRes> getCalendars(
        WsnyCalendarDto.SearchReq dto
    ) {
        return wsnyCalendarService.getCalendars(dto);
    }

    @ApiOperation(value = "Calendar 등록 팝업 - Calendar 등록 및 수정", notes = "Calendar 등록")
    @PostMapping
    public SaveResponse saveCalendar(
        @Valid
        @RequestBody
        WsnyCalendarDto.SaveRegReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(wsnyCalendarService.saveCalendar(dto))
            .build();
    }

    @ApiOperation(value = "Calendar 등록 팝업 - Calendar Day 조회", notes = "조회조건에 따른 Calendar Day 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "serviceCenterCd", value = "서비스센터", paramType = "query", required = false),
        @ApiImplicitParam(name = "baseY", value = "기준년", paramType = "query", required = false),
        @ApiImplicitParam(name = "baseMm", value = "기준월", paramType = "query", required = false),
        @ApiImplicitParam(name = "baseD", value = "기준일", paramType = "query", required = false),
    })
    @GetMapping("/day")
    public WsnyCalendarDto.SearchRegRes getCalendarDay(
        WsnyCalendarDto.SearchRegReq dto
    ) {
        return wsnyCalendarService.getCalendarDay(dto);
    }
}
