package com.kyowon.sms.wells.web.bond.transfer.rest;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.kyowon.sms.wells.web.bond.transfer.service.WbnaBondPartTransferService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaBondPartTransferDto.*;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[WBNA] 채권 파트 이관")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(BnBondConst.REST_URL_V1 + "/part-transfers")
public class WbnaBondPartTransferController {

    private final WbnaBondPartTransferService service;

    @ApiOperation(value = "채권 파트이관 파트별 집계", notes = "검색 조건을 입력 받아 채권 파트별 집계 정보를 조회 한다.")
    @GetMapping()
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "bzHdqDvCd", value = "사업본부구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cstKnm", value = "고객명-(임시)", paramType = "query"),
        @ApiImplicitParam(name = "phoneNumber", value = "핸드폰번호-(임시)", paramType = "query")
    }) // TODO List<HashMap<String, String>> 임시로 데이터 작업 List<SearchRes>로 변경 필요
    public List<HashMap<String, String>> getPartTransferAggregation(
        @Valid
        SearchReq reqDto,
        PageInfo pageInfo
    ) {
        log.debug("call getAggregateParts");
        log.debug("reqDto: " + reqDto.toString());
        List<SearchRes> partTransferAggregations = service.getPartTransferAggregation(reqDto);
        log.debug("partTransferAggregations: " + partTransferAggregations);
        // TODO: 데이터 없는 상태로 작업 하기 때문에 임시 데이터 작업 진행
        return service.getDumyData();
    }

    @ApiOperation(value = "채권 파트이관 파트별 상세", notes = "검색 조건을 입력 받아 파트별 집계 상세 정보를 조회 한다.")
    @GetMapping("/details/paging")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "query", required = true)
    }) // TODO HashMap<String, String> 임시로 데이터 작업 PagingResult<SearchDetailRes>로 변경 필요
    public PagingResult<HashMap<String, String>> getPartAggregationDetails(
        @Valid
        SearchDetailReq reqDto,
        PageInfo pageInfo
    ) {
        log.debug("call getPartAggregationDetails");
        log.debug("reqDto: " + reqDto.toString());

        PagingResult<SearchDetailRes> searchDetailRes = service.getPartTransferDetails(reqDto, pageInfo);
        log.debug("searchDetailRes: " + searchDetailRes);
        // TODO: 데이터 없는 상태로 작업 하기 때문에 임시 데이터 작업 진행
        return service.getDumyData2();
    }

    @ApiOperation(value = "채권 파트이관 파트별 상세 엑셀다운로드 정보 조회", notes = "검색 조건을 입력 받아 파트별 집계 상세 정보를 조회")
    @GetMapping("/details/excel-download")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "query", required = true)
    }) // TODO HashMap<String, String> 임시로 데이터 작업 List<SearchDetailRes>로 변경 필요
    public List<HashMap<String, String>> getPartAggregationDetailsForExcelDownload(
        @Valid
        SearchDetailReq reqDto
    ) {
        List<SearchDetailRes> searchDetailRes = service.getExcelDownload(reqDto);
        log.debug("searchDetailRes: " + searchDetailRes); // 쿼리 동작 확인을 위한 임시 소스
        // TODO: 데이터 없는 상태로 작업 하기 때문에 임시 데이터 작업 진행
        return service.getDumyData2();
    }

    @ApiOperation(value = "파트 이관 정보 생성", notes = "집금(추심)대상 채권을 집금구분(단기, 중기, 소송, 집행)별로 사업부 파트로 채권을 이관한다. 이관 후 파트별 집계 상세에서 집금구분 수정건에 대하여 저장한다.")
    @PostMapping()
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "body", required = true),
        @ApiImplicitParam(name = "bzHdqDvCd", value = "기준년월", paramType = "body", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "body", required = true)
    })
    public SaveResponse createPartTransfers(
        @Valid
        @RequestBody
        CreateReq dto
    ) {
        // TODO 멩세서 내용 확인 후 추가 작업 예정 그 전에는 임시 갱신 건수 전달
        log.debug("call createPartTransfers");
        return SaveResponse.builder()
            .processCount(service.createPartTransfers(dto))
            .build();
    }

    @ApiOperation(value = "채권 집금구분 정보 갱신", notes = "계약,배정내역의 집금 구분 정보를 갱신")
    @PutMapping
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "List<EditReq>", value = "갱신 목록", paramType = "body", required = true)
    })
    public SaveResponse editPartTransferDetails(
        @Valid
        @RequestBody
        @NotEmpty
        List<EditReq> dtos
    ) {
        // TODO 멩세서 내용 확인 후 추가 작업 예정 그 전에는 임시 갱신 건수 전달
        log.debug("call editPartTransferDetails");
        return SaveResponse.builder()
            .processCount(service.editPartTransferDetails(dtos))
            .build();
    }

    @ApiOperation(value = "채권 배정 이력 확인", notes = "기준년월 정보를 입력 받아 채권 배정 내역 등록 여부를 조회 한다")
    @GetMapping("/has-part-transfer")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true)
    })
    public Boolean hasPartTransfer(
        @RequestParam
        String baseYm
    ) {
        log.debug("call hasPartTransfer");
        log.debug("baseYm: " + baseYm);
        return service.hasPartTransfer(baseYm);
    }

    @ApiOperation(value = "채권 이관 배정 수행내역 확인", notes = "기준년월 정보를 입력 받아 채권 배정 내역 등록 여부를 조회 한다")
    @GetMapping("/has-part-transfer-detail")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "bzHdqDvCd", value = "사업부구분", paramType = "query", required = true)
    })
    public Boolean hasPartTransferDetail(
        @Valid
        SearchDetailReq reqDto
    ) {
        log.debug("call hasPartTransferDetail");
        return service.hasPartTransferDetail(reqDto);
    }
}
