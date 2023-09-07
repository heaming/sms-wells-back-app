package com.kyowon.sms.wells.web.service.allocate.rest;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncAsEngineerAllocateSearchDto.SearchReq;
import static com.kyowon.sms.wells.web.service.allocate.dto.WsncAsEngineerAllocateSearchDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.service.WsncAsEngineerAllocateSearchService;
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
@RequestMapping(SnServiceConst.REST_URL_V1 + "/as-engineer-allocate")
@Api(tags = "[WSNC] 엔지니어 배정현황 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsncAsEngineerAllocateSearchController {

    private final WsncAsEngineerAllocateSearchService service;

    @ApiOperation(value = "엔지니어 배정현황 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogId", value = "조직id", paramType = "query", example = "OGO202000010295"),
        @ApiImplicitParam(name = "engId", value = "엔지니어id", paramType = "query", example = "36680"),
        @ApiImplicitParam(name = "rgsnYn", value = "퇴사자여부", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "svDvCd", value = "작업구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "prgsDvCd", value = "진행구분", paramType = "query", example = "00"),
        @ApiImplicitParam(name = "baseDateFrom", value = "기준일자 From", paramType = "query", example = "20230601"),
        @ApiImplicitParam(name = "baseDateTo", value = "기준일자 To", paramType = "query", example = "20230615"),
        @ApiImplicitParam(name = "vstCfrmDt", value = "방문확정일자", paramType = "query", example = "20230615"),
        @ApiImplicitParam(name = "cfrmOnlyYn", value = "방문확정일자로만 조회", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "istConfCd", value = "설치확인", paramType = "query", example = "1"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getAsEngineerAllocateSearchPages(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getAsEngineerAllocateSearchPages(dto, pageInfo);
    }

    @ApiOperation(value = "엔지니어 배정현황 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogId", value = "조직id", paramType = "query", example = "OGO202000010295"),
        @ApiImplicitParam(name = "engId", value = "엔지니어id", paramType = "query", example = "36680"),
        @ApiImplicitParam(name = "rgsnYn", value = "퇴사자여부", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "svDvCd", value = "작업구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "prgsDvCd", value = "진행구분", paramType = "query", example = "00"),
        @ApiImplicitParam(name = "baseDateFrom", value = "기준일자 From", paramType = "query", example = "20230601"),
        @ApiImplicitParam(name = "baseDateTo", value = "기준일자 To", paramType = "query", example = "20230615"),
        @ApiImplicitParam(name = "vstCfrmDt", value = "방문확정일자", paramType = "query", example = "20230615"),
        @ApiImplicitParam(name = "cfrmOnlyYn", value = "방문확정일자로만 조회", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "istConfCd", value = "설치확인", paramType = "query", example = "1"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getAsEngineerAllocateSearchExcelDownload(SearchReq dto) {
        return service.getAsEngineerAllocateSearchExcelDownload(dto);
    }

}
