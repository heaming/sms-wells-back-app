
package com.kyowon.sms.wells.web.service.common.rest;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.common.dto.WsnyAsVisitCostMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyAsVisitCostMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyAsVisitCostMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.common.service.WsnyAsVisitCostMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/as-visit-costs")
@Api(tags = "[WSNY] W-SV-U-0101M01 유상 AS 출장비 관리 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnyAsVisitCostMgtController {

    private final WsnyAsVisitCostMgtService service;

    @ApiOperation(value = "유상 AS 출장비 관리")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", example = "4103000000"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getAsVisitCostPages(
        SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getAsVisitCostPages(dto, pageInfo);
    }

    @ApiOperation(value = "유상 AS 출장비 관리 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", example = "4103000000"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getAsVisitCostForExcelDownload(
        SearchReq dto
    ) {
        return service.getAsVisitCostForExcelDownload(dto);
    }

    @ApiOperation(value = "유상 AS 출장비 관리 저장")
    @PutMapping
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "izSn", value = "내역일련번호", paramType = "query"),
        @ApiImplicitParam(name = "bstrCsAmt", value = "비용", paramType = "query"),
        @ApiImplicitParam(name = "apyStrtdt", value = "유효시작일시", paramType = "query"),
        @ApiImplicitParam(name = "apyEnddt", value = "유효종료일시", paramType = "query"),
        @ApiImplicitParam(name = "rmkCn", value = "비고내용", paramType = "query"),
    })
    public SaveResponse saveAsVisitCosts(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> rowData
    ) throws ParseException {
        return SaveResponse.builder().processCount(service.saveAsVisitCosts(rowData)).build();
    }

}
