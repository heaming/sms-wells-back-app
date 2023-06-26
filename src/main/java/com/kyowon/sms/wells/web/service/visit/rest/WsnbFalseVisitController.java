package com.kyowon.sms.wells.web.service.visit.rest;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbFalseVisitDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFalseVisitDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbFalseVisitService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/false-visit")
@Api(tags = "[WSNB] 허위방문 등록현황 REST API")
@RequiredArgsConstructor
@Validated
public class WsnbFalseVisitController {
    private final WsnbFalseVisitService service;

    @ApiOperation(value = "허위방문 등록현황 조회", notes = "허위방문 등록현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "toDate", value = "기간", paramType = "query", example = "20230601"),
        @ApiImplicitParam(name = "fromDate", value = "기간", paramType = "query", example = "20230601"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getFalseVisits(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getFalseVisits(dto, pageInfo);
    }
}
