package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbSamsungManufactureNoInqrDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSamsungManufactureNoInqrDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbSamsungManufactureNoInqrService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Validated
@Api(tags = "[WSNB] 삼성 제조번호 조회 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/samsung-manufacture-no-inqr")
public class WsnbSamsungManufactureNoInqrController {

    private final WsnbSamsungManufactureNoInqrService service;

    @ApiOperation(value = "삼성 제조번호 조회", notes = "조회조건에 일치하는 담당자별 정기방문 처리 집계표 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "등록시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "등록종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "ssPdctBcNo", value = "삼성제조번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "saleCd", value = "판매코드", paramType = "query"),
        @ApiImplicitParam(name = "rcgvpKnm", value = "고객명", paramType = "query"),

    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getSamsungManufactureNoInqrPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getSamsungManufactureNoInqrPages(dto, pageInfo);
    }

    @ApiOperation(value = "삼성 제조번호 조회(엑셀 다운로드)", notes = "조회조건에 일치하는 삼성 제조번호 조회 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "등록시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "등록종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "ssPdctBcNo", value = "삼성제조번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "saleCd", value = "판매코드", paramType = "query"),
        @ApiImplicitParam(name = "rcgvpKnm", value = "고객명", paramType = "query"),

    })
    @GetMapping("/excel-download")
    public List<SearchRes> getSamsungManufactureNoInqrForExcelDownload(
        SearchReq dto
    ) {
        return this.service.getSamsungManufactureNoInqrs(dto);
    }
}
