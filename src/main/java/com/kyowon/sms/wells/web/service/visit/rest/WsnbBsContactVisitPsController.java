package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsContactVisitPsDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsContactVisitPsDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbBsContactVisitPsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNB] B/S 컨택방문현황 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/bs-contact-visit-ps")
public class WsnbBsContactVisitPsController {

    private final WsnbBsContactVisitPsService service;

    @ApiOperation(value = "B/S 컨택방문현황")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "asnOjYm", value = "배정년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "총괄단", paramType = "query", required = true),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "지역단", paramType = "query", required = true),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getBsContactVisitPsPages(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getBsContactVisitPsPages(dto, pageInfo);
    }

    @GetMapping("/excel-download")
    @ApiOperation(value = "B/S 컨택방문현황 엑셀 다운로드", notes = "조회조건에 해당하는 B/S 컨택방문현황을 엑셀 다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "asnOjYm", value = "배정년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "총괄단", paramType = "query", required = true),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "지역단", paramType = "query", required = true),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
    })
    public List<SearchRes> getBsContactVisitPsExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return this.service.getBsContactVisitPsExcelDownload(dto);
    }
}
