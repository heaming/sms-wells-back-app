package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsManagerScheduleDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBsManagerScheduleDvo;
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

    @ApiOperation(value = "BS관리일정 조회 화면(일자별) - 집계 조회", notes = "조회조건에 따른 BS관리일정(일자별) 집계 조회 일자별로 화면명 변경")
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

    @ApiOperation(value = "BS관리일정 조회 화면(일자별) - 리스트 조회 페이징", notes = "조회조건에 따른 BS관리일정(일자별) 리스트 페이징")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "fxnPrtnrNo", value = "파트너사번", paramType = "query", required = false),
        @ApiImplicitParam(name = "baseDateFrom", value = "관리년월From", paramType = "query", required = true),
        @ApiImplicitParam(name = "baseDateTo", value = "관리년월To", paramType = "query", required = true),
    })
    @GetMapping("/dt-paging")
    public List<WsncBsManagerScheduleDvo> getBsScheduleDatePages(
        WsncBsManagerScheduleDto.SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getBsScheduleDatePages(dto, pageInfo);
    }

    @ApiOperation(value = "BS관리일정 조회 화면(주차별) - 주차 리스트 조회", notes = "주차별 날짜 리스트 공통코드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "managementYear", value = "관리년월", paramType = "query", required = true),
    })
    @GetMapping("/weeks-items")
    public List<WsncBsManagerScheduleDto.Weekres> getWeeksCodesLists(
        WsncBsManagerScheduleDto.Weekreq dto
    ) {
        return service.getWeeksCodes(dto);
    }

    @ApiOperation(value = "BS관리일정 조회 화면(주차별) - 상세 조회", notes = "조회조건에 따른 BS관리일정(일자별) 상세 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "fxnPrtnrNo", value = "파트너사번", paramType = "query", required = true),
        @ApiImplicitParam(name = "weekDay1", value = "선택주차첫날", paramType = "query", required = true),
        @ApiImplicitParam(name = "weekDay2", value = "선택주차둘째날", paramType = "query", required = false),
        @ApiImplicitParam(name = "weekDay3", value = "선택주차셋째날", paramType = "query", required = false),
        @ApiImplicitParam(name = "weekDay4", value = "선택주차넷째날", paramType = "query", required = false),
        @ApiImplicitParam(name = "weekDay5", value = "선택주차다섯째날", paramType = "query", required = false),
        @ApiImplicitParam(name = "weekDay6", value = "선택주차여섯째날", paramType = "query", required = false),
        @ApiImplicitParam(name = "weekDay7", value = "선택주차일곱째날", paramType = "query", required = false),
    })
    @GetMapping("/details")
    public List<WsncBsManagerScheduleDto.Detailres> getBsScheduleDateDetail(
        WsncBsManagerScheduleDto.Detailreq dto
    ) {
        return service.getBsScheduleDateDetail(dto);
    }

}
