package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbPersonInChargeVisitAgrgDto.FindBldRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbPersonInChargeVisitAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbPersonInChargeVisitAgrgDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbPersonInChargeVisitAgrgService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNB] 담당자별 정기방문 처리 집계표 조회 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/person-in-charge-visit-agrg")
public class WsnbPersonInChargeVisitAgrgController {

    private final WsnbPersonInChargeVisitAgrgService service;

    @ApiOperation(value = "담당자별 정기방문 처리 집계표 ", notes = "조회조건에 일치하는 담당자별 정기방문 처리 집계표 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "mngrDvCd", value = "관리구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "asnOjYm", value = "배정년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "startDt", value = "처리시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "처리종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query"),
        @ApiImplicitParam(name = "bldCd", value = "빌딩코드", paramType = "query"),
        @ApiImplicitParam(name = "exceptWellsManagerYn", value = "웰스매니저 미관리 제외", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getPersonInChargeVisitAgrgs(
        @Valid
        SearchReq dto
    ) {
        return service.getPersonInChargeVisitAgrgs(dto);
    }

    @ApiOperation(value = "빌딩 목록 조회", notes = "조회조건에 일치하는 정보를 조회한다.")
    @GetMapping("/buildings")
    public List<FindBldRes> getBuildings() {
        return service.getBuildings();
    }
}
