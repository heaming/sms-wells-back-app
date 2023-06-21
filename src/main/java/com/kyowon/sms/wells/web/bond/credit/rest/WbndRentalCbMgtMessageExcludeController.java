package com.kyowon.sms.wells.web.bond.credit.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtMessageExcludeDto;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtMessageExcludeDto.SaveReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtMessageExcludeDto.SearchRes;
import com.kyowon.sms.wells.web.bond.credit.service.WbndRentalCbMgtMessageExcludeService;
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

@Api(tags = "[WBND] 렌탈CB 알림톡 발송 제외")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(BnBondConst.REST_URL_V1 + "/rental-cb-mgt/message-excludes")
public class WbndRentalCbMgtMessageExcludeController {

    private final WbndRentalCbMgtMessageExcludeService service;

    @ApiOperation(value = "렌탈CB 알림톡 발송 제외 자료 페이징 조회", notes = "렌탈CB의 연체에 대한 알림톡 발송 제외 대상을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query", example = "123456"),
        @ApiImplicitParam(name = "ctntExcdBndBizCd", value = "연락제외채권업무코드", paramType = "query", required = true, example = "02"),
        @ApiImplicitParam(name = "ctntExcdOjTpCd", value = "연락제외대상유형코드", paramType = "query", required = true, example = "01"),
        @ApiImplicitParam(name = "ctntExcdMediTpCd", value = "연락제외매체유형코드", paramType = "query", required = true, example = "03"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getRentalCbMessageExcludePages(
        @Valid
        WbndRentalCbMgtMessageExcludeDto.SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getRentalCbMessageExcludePages(dto, pageInfo);
    }

    @ApiOperation(value = "렌탈CB 알림톡 발송 제외 자료 엑셀 다운로드", notes = "렌탈CB의 연체에 대한 알림톡 발송 제외 대상 엑셀 다운로드")
    @GetMapping("/excel-download")
    public List<SearchRes> getRentalCbMessageExcludesForExcelDownload(
        @Valid
        WbndRentalCbMgtMessageExcludeDto.SearchReq dto
    ) {
        return service.getRentalCbMessageExcludesForExcelDownload(dto);
    }

    @ApiOperation(value = "렌탈CB 알림톡 발송 제외 자료 저장", notes = "CUD 변경 데이터를 List 형태로 받아 일괄 저장한다.")
    @PutMapping
    public SaveResponse saveRentalCbMessageExcludes(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveRentalCbMessageExcludes(dtos)).build();
    }

    @ApiOperation(value = "렌탈CB 알림톡 발송 제외 자료 삭제", notes = "선택한 알림톡 발송 제외 내역을 일괄 삭제한다.")
    @DeleteMapping
    public SaveResponse removeRentalCbMessageExcludes(
        @Valid
        @RequestBody
        @NotEmpty
        List<String> bndCntcExcdOjIds
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.removeRentalCbMessageExcludes(bndCntcExcdOjIds))
            .build();
    }

    @ApiOperation(value = "렌탈CB 알림톡 발송 제외 엑셀 업로드", notes = "엑셀 업로드")
    @PostMapping("excel-upload")
    public UploadRes saveRentalCbMessageExcludesExcelUpload(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {
        return service.saveRentalCbMessageExcludesExcelUpload(file);
    }
}
