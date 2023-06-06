package com.kyowon.sms.wells.web.bond.transfer.rest;

import java.util.List;

import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaCollectorAssignDto.*;
import com.kyowon.sms.wells.web.bond.transfer.service.WbnaCollectorAssignService;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

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
        @ApiImplicitParam(name = "bndNwDvCd", value = "신규구분코드", paramType = "query"),
        @ApiImplicitParam(name = "clctamPrtnrNo", value = "집금담당자", paramType = "query")
    })
    public List<SearchRes> getCollectorAssigns(
        @Valid
        SearchReq reqDto
    ) {
        log.debug("call getCollectorAssigns");
        log.debug("reqDto: " + reqDto.toString());
        return service.getCollectorAssigns(reqDto);
    }

    @ApiOperation(value = "집금자배정 집계 상세", notes = "집금자배정 집계 별 상세목록을 조회(계약-집금자)")
    @GetMapping("/details/paging")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bzHdqDvCd", value = "사업본부구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "bndNwDvCd", value = "신규구분코드", paramType = "query"),
        @ApiImplicitParam(name = "clctamPrtnrNo", value = "집금담당자", paramType = "query")
    })
    public PagingResult<SearchDetailRes> getCollectorAssignDetails(
        @Valid
        SearchReq reqDto,
        PageInfo pageInfo
    ) {
        log.debug("call getCollectorAssignDetails");
        log.debug("reqDto: " + reqDto.toString());
        return service.getCollectorAssignDetails(reqDto, pageInfo);
    }

    @ApiOperation(value = "집금자배정 집계 상세 합계", notes = "집금자배정 집계 별 상세목록을 조회(계약-집금자)")
    @GetMapping("/details/summary")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "bzHdqDvCd", value = "사업본부구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "bndNwDvCd", value = "신규구분코드", paramType = "query"),
        @ApiImplicitParam(name = "clctamPrtnrNo", value = "집금담당자", paramType = "query")
    })
    public SearchSummaryRes getCollectorAssignDetailsSummary(
        @Valid
        SearchReq reqDto
    ) {
        log.debug("call getCollectorAssignDetailsSummary");
        log.debug("reqDto: " + reqDto.toString());
        return service.getCollectorAssignDetailsSummary(reqDto);
    }

    @ApiOperation(value = "집금자배정 상세 엑셀다운로드 조회", notes = "집금자배정 집계 별 상세목록을 조회")
    @GetMapping("/details/excel-download")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bzHdqDvCd", value = "사업본부구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "bndNwDvCd", value = "신규구분코드", paramType = "query"),
        @ApiImplicitParam(name = "clctamPrtnrNo", value = "집금담당자", paramType = "query")
    })
    public List<SearchDetailRes> getCollectorAssignsDetailsForExcelDownload(
        @Valid
        SearchReq reqDto
    ) {
        return service.getExcelDownload(reqDto);
    }

    @ApiOperation(value = "집금자 배정 정보 생성", notes = "집금자 배정 정보를 생성")
    @PostMapping
    public String createCollectorAssigns(
        @Valid
        @RequestBody
        CreateReq dto
    ) throws Exception {
        // TODO 배치 호출 관련 서비스 계속 테스트 하면서 수정 필요
        log.debug("call createCollectorAssigns");
        return service.createCollectorAssigns(dto);
    }

    @ApiOperation(value = "집금자 배정 정보 수정", notes = "집금자 배정 정보를 수정")
    @PutMapping
    public SaveResponse editCollectorAssignsDetails(
        @Valid
        @RequestBody
        @NotEmpty
        List<EditReq> dtos
    ) {
        // TODO 멩세서 내용 확인 후 추가 작업 예정 그 전에는 임시 갱신 건수 전달
        log.debug("call editCollectorAssignsDetails");
        return SaveResponse.builder()
            .processCount(service.editCollectorAssignsDetails(dtos))
            .build();
    }

    @ApiOperation(value = "집금자 배정 확정", notes = "집금자 배정 정보를 확정 상태로 변경 한다")
    @PutMapping("/confirm")
    public SaveResponse editCollectorAssingsConfirm(
        @Valid
        @RequestBody
        SearchReq reqDto
    ) {
        // TODO 멩세서 내용 확인 후 추가 작업 예정 그 전에는 임시 갱신 건수 전달
        log.debug("call editCollectorAssingsConfirm");
        return SaveResponse.builder()
            .processCount(service.editCollectorAssingsConfirm(reqDto))
            .build();
    }
}
