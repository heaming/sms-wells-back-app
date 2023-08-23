package com.kyowon.sms.wells.web.service.visit.rest;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbMobileHappycallSearchDto.SearchReq;
import static com.kyowon.sms.wells.web.service.visit.dto.WsnbMobileHappycallSearchDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.service.WsnbMobileHappycallSearchService;
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
@RequestMapping(SnServiceConst.REST_URL_V1 + "/mobile-happycall")
@Api(tags = "[WSNB] 모바일해피콜 현황 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnbMobileHappycallSearchController {

    private final WsnbMobileHappycallSearchService service;

    @ApiOperation(value = "모바일해피콜 현황 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "svcTpCd", value = "기준년월", paramType = "query", example = "202306"),
        @ApiImplicitParam(name = "ogId", value = "조직id", paramType = "query", example = "OGO202000010295"),
        @ApiImplicitParam(name = "engId", value = "엔지니어id", paramType = "query", example = "36680"),
        @ApiImplicitParam(name = "inqrBase", value = "조회기준", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "baseDateFrom", value = "기준일자 From", paramType = "query", example = "20230601"),
        @ApiImplicitParam(name = "baseDateTo", value = "기준일자 To", paramType = "query", example = "20230615"),
        @ApiImplicitParam(name = "rgsnYn", value = "퇴사자여부", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "acpnWrkInc", value = "동행건포함", paramType = "query", example = "N"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getMobileHappycallSearchPages(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getMobileHappycallSearchPages(dto, pageInfo);
    }

    @ApiOperation(value = "모바일해피콜 현황 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "svcTpCd", value = "기준년월", paramType = "query", example = "202306"),
        @ApiImplicitParam(name = "ogId", value = "조직id", paramType = "query", example = "OGO202000010295"),
        @ApiImplicitParam(name = "engId", value = "엔지니어id", paramType = "query", example = "36680"),
        @ApiImplicitParam(name = "inqrBase", value = "조회기준", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "baseDateFrom", value = "기준일자 From", paramType = "query", example = "20230601"),
        @ApiImplicitParam(name = "baseDateTo", value = "기준일자 To", paramType = "query", example = "20230615"),
        @ApiImplicitParam(name = "rgsnYn", value = "퇴사자여부", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "acpnWrkInc", value = "동행건포함", paramType = "query", example = "N"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getMobileHappycallSearchExcelDownload(SearchReq dto) {
        return service.getMobileHappycallSearchExcelDownload(dto);
    }

}
