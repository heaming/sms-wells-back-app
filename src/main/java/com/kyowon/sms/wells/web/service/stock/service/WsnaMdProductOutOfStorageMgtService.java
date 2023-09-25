package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageExcelUploadDto.ValidateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageExcelUploadDto.ValidateRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaMdProductOutOfStorageMgtMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaMdProductOutOfStorageMgtService {

    private final WsnaMdProductOutOfStorageMgtMapper mapper;

    private final WsnaMdProductOutOfStorageSaveService saveService;

    private final WsnaMdProductOutOfStorageExcelUploadService excelUploadService;

    public List getMdProductOutOfStorages(SearchReq dto) {
        return mapper.selectMdProductOutOfStorages(dto);
    }

    public int saveMdProductOutOfStorages(List<SaveReq> dtos) {
        return saveService.saveMdProductOutOfStorages(dtos);
    }

    public ValidateRes validateMdProductOutOfStoragExcelUpload(
        List<ValidateReq> dtos
    ) {
        return excelUploadService.validateMdProductOutOfStoragExcelUpload(dtos);
    }

    public int saveMdProductOutOfStoragExcelUpload(List<ValidateReq> dtos) {
        return excelUploadService.saveMdProductOutOfStoragExcelUpload(dtos);
    }
}
