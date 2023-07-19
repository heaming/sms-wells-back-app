
package com.kyowon.sms.wells.web.service.common.rest;

import static com.kyowon.sms.wells.web.service.common.dto.WsnyErrorCodeMgtDto.DeleteReq;
import static com.kyowon.sms.wells.web.service.common.dto.WsnyErrorCodeMgtDto.SaveReq;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.common.dto.WsnyErrorCodeMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyErrorCodeMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.common.service.WsnyErrorCodeMgtService;
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
@RequestMapping(SnServiceConst.REST_URL_V1 + "/error-code")
@Api(tags = "[WSNY] K-W-SV-U-0304M01 상품별 에러코드 관리 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnyErrorCodeMgtController {

    private final WsnyErrorCodeMgtService service;

    @ApiOperation(value = "상품별 에러코드 관리")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹코드", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", example = "WM01100137"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getErrorCodePages(
        SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getErrorCodePages(dto, pageInfo);
    }

    @ApiOperation(value = "상품별 에러코드 관리 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹코드", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", example = "WM01100137"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getErrorCodeForExcelDownload(
        SearchReq dto
    ) {
        return service.getErrorCodeForExcelDownload(dto);
    }

    @ApiOperation(value = "에러코드 삭제")
    @DeleteMapping
    public SaveResponse deleteErrorCode(
        @Valid
        @RequestBody
        @NotEmpty
        List<DeleteReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(this.service.deleteErrorCode(dtos))
            .build();
    }

    @ApiOperation(value = "에러코드 등록")
    @PostMapping
    public SaveResponse saveErrorCode(
        @RequestBody
        SaveReq req
    ) throws Exception {
        return SaveResponse.builder().processCount(service.saveErrorCode(req)).build();
    }
}
