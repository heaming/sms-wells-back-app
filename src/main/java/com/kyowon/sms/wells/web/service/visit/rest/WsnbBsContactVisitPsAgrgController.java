package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsContactVisitPsAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsContactVisitPsAgrgDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbBsContactVisitPsAgrgService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNB] B/S 컨택방문현황 집계 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/bs-contact-visit-ps-agrg")
public class WsnbBsContactVisitPsAgrgController {

    private final WsnbBsContactVisitPsAgrgService service;

    @ApiOperation(value = "B/S 컨택방문현황 집계")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "배정년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "mngrDvCd", value = "관리구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getBsContactVisitPsAgrgs(
        @Valid
        SearchReq dto
    ) {
        return this.service.getBsContactVisitPsAgrgs(dto);
    }
}
