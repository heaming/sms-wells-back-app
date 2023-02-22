package com.kyowon.sms.wells.web.bond.transfer.rest;

import java.util.HashMap;
import java.util.List;

import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaCollectorAssignDto.SearchRes;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaCollectorAssignDto.SearchReq;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaCollectorAssignDto.SearchDetailRes;
import com.kyowon.sms.wells.web.bond.transfer.service.WbnaCollectorAssignService;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Slf4j
@Api(tags = "[WBNA] 집금자배정")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(BnBondConst.REST_URL_V1 + "/collector-assigns")
public class WbnaCollectorAssignController {

    private final WbnaCollectorAssignService service;

    @ApiOperation(value = "집금자배정 집계", notes = "집금자배정 집계 결과 목록을 조회")
    @GetMapping
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bzHdqDvCd", value = "사업본부구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "nwYn", value = "신규여부", paramType = "query"),
        @ApiImplicitParam(name = "clctamPrtnrNo", value = "집금담당자", paramType = "query")
    }) // TODO List<HashMap<String, String>> 임시로 데이터 작업 List<SearchRes>로 변경 필요
    public List<HashMap<String, String>> getCollectorAssigns(
        @Valid
        SearchReq reqDto
    ) {
        log.debug("call getCollectorAssigns");
        log.debug("reqDto: " + reqDto.toString());
        List<SearchRes> collectorAssigns = service.getCollectorAssigns(reqDto);
        log.debug("collectorAssigns: " + collectorAssigns);
        // TODO: 데이터 없는 상태로 작업 하기 때문에 임시 데이터 작업 진행
        return service.getDumyData();
    }

    @ApiOperation(value = "집금자배정 집계 상세", notes = "집금자배정 집계 별 상세목록을 조회(계약-집금자)")
    @GetMapping("/details/paging")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bzHdqDvCd", value = "사업본부구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "nwYn", value = "신규여부", paramType = "query"),
        @ApiImplicitParam(name = "clctamPrtnrNo", value = "집금담당자", paramType = "query")
    }) // TODO HashMap<String, String> 임시로 데이터 작업 PagingResult<SearchDetailRes>로 변경 필요
    public PagingResult<HashMap<String, String>> getCollectorAssignDetails(
        @Valid
        SearchReq reqDto,
        PageInfo pageInfo
    ) {
        log.debug("call getCollectorAssignDetails");
        log.debug("reqDto: " + reqDto.toString());

        PagingResult<SearchDetailRes> searchDetailRes = service.getCollectorAssignDetails(reqDto, pageInfo);
        log.debug("searchDetailRes: " + searchDetailRes);
        // TODO: 데이터 없는 상태로 작업 하기 때문에 임시 데이터 작업 진행
        return service.getDumyData2();
    }

    @ApiOperation(value = "집금자배정 상세 엑셀다운로드 조회", notes = "집금자배정 집계 별 상세목록을 조회")
    @GetMapping("/details/excel-download")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bzHdqDvCd", value = "사업본부구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "nwYn", value = "신규여부", paramType = "query"),
        @ApiImplicitParam(name = "clctamPrtnrNo", value = "집금담당자", paramType = "query")
    }) // TODO HashMap<String, String> 임시로 데이터 작업 List<SearchDetailRes>로 변경 필요
    public List<HashMap<String, String>> getCollectorAssignsDetailsForExcelDownload(
        @Valid
        SearchReq reqDto
    ) {
        List<SearchDetailRes> searchDetailRes = service.getExcelDownload(reqDto);
        log.debug("searchDetailRes: " + searchDetailRes); // 쿼리 동작 확인을 위한 임시 소스
        // TODO: 데이터 없는 상태로 작업 하기 때문에 임시 데이터 작업 진행
        return service.getDumyData2();
    }
}
