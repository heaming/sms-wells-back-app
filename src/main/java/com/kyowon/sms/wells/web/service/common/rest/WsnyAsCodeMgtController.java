package com.kyowon.sms.wells.web.service.common.rest;

import com.kyowon.sms.wells.web.service.common.dto.WsnyAsCodeMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyAsCodeMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.common.service.WsnyAsCodeMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/as-codes")
@Api(tags = "[WSNY] W-SV-U-0016M01 AS 코드 관리 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnyAsCodeMgtController {

    private final WsnyAsCodeMgtService service;


    @ApiOperation(value = "AS 코드 관리 목록조회", notes = "조회조건에 일치하는 AS 코드 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "pdCd", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "svTpCd", value = "서비스 유형", paramType = "query", example = "3"),
        @ApiImplicitParam(name = "asLctCd", value = "A/S위치", paramType = "query", example = "T236"),
        @ApiImplicitParam(name = "apyChk", value = "적용일자선택", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "applyDate", value = "적용일자", paramType = "query", dataType = "date", example = "20220101")
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getAsCodePages(
        SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getAsCodePages(dto, pageInfo);
    }

    @ApiOperation(value = "AS 코드 관리 목록조회 Excel Download", notes = "조회조건에 일치하는 AS 코드 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "pdCd", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "svTpCd", value = "서비스 유형", paramType = "query", example = "3"),
        @ApiImplicitParam(name = "asLctCd", value = "A/S위치", paramType = "query", example = "T236"),
        @ApiImplicitParam(name = "apyChk", value = "적용일자선택", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "applyDate", value = "적용일자", paramType = "query", dataType = "date", example = "20220101")
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getAsCodeExcelDownload(
        SearchReq dto
    ) {
        return service.getAsCodes(dto);
    }

    @PostMapping("/excel-upload")
    public UploadRes uploadExcel(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {
        return service.uploadExcel(file);

    }

}
