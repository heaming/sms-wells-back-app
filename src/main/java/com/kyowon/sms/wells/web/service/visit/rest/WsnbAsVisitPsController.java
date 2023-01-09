package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbAsVisitPsDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbAsVisitPsDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbAsVisitPsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/as-visit-state")
@Api(tags = "[WSNB] 방문 관리 REST API")
@RequiredArgsConstructor
@Validated
public class WsnbAsVisitPsController {

    private final WsnbAsVisitPsService service;

    @ApiOperation(value = "상품별 서비스 처리 집계 현황 ", notes = "조회조건에 일치하는 상품별 서비스 처리 집계 현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "서비스센터", value = "ogId", paramType = "query", example = "OG00002"),
        @ApiImplicitParam(name = "처리일자 From", value = "wkExcnDtFrom", paramType = "query", example = "20221201", required = true),
        @ApiImplicitParam(name = "처리일자 To", value = "wkExcnDtTo", paramType = "query", example = "20221230", required = true),
        @ApiImplicitParam(name = "유/무상구분", value = "refriDvCd", paramType = "query", example = "1")
    })
    @GetMapping("/product-services")
    public List<SearchRes> getProductServices(@Valid
    SearchReq dto) {
        return this.service.getProductServices(dto);
    }

}
