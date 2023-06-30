package com.kyowon.sms.wells.web.service.visit.rest;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.service.visit.service.WsnbWellsManagerIchrExcdService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsManagerIchrExcdDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsManagerIchrExcdDto.SearchRes;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/wells-manager-incharge-excd")
@Api(tags = "[WSNB] 웰스매니저 미관리계정 현황 REST API")
@RequiredArgsConstructor
@Validated
public class WsnbWellsManagerIchrExcdController {
    private final WsnbWellsManagerIchrExcdService service;

    @ApiOperation(value = "웰스매니저 미관리계정 현황", notes = "웰스매니저 미관리계정 현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "mngtYm", value = "관리년월", paramType = "query", example = ""),
        @ApiImplicitParam(name = "fromAdrZip", value = "우편번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "toAdrZip", value = "우편번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "총괄단", paramType = "query", example = ""),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "지역단", paramType = "query", example = ""),
        @ApiImplicitParam(name = "accountDivCd", value = "계정구분", paramType = "query", example = ""),
        @ApiImplicitParam(name = "exceptWellsFarmYn", value = "검색제외: 웰스팜(새싹재배기 포함)", paramType = "query", example = ""),
        @ApiImplicitParam(name = "exceptFixYn", value = "검색제외: 강제배정", paramType = "query", example = ""),
        @ApiImplicitParam(name = "exceptEgerWkYn", value = "검색제외: 고정등록", paramType = "query", example = ""),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getWellsManagerInchargeExcds(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getWellsManagerInchargeExcds(dto, pageInfo);
    }

    @ApiOperation(value = "웰스매니저 미관리계정 현황 (엑셀 다운로드)", notes = "웰스매니저 미관리계정 현황을 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getWellsManagerInchargeExcdsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getWellsManagerInchargeExcdsForExcelDownload(dto);
    }
}
