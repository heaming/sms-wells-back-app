package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsRcpListDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsRcpListDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.service.WsncAsRcpListInqrService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNC] AS접수 목록 조회")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/as-rcp-list-inqr")
@Validated
public class WsncAsRcpListInqrController {

    private final WsncAsRcpListInqrService service;

    @ApiOperation(value = "AS접수 목록 조회", notes = "조회조건에 일치하는 AS접수 목록 조회 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "procYn", value = "처리여부", paramType = "query"),
        @ApiImplicitParam(name = "rgsnYn", value = "퇴사자제외여부", paramType = "query"),
        @ApiImplicitParam(name = "rcpGb", value = "접수구분", paramType = "query"),
        @ApiImplicitParam(name = "mngrDvCd", value = "관리구분", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getAsRcpListInqrPages(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getAsRcpListInqrPages(dto, pageInfo);
    }

    @ApiOperation(value = "AS접수 목록 조회 목록 엑셀 다운로드", notes = "조회조건에 일치하는 AS접수 목록 조회 엑셀 다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "procYn", value = "처리여부", paramType = "query"),
        @ApiImplicitParam(name = "rgsnYn", value = "퇴사자제외여부", paramType = "query"),
        @ApiImplicitParam(name = "rcpGb", value = "접수구분", paramType = "query"),
        @ApiImplicitParam(name = "mngrDvCd", value = "관리구분", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getBondCollectionRelayServicesForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getAsRcpListInqrForExcelDownload(dto);
    }

}
