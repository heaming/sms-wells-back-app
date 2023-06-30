package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbRentalAsPerformanceDto;
import com.kyowon.sms.wells.web.service.visit.service.WsnbRentalAsPerformanceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/rental-performance-cpr")
@Api(tags = "[WSNB] 렌탈 실적 대비 A/S율 집계 현황")
@RequiredArgsConstructor
public class WsnbRentalAsPerformanceController {

    private final WsnbRentalAsPerformanceService service;

    @ApiOperation(value = "렌탈 실적 대비 A/S율 집계 현황", notes = "조회조건에 일치하는 렌탈 실적 대비 A/S율 집계 현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseY", value = "기준년월", paramType = "query", example = "2023", required = true),
        @ApiImplicitParam(name = "svType", value = "서비스유형", paramType = "query", example = "3110"),
        @ApiImplicitParam(name = "badDivide", value = "불량구분", paramType = "query", example = "500R"),
        @ApiImplicitParam(name = "pdGrp", value = "상품그룹", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", example = "WM04100162")
    })
    @GetMapping("/aggregate")
    public List<WsnbRentalAsPerformanceDto.SearchRes> getRentalPerformanceCprAsPercent(
        @Valid
        WsnbRentalAsPerformanceDto.SearchReq dto
    ) {
        return this.service.getRentalPerformanceCprAs(dto);
    }
}
