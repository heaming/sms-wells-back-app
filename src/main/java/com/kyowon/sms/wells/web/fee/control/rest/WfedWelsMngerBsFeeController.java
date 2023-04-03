package com.kyowon.sms.wells.web.fee.control.rest;

import java.util.List;

import javax.validation.Valid;

import com.sds.sflex.system.config.response.SaveResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.fee.control.dto.WfedWelsMngerBsFeeDto.*;
import com.kyowon.sms.wells.web.fee.control.service.WfedWelsMngerBsFeeService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/wm-bs-fees")
@Api(tags = "[WFED] WM 방문실적 및 수수료 관리")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WfedWelsMngerBsFeeController {
    private final WfedWelsMngerBsFeeService service;

    @ApiOperation(value = "WM방문실적 수수료관리 인사정보 조회", notes = "조회조건 실적년월에 해당하는 사번의 WM방문실적 수수료관리 인사정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/human-resources")
    public FindHumanRes getHuman(
        @Valid
        SearchReq dto
    ) {
        return service.getHuman(dto);
    }

    @ApiOperation(value = "WM방문실적 수수료관리 방문실적 목록 조회", notes = "조회조건 실적년월에 해당하는 사번의 WM방문실적 수수료관리 방문실적 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/visits")
    public List<SearchVisitRes> getVisits(
        SearchReq dto
    ) {
        return this.service.getVisits(dto);
    }

    @ApiOperation(value = "WM방문실적 수수료관리 수수료 목록 조회", notes = "조회조건 실적년월에 해당하는 사번의 WM방문실적 수수료관리 수수료 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/fees")
    public List<SearchFeeRes> getFees(
        SearchReq dto
    ) {
        return this.service.getFees(dto);
    }

    @ApiOperation(value = "WM방문실적 수수료관리 방문실적 집계정보 조회", notes = "조회조건 실적년월에 해당하는 사번의 WM방문실적 수수료관리 방문실적 집계정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/visit-aggregate")
    public FindVisitAgrgRes getVisitAgrg(
        SearchReq dto
    ) {
        return this.service.getVisitAgrg(dto);
    }

    @ApiOperation(value = "WM방문실적 수수료관리 수수료 목록 조회", notes = "조회조건 실적년월에 해당하는 사번의 WM방문실적 수수료관리 수수료 집계정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/fee-aggregate")
    public FindFeeAgrgRes getFeeAgrg(
        SearchReq dto
    ) {
        return this.service.getFeeAgrg(dto);
    }

    @ApiOperation(value = "수수료 내역 저장")
    @PostMapping
    public SaveResponse saveFee(
        @RequestBody
        @Valid
        List<SaveFeeReq> info
    ) {
        return SaveResponse.builder().processCount(service.saveFee(info)).build();
    }
}
