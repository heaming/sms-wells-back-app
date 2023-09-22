package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageExcelUploadDto.ValidateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageExcelUploadDto.ValidateRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaMdProductOutOfStorageMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] MD 상품 출고관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/md-product--out-of-storage")
public class WsnaMdProductOutOfStorageMgtController {

    private final WsnaMdProductOutOfStorageMgtService service;

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
    public int saveMdProductOutOfStoragExcelUpload() {
        return service.saveMdProductOutOfStoragExcelUpload();
    }
}
