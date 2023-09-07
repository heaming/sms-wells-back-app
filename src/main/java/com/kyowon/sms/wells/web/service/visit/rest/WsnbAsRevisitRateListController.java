
package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbAsRevisitRateListDto;
import com.kyowon.sms.wells.web.service.visit.service.WsnbAsRevisitRateListService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/as-revisit-rate")
@Api(tags = "[WSNB] K-W-SV-U-0100M01 A/S 재방문율 조회 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnbAsRevisitRateListController {

    private final WsnbAsRevisitRateListService service;

    @ApiOperation(value = "A/S 처리 현황")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDateFrom", value = "조회일자from", paramType = "query", example = "20230601"),
        @ApiImplicitParam(name = "baseDateTo", value = "조회일자to", paramType = "query", example = "20230630"),
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query", example = "OGO198500002389"),
    })
    @GetMapping
    public List<WsnbAsRevisitRateListDto.SearchRes> getAsRevisitRateList(
        WsnbAsRevisitRateListDto.SearchReq dto
    ) {
        return service.getAsRevisitRateList(dto);
    }
}
