
package com.kyowon.sms.wells.web.service.allocate.rest;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncIntergratedQrProcsListSearchDto.SearchAggrReq;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncIntergratedQrProcsListSearchDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncIntergratedQrProcsListSearchService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/intergrated-qr-aggregate")
@Api(tags = "[WSNC] K-W-SV-U-0222M01 통합QR 집계표 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsncIntergratedQrAggregateListSearchController {

    private final WsncIntergratedQrProcsListSearchService service;

    @ApiOperation(value = "통합QR 집계표")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYear", value = "기준년도", paramType = "query", example = "2023"),
    })
    @GetMapping
    public List<WsncIntergratedQrProcsListSearchDto.SearchAggrRes> getAggrList(
        SearchAggrReq dto
    ) {
        return service.getAggrList(dto);
    }

}
