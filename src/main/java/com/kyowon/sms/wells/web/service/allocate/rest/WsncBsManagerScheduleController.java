package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsManagerScheduleDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncBsManagerScheduleService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNC] BS관리일정 조회 ")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/bs-manager-schedule")
public class WsncBsManagerScheduleController {
    private final WsncBsManagerScheduleService service;

    @ApiOperation(value = "BS관리일정 조회 화면(일자별) - 집계 조회", notes = "조회조건에 따른 BS관리일정(일자별) 집계 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "fxnPrtnrNo", value = "파트너사번", paramType = "query", required = false),
        @ApiImplicitParam(name = "baseDateFrom", value = "관리년월From", paramType = "query", required = true),
        @ApiImplicitParam(name = "baseDateTo", value = "관리년월To", paramType = "query", required = true),
    })
    @GetMapping("/dt-aggregates")
    public List<WsncBsManagerScheduleDto.Aggregates> getBsScheduleDateAgrg(
        WsncBsManagerScheduleDto.SearchReq dto
    ) {
        return service.getBsScheduleDateAgrg(dto);
    }

    @ApiOperation(value = "BS관리일정 조회 화면(일자별) - 상세 조회", notes = "조회조건에 따른 BS관리일정(일자별) 상세 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "fxnPrtnrNo", value = "파트너사번", paramType = "query", required = false),
        @ApiImplicitParam(name = "baseDateFrom", value = "관리년월From", paramType = "query", required = true),
        @ApiImplicitParam(name = "baseDateTo", value = "관리년월To", paramType = "query", required = true),
    })
    @GetMapping("/dt-paging")
    public List<WsncBsManagerScheduleDto.SearchRes> getBsScheduleDatePages(
        WsncBsManagerScheduleDto.SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getBsScheduleDatePages(dto, pageInfo);
    }
}
