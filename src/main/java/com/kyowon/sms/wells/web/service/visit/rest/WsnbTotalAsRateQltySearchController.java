
package com.kyowon.sms.wells.web.service.visit.rest;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbTotalAsRateQltySearchDto.SearchReq;
import static com.kyowon.sms.wells.web.service.visit.dto.WsnbTotalAsRateQltySearchDto.SearchRes;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.service.WsnbTotalAsRateQltySearchService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/total-as-rate-qlty")
@Api(tags = "[WSNB] K-W-SV-U-0248M01 총 A/S율 현황(품질) REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnbTotalAsRateQltySearchController {

    private final WsnbTotalAsRateQltySearchService service;

    @ApiOperation(value = "총 A/S율 현황(품질)")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYear", value = "기준년월", paramType = "query", example = "2023"),
        @ApiImplicitParam(name = "taskTypeCd", value = "서비스유형", paramType = "query", example = "3310"),
        @ApiImplicitParam(name = "badDvCd", value = "불량구분", paramType = "query", example = "300R"),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹", paramType = "query", example = "93"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", example = "WM01100137"),
    })
    @GetMapping
    public List<SearchRes> getTaskTypeItemList(
        SearchReq dto
    ) {
        return service.getTotalAsRateQltyList(dto);
    }
}
