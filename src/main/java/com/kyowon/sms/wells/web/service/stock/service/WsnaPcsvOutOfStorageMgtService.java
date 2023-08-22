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

    public String getPcsvOutOfStorageStockQty(SearchReq dto) {
        return mapper.selectPcsvOutOfStorageStockQty(dto);
    }

    public List<FindLogisticsCentersRes> getPcsvLogisticsCenters() {
        return mapper.selectPcsvLogisticsCenters();
    }

    public List<FindProductsRes> getPcsvProducts(FindProductsReq dto) {
        return mapper.selectPcsvProducts(dto);
    }

    public List<FindIvcPrntSnRes> getPcsvIvcPrntSns(SearchReq dto) {
        return mapper.selectPcsvIvcPrntSns(dto);
    }

    public String getPcsvIvcPrntSn(SearchReq dto) {
        return mapper.selectPcsvIvcPrntSn(dto);
    }

    public int savePcsvOutOfStorage(List<SaveReq> dtos) {
        return service.savePcsvOutOfStorageTest(dtos);
    }

}
