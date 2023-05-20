package com.kyowon.sms.wells.web.bond.transfer.rest;

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
    @GetMapping
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "bzHdqDvCd", value = "사업본부구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cstNm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "phoneNumber", value = "핸드폰번호", paramType = "query")
    })
    public List<SearchRes> getPartTransferAggregation(
        @Valid
        SearchReq reqDto,
        PageInfo pageInfo
    ) {
        log.debug("call getAggregateParts");
        log.debug("reqDto: " + reqDto.toString());
        return service.getPartTransferAggregation(reqDto);
    }

    @ApiOperation(value = "채권 파트이관 파트별 상세", notes = "검색 조건을 입력 받아 파트별 집계 상세 정보를 조회 한다.")
    @GetMapping("/details/paging")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "query", required = true)
    })
    public PagingResult<SearchDetailRes> getPartAggregationDetails(
        @Valid
        SearchDetailReq reqDto,
        PageInfo pageInfo
    ) {
        log.debug("call getPartAggregationDetails");
        log.debug("reqDto: " + reqDto.toString());
        return service.getPartTransferDetails(reqDto, pageInfo);
    }

    @ApiOperation(value = "채권 파트이관 파트별 상세 합계", notes = "검색 조건을 입력 받아 파트별 집계 상세 합계 정보를 조회 한다.")
    @GetMapping("/details/summary")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "query", required = true)
    })
    public SearchDetailSummaryRes getPartAggregationDetailSummary(
        @Valid
        SearchDetailReq reqDto
    ) {
        log.debug("call getPartAggregationDetailSummary");
        log.debug("reqDto: " + reqDto.toString());

        return service.getPartTransferDetailSummary(reqDto);
    }

    @ApiOperation(value = "채권 파트이관 파트별 상세 엑셀다운로드 정보 조회", notes = "검색 조건을 입력 받아 파트별 집계 상세 정보를 조회")
    @GetMapping("/details/excel-download")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "bzHdqDvCd", value = "사업부구분코드", paramType = "query")
    })
    public List<SearchDetailRes> getPartAggregationDetailsForExcelDownload(
        @Valid
        SearchDetailReq reqDto
    ) {
        return service.getExcelDownload(reqDto);
    }

    @ApiOperation(value = "파트 이관 정보 생성", notes = "집금(추심)대상 채권을 집금구분(단기, 중기, 소송, 집행)별로 사업부 파트로 채권을 이관한다. 이관 후 파트별 집계 상세에서 집금구분 수정건에 대하여 저장한다.")
    @PostMapping
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "body", required = true),
        @ApiImplicitParam(name = "bzHdqDvCd", value = "기준년월", paramType = "body", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "body")
    })
    public SaveResponse createPartTransfer(
        @Valid
        @RequestBody
        CreateReq dto
    ) {
        // TODO 멩세서 내용 확인 후 추가 작업 예정 그 전에는 임시 갱신 건수 전달
        log.debug("call createPartTransfer");
        return SaveResponse.builder()
            .processCount(service.createPartTransfer(dto))
            .build();
    }

    @ApiOperation(value = "채권 집금구분 정보 갱신", notes = "계약,배정내역의 집금 구분 정보를 갱신")
    @PutMapping
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
