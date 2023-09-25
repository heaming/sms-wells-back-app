package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageExcelUploadDto.ValidateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageExcelUploadDto.ValidateRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.service.WsnaMdProductOutOfStorageMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] MD 상품 출고관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/md-product-out-of-storage")
public class WsnaMdProductOutOfStorageMgtController {

    private final WsnaMdProductOutOfStorageMgtService service;

    @ApiOperation(value = "MD 상품 출고관리", notes = "조회조건에 일치하는 MD 상품출고관리 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "계약시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "계약종료일자", paramType = "query", required = true),
    })
    @GetMapping
    public List getMdProductOutOfStorages(
        @Valid
        SearchReq dto
    ) {
        return service.getMdProductOutOfStorages(dto);
    }

    @ApiOperation(value = "MD상품 출고관리 출고관리 저장", notes = "MD상품 출고관리 정보를 저장한다.")
    @PostMapping
    public SaveResponse saveMdProductOutOfStorages(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveMdProductOutOfStorages(dtos))
            .build();
    }

    @ApiOperation(value = "MD 상품 출고관리 - 엑셀업로드 유효성체크", notes = "MD 상품 출고관리 엑셀업로드 유효성체크 를 한다.")
    @PutMapping("/excel-upload-validate")
    public ValidateRes validateMdProductOutOfStoragExcelUpload(
        @RequestBody
        List<ValidateReq> dtos
    ) throws Exception {
        return service.validateMdProductOutOfStoragExcelUpload(dtos);
    }

    @ApiOperation(value = "MD 상품 출고관리 - 엑셀업로드 저장", notes = "MD 상품 출고관리 엑셀업로드 유효성체크 를 한다.")
    @PostMapping("/excel-upload")
    public int saveMdProductOutOfStoragExcelUpload(
        @Valid
        @RequestBody
        @NotEmpty
        List<ValidateReq> dtos
    ) {
        return service.saveMdProductOutOfStoragExcelUpload(dtos);
    }
}
