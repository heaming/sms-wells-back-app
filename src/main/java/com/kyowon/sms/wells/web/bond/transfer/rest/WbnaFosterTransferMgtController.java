package com.kyowon.sms.wells.web.bond.transfer.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchDetailRes;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchDetailSummaryRes;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchReq;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchRes;
import com.kyowon.sms.wells.web.bond.transfer.service.WbnaFosterTransferMgtService;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WBNA] 위탁 이관 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(BnBondConst.REST_URL_V1 + "/foster-transfers")
public class WbnaFosterTransferMgtController {

    private final WbnaFosterTransferMgtService service;

    @ApiOperation(value = "위탁 이관 관리 조회", notes = "위탁이관 집계결과 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "bzHdqDvCd", value = "사업부", paramType = "query", required = true),
        @ApiImplicitParam(name = "clcoCd", value = "추심사코드", paramType = "query"),
        @ApiImplicitParam(name = "bndNwDvCd", value = "신규구분", paramType = "query"),
        @ApiImplicitParam(name = "cstNm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화국번호암호화", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getFosterTransfers(
        @Valid
        SearchReq dto
    ) {
        return service.getFosterTransfers(dto);
    }

    @ApiOperation(value = "위탁 이관 관리 상세 조회", notes = "위탁이관 집계결과 상세 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "bzHdqDvCd", value = "사업부", paramType = "query", required = true),
        @ApiImplicitParam(name = "clcoCd", value = "추심사코드", paramType = "query"),
        @ApiImplicitParam(name = "bndNwDvCd", value = "신규구분", paramType = "query"),
        @ApiImplicitParam(name = "cstNm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화국번호암호화", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호", paramType = "query"),
    })
    @GetMapping("/detail/paging")
    public PagingResult<SearchDetailRes> getFosterTransferDetails(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getFosterTransferDetails(dto, pageInfo);
    }

    @ApiOperation(value = "채권 위탁이관 추심사별 상세 합계", notes = "검색 조건을 입력 받아 추심사별 집계 상세 합계 정보를 조회 한다.")
    @GetMapping("/detail/summary")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "bzHdqDvCd", value = "사업부", paramType = "query", required = true),
        @ApiImplicitParam(name = "bndNwDvCd", value = "신규구분", paramType = "query"),
        @ApiImplicitParam(name = "clcoCd", value = "추심사코드", paramType = "query"),
        @ApiImplicitParam(name = "cstNm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화국번호암호화", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호", paramType = "query"),
    })
    public SearchDetailSummaryRes getFosterTransferDetailsSummary(
        @Valid
        SearchReq dto
    ) {
        return service.getPartTransferDetailsSummary(dto);
    }

    @ApiOperation(value = "위탁이관 관리 발송 (위탁이관 자료 발송)", notes = "1. 위탁이관 집계결과 상세조회 결과를 채권위탁이관내역에 저장한다." +
        "2. 위탁이관 자료 발송을 위한 인터페이스 클래스의 Method를 호출한다." +
        "3. 호출결과를 Return 한다.")
    @PostMapping("/send")
    public String sendFosterDataTransfer(
        @RequestBody
        SearchReq dto
    ) throws Exception {
        return this.service.sendFosterDataTransfer(dto);
    }

    @ApiOperation(value = "위탁 이관 관리 상세 조회 엑셀 다운로드", notes = "엑셀다운로드 클릭(위탁이관 조회결과 상세 엑셀다운로드)")
    @GetMapping("/detail/excel-download")
    public List<SearchDetailRes> getFosterTransferDetailsExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getFosterTransferDetailsExcelDownload(dto);
    }
}
