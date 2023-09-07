package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterUseAgrgDto.FindFilterProductRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterUseAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterUseAgrgDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaFilterUseAgrgService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 필터사용집계현황 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/filter-use-agrg")
public class WsnaFilterUseAgrgController {

    private final WsnaFilterUseAgrgService service;

    @GetMapping("/filter-products")
    @ApiOperation(value = "필터 상품목록 조회", notes = "조회조건에 해당하는 필터 상품목록을 조회한다.")
    public List<FindFilterProductRes> getFilterProducts(
        @Valid
        SearchReq dto
    ) {
        return this.service.getFilterProducts(dto);
    }

    @ApiOperation(value = "필터사용집계현황 목록", notes = "조회조건에 일치하는 필터사용집계현황 목록 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "출고시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "출고종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "mngrDvCd", value = "관리자구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹코드", paramType = "query"),
        @ApiImplicitParam(name = "svBizHclsfCd", value = "서비스업무대분류코드", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query"),
        @ApiImplicitParam(name = "rgsnYn", value = "퇴사자제외 여부", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getFilterUseAgrgs(
        @Valid
        SearchReq dto
    ) {
        return service.getFilterUseAgrgs(dto);
    }
}
