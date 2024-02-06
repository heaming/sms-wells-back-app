package com.kyowon.sms.wells.web.bond.transfer.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.*;
import com.kyowon.sms.wells.web.bond.transfer.service.WbnaFosterTransferMgtService;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

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

    @ApiOperation(value = "WELLS 위탁 이관 관리 조회", notes = "위탁이관 집계결과 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "bzHdqDvCd", value = "사업부", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "query", required = true),
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

    @ApiOperation(value = "WELLS 위탁 이관 관리 상세 페이징 조회", notes = "위탁이관 집계결과 상세 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "bzHdqDvCd", value = "사업부", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "bndNwDvCd", value = "신규구분", paramType = "query"),
        @ApiImplicitParam(name = "cstNm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화국번호암호화", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호", paramType = "query"),
        @ApiImplicitParam(name = "clctamPrtnrNo", value = "집금담당자번호", paramType = "query", required = true),
    })
    @GetMapping("/detail/paging")
    public PagingResult<SearchDetailRes> getFosterTransferDetails(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getFosterTransferDetails(dto, pageInfo);
    }

    @ApiOperation(value = "WELLS 채권 위탁이관 추심사별 상세 합계", notes = "검색 조건을 입력 받아 추심사별 집계 상세 합계 정보를 조회 한다.")
    @GetMapping("/detail/summary")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "bzHdqDvCd", value = "사업부", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "bndNwDvCd", value = "신규구분", paramType = "query"),
        @ApiImplicitParam(name = "cstNm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화국번호암호화", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호", paramType = "query"),
        @ApiImplicitParam(name = "clctamPrtnrNo", value = "집금담당자번호", paramType = "query", required = true),
    })
    public SearchDetailSummaryRes getFosterTransferDetailsSummary(
        @Valid
        SearchReq dto
    ) {
        return service.getPartTransferDetailsSummary(dto);
    }

    @ApiOperation(value = "WELLS 위탁이관 관리 확정 (위탁이관 자료 확정)", notes = "1. 위탁이관 집계결과 상세조회 결과를 채권위탁이관내역에 저장한다." +
        "2. 위탁이관 자료 발송을 위한 인터페이스 클래스의 Method를 호출한다." +
        "3. 호출결과를 Return 한다.")
    @PostMapping("/confirm")
    public SaveResponse confirmFosterDataTransfers(
        @RequestBody
        @Valid
        SearchReq dto
    ) throws Exception {
        return this.service.confirmFosterDataTransfers(dto);
    }

    @ApiOperation(value = "WELLS 위탁이관 관리 저장", notes = "1. 수정된 Row의 채권계약이력 Table에 이력을 INSERT 한다." +
        "2. 수정된 Row를 채권계약기본 Table에 UPDATE한다.")
    @PutMapping
    public SaveResponse editFosterDataTransfers(
        @RequestBody
        @Valid
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.editFosterDataTransfers(dtos)).build();
    }

    @ApiOperation(value = "WELLS 위탁 이관 관리 상세 조회 엑셀 다운로드", notes = "엑셀다운로드 클릭(위탁이관 조회결과 상세 엑셀다운로드)")
    @GetMapping("/detail/excel-download")
    public List<SearchDetailRes> getFosterTransferDetailsExcelDownload(
        @Valid
        SearchReq dto,
        @RequestParam
        String downFileName,
        @RequestParam
        String pageId
    ) {
        return service.getFosterTransferDetailsExcelDownload(dto, downFileName, pageId);
    }

    @ApiOperation(value = "WELLS 위탁 이관 관리 상세 조회 엑셀 업로드", notes = "엑셀 업로드 클릭(위탁이관 조회결과 상세 엑셀 업로드)")
    @PostMapping("/excel-upload")
    public UploadRes editFosterTransferDetailsExcelUpload(
        @RequestParam("file")
        MultipartFile file,
        @RequestParam
        String baseYm,
        @RequestParam
        String bzHdqDvCd,
        @RequestParam
        String clctamDvCd,
        @RequestParam
        String pageId
    ) throws Exception {
        return service.editFosterTransferDetailsExcelUpload(file, baseYm, bzHdqDvCd, clctamDvCd, pageId);
    }

}
