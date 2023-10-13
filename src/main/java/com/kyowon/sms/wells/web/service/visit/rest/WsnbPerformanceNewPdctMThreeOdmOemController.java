package com.kyowon.sms.wells.web.service.visit.rest;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbPerformanceNewPdctMThreeOdmOemDto.*;
import com.kyowon.sms.wells.web.service.visit.service.WsnbPerformanceNewPdctMThreeOdmOemService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/performance-new-pdct-m-three-odm-oem")
@Api(tags = "[WSNB] 실적_신제품 M+3 ODM/OEM")
@RequiredArgsConstructor
@Validated
public class WsnbPerformanceNewPdctMThreeOdmOemController {
    private final WsnbPerformanceNewPdctMThreeOdmOemService service;

    @ApiOperation(value = "실적_신제품 M+3 ODM/OEM")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseY", value = "기준년월", paramType = "query", example = "2023"),
        @ApiImplicitParam(name = "serviceTypes", value = "서비스유형", paramType = "query", example = "3110"),
        @ApiImplicitParam(name = "badDvCd", value = "불량구분", paramType = "query", example = "500R"),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", example = "WM04100162")
    })
    @GetMapping
    public List<SearchRes> getPerformanceNewPdctMThreeOdmOemList(SearchReq dto){
        return service.getPerformanceNewPdctMThreeOdmOemList(dto);
    }
}
