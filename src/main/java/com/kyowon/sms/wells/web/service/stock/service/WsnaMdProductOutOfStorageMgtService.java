package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaMdProductOutOfStorageMgtConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageExcelUploadDto.ValidateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageExcelUploadDto.ValidateRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageMgtDto.RemoveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProdcutOutOfStorageSearchDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaMdProductOutOfStorageMgtMapper;
import com.sds.sflex.common.utils.DateUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaMdProductOutOfStorageMgtService {

    private final WsnaMdProductOutOfStorageMgtMapper mapper;

    private final WsnaMdProductOutOfStorageMgtConverter converter;

    private final WsnaMdProductOutOfStorageSaveService saveService;

    private final WsnaMdProductOutOfStorageExcelUploadService excelUploadService;

    public String getLoginPrtnrBzs() {
        return mapper.selectLoginPrtnrBzs();
    }

    public List<SearchRes> getMdProductOutOfStorages(SearchReq dto) {
        WsnaMdProdcutOutOfStorageSearchDvo dvo = converter.mapSearchReqToWsnaMdProdcutOutOfStorageSerachDvo(dto);

        // 작업구분: (작업대기), 종료일자 존재 , 첫배송 여부가 (전체 또는 N차)인 경우
        if ("2".equals(dvo.getFindGb()) && StringUtils.isNotEmpty(dvo.getEndDt())) {
            if ("ALL".equals(dvo.getFirstSppGb()) || "N".equals(dvo.getFirstSppGb())) {
                // 검색조건 (종료일자) 매월 마지막 영업일이후인경우 해당월 마지막 일자까지 조회
                String lasyDay = mapper.selectBusinessDays(dvo);
                if (lasyDay.compareTo(dvo.getEndDt()) <= 0) {
                    dvo.setEndDt(DateUtil.getLastDateOfMonth(dvo.getEndDt()));
                }
            }
        }

        return converter.mapAllDvoToSearchRes(mapper.selectMdProductOutOfStorages(dvo));
    }

    public int saveMdProductOutOfStorages(List<SaveReq> dtos) {
        return saveService.saveMdProductOutOfStorages(dtos);
    }

    public int saveMdProductOutOfStorageCancels(List<RemoveReq> dtos) {
        return saveService.saveMdProductOutOfStorageCancels(dtos);
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
