package com.kyowon.sms.wells.web.service.allocate.rest;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncBsEngineerAllocateSearchDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.service.WsncBsEngineerAllocateSearchService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WSNC] B/S배정현황(엔지니어)")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/bs-engineer-allocate")
@Slf4j
public class WsncBsEngineerAllocateSearchController {

    private final WsncBsEngineerAllocateSearchService service;

    @ApiOperation(value = "B/S배정현황(엔지니어)")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "배정년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query", required = false),
        @ApiImplicitParam(name = "engId", value = "엔지니어", paramType = "query", required = false),
        @ApiImplicitParam(name = "rgsnYn", value = "퇴사자제외", paramType = "query", required = false),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹", paramType = "query", required = false),
        @ApiImplicitParam(name = "svBizDclsfCd", value = "작업내용", paramType = "query", required = false),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getBsEngineerAllocateList(
        SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getBsEngineerAllocateList(dto, pageInfo);
    }

    @ApiOperation(value = "B/S배정현황(엔지니어) 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "배정년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query", required = false),
        @ApiImplicitParam(name = "engId", value = "엔지니어", paramType = "query", required = false),
        @ApiImplicitParam(name = "rgsnYn", value = "퇴사자제외", paramType = "query", required = false),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹", paramType = "query", required = false),
        @ApiImplicitParam(name = "svBizDclsfCd", value = "작업내용", paramType = "query", required = false),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getBsEngineerAllocateListExcelDownload(
        SearchReq dto
    ) {
        return service.getBsEngineerAllocateListExcelDownload(dto);
    }

    @ApiOperation(value = "B/S배정현황(엔지니어) 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "배정년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query", required = false),
        @ApiImplicitParam(name = "engId", value = "엔지니어", paramType = "query", required = false),
        @ApiImplicitParam(name = "rgsnYn", value = "퇴사자제외", paramType = "query", required = false),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹", paramType = "query", required = false),
        @ApiImplicitParam(name = "svBizDclsfCd", value = "작업내용", paramType = "query", required = false),
    })
    @GetMapping("/aggregate")
    public List<AggregateRes> getBsEngineerAllocateAggregate(
        SearchReq dto
    ) {
        return service.getBsEngineerAllocateAggregate(dto);
    }
}
