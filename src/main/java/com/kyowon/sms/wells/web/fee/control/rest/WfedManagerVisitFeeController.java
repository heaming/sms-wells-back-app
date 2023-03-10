package com.kyowon.sms.wells.web.fee.control.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.fee.control.dto.WfedManagerVisitFeeDto;
import com.kyowon.sms.wells.web.fee.control.service.WfedManagerVisitFeeService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/manager-visit-fees")
@Api(tags = "[WFED] 웰스매니저 방문수수료 현황")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WfedManagerVisitFeeController {

    private final WfedManagerVisitFeeService service;

    @ApiOperation(value = "웰스매니저 방문수수료 조회", notes = "조회조건에 일치하는 실적년월에 생성된 웰스매니저 방문수수료 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "inqrDv", value = "조회구분", paramType = "query", example = "총괄단", required = true),
        @ApiImplicitParam(name = "ogLevl1", value = "조직레벨1", paramType = "query", example = "전체"),
        @ApiImplicitParam(name = "ogLevl2", value = "조직레벨2", paramType = "query", example = "전체"),
        @ApiImplicitParam(name = "ogLevl3", value = "조직레벨3", paramType = "query", example = "전체"),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "123456"),
    })
    @GetMapping
    public List<WfedManagerVisitFeeDto.SearchRes> getManagerVisitFees(WfedManagerVisitFeeDto.SearchReq dto) {
        return this.service.getManagerVisitFees(dto);
    }

}
