package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaPcsvOutOfStorageMgtConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvOutOfStorageMgtDto.*;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaPcsvOutOfStorageMgtMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaPcsvOutOfStorageMgtService {

    private final WsnaPcsvOutOfStorageMgtMapper mapper;

    private final WsnaPcsvOutOfStorageMgtConverter converter;

    private final WsnaPcsvOutOfStorageSaveService service;

    public List<SearchRes> getPcsvOutOfStorages(SearchReq dto) {
        return converter.mapAllDvoToSearchRes(mapper.selectPcsvOutOfStorages(dto));
    }

    public List<FindLogisticsCentersRes> getPcsvLogisticsCenters() {
        return mapper.selectPcsvLogisticsCenters();
    }

    public List<FindProductsRes> getPcsvProducts() {
        return mapper.selectPcsvProducts();
    }

    public int savePcsvOutOfStorages(List<SaveReq> dtos) throws Exception {
        return service.savePcsvOutOfStorages(dtos);
    }
}
