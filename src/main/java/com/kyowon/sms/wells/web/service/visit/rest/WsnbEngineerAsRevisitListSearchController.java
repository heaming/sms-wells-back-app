package com.kyowon.sms.wells.web.service.visit.rest;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbEngineerAsRevisitListSearchDto.SearchReq;
import static com.kyowon.sms.wells.web.service.visit.dto.WsnbEngineerAsRevisitListSearchDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.service.WsnbEngineerAsRevisitListSearchService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/engineer-as-revisit-list")
@Api(tags = "[WSNB] A/S 재방문현황(엔지니어) REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnbEngineerAsRevisitListSearchController {

    private final WsnbEngineerAsRevisitListSearchService service;

    @ApiOperation(value = "A/S 재방문현황(엔지니어) 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDateFrom", value = "기준일자 From", paramType = "query", example = "20230601"),
        @ApiImplicitParam(name = "baseDateTo", value = "기준일자 To", paramType = "query", example = "20230615"),
        @ApiImplicitParam(name = "acuBck", value = "누적구간", paramType = "query", example = "30"),
        @ApiImplicitParam(name = "ogId", value = "조직id", paramType = "query", example = "OGO202000010295"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getEngineerAsRevisitListSearchPages(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getEngineerAsRevisitListSearchPages(dto, pageInfo);
    }

    @ApiOperation(value = "A/S 재방문현황(엔지니어) 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDateFrom", value = "기준일자 From", paramType = "query", example = "20230601"),
        @ApiImplicitParam(name = "baseDateTo", value = "기준일자 To", paramType = "query", example = "20230615"),
        @ApiImplicitParam(name = "acuBck", value = "누적구간", paramType = "query", example = "30"),
        @ApiImplicitParam(name = "ogId", value = "조직id", paramType = "query", example = "OGO202000010295"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getEngineerAsRevisitListSearchExcelDownload(SearchReq dto) {
        return service.getEngineerAsRevisitListSearchExcelDownload(dto);
    }

}
