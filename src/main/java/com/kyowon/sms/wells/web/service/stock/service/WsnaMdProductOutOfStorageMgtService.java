package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageExcelUploadDto.ValidateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageExcelUploadDto.ValidateRes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaMdProductOutOfStorageMgtService {

    private final WsnaMdProductOutOfStorageExcelUploadService excelUploadService;

    public ValidateRes validateMdProductOutOfStoragExcelUpload(
        List<ValidateReq> dtos
    ) {
        return excelUploadService.validateMdProductOutOfStoragExcelUpload(dtos);
    }

    public int saveMdProductOutOfStoragExcelUpload() {
        return 0;
    }
}
