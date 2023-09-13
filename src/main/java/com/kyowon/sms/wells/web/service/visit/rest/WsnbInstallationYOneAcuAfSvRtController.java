package com.kyowon.sms.wells.web.service.visit.rest;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallationYOneAcuAfSvRtDto.*;
import com.kyowon.sms.wells.web.service.visit.service.WsnbInstallationYOneAcuAfSvRtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/installation-yone-acu-af-sv-rt")
@Api(tags = "[WSNB] 실적_설치1년누적AS율")
@RequiredArgsConstructor
@Validated
public class WsnbInstallationYOneAcuAfSvRtController {
    private final WsnbInstallationYOneAcuAfSvRtService service;

    @ApiOperation(value = "실적_설치1년누적AS율 목록조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseY", value = "기준년월", paramType = "query", example = "2023"),
        @ApiImplicitParam(name = "svType", value = "서비스유형", paramType = "query", example = "3110"),
        @ApiImplicitParam(name = "badDivide", value = "불량구분", paramType = "query", example = "500R"),
        @ApiImplicitParam(name = "pdGrp", value = "상품그룹", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", example = "WM04100162")
    })
    @GetMapping("/installationOneYearAcuAfSvRtInfo")
    public List<SearchRes> getInstallationYOneAcuAfSvRtInfo(SearchReq dto){
        return this.service.getInstallationYOneAcuAfSvRtInfo(dto);
    }
}
