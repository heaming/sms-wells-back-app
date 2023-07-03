package com.kyowon.sms.wells.web.closing.performance.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccNormalAccountExceptDto.*;
import com.kyowon.sms.wells.web.closing.performance.service.WdccNormalAccountExceptService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCC] 정상계정 제외관리")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/normal-account")
public class WdccNormalAccountExceptController {
    private final WdccNormalAccountExceptService service;

    @ApiOperation(value = "상품별 조회", notes = "조회조건에 따른 상품별 내역 조회")

    @GetMapping("/by-product/lists")
    public List<SearchRes> getProductsList(
        SearchReq dto
    ) {
        return service.getProductsList(dto);
    }

    @ApiOperation(value = "계약상세번호 조회", notes = "조회조건에 따른 계약상세번호 내역 조회")

    @GetMapping("/by-contract/lists")
    public List<SearchCntrRes> getContractsList(
        SearchContractReq dto
    ) {
        return service.getContractsList(dto);
    }

    @ApiOperation(value = "정상계정 제외관리 등록", notes = "정상계정 제외관리 등록")
    @PostMapping
    public SaveResponse createNormalAccountExcept(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(service.createNormalAccountExcept(dtos)).build();
    }

    @ApiOperation(value = "정상계정 제외관리 수정", notes = "정상계정 제외관리 수정")
    @PutMapping
    public SaveResponse editNormalAccountExcept(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(service.createNormalAccountExcept(dtos)).build();
    }

    @ApiOperation(value = "정상계정 제외관리 삭제", notes = "정상계정 제외관리 삭제")
    @DeleteMapping
    public SaveResponse removeNormalAccountExcept(
        @Valid
        @RequestParam
        @NotEmpty
        List<String> nomAccExcdIds
    ) {
        return SaveResponse.builder().processCount(service.removeNormalAccountExcept(nomAccExcdIds)).build();
    }

    @ApiOperation(value = "정상계정 제외관리 엑셀업로드", notes = "제외관리할 계정을 엑셀업로드한다.")
    @PostMapping("/excel-upload")
    public UploadRes saveNormalAccountExceptForExcelUpload(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {
        return service.saveNormalAccountExceptForExcelUpload(file);
    }
}
