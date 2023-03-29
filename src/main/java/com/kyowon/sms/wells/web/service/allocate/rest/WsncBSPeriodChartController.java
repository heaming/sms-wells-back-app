package com.kyowon.sms.wells.web.service.allocate.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBSPeriodChartDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncBSPeriodChartService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNC] 정기 BS주기표를 생성")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/before-service-period-charts")
public class WsncBSPeriodChartController {

    private final WsncBSPeriodChartService service;

    @ApiOperation(value = "정기 BS주기표를 생성", notes = "조회조건에 따른 정기 BS주기표를 생성")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping("/paging")
    public SaveResponse processBSPeriodChart(WsncBSPeriodChartDto.SearchReq dto) throws Exception {
        return SaveResponse.builder().processCount(service.processBSPeriodChart(dto)).build();
    }

}
