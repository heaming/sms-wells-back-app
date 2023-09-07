package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaPcsvReturningGoodsMgtMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaPcsvReturningGoodsMgtService {
    private final WsnaPcsvReturningGoodsMgtMapper mapper;
    private final WsnaPcsvReturningGoodsSaveService service;

    public List<SearchRes> getPcsvReturningGoods(
        SearchReq req
    ) {
        return mapper.selectPcsvReturningGoods(req);
    }

    public List<WsnaPcsvReturningGoodsMgtDto.FindLogisticsCentersRes> getPcsvLogisticsCenters() {
        return mapper.selectPcsvLogisticsCenters();
    }

    public List<WsnaPcsvReturningGoodsMgtDto.FindProductsRes> getPcsvProducts(
        WsnaPcsvReturningGoodsMgtDto.FindProductsReq dto
    ) {
        return mapper.selectPcsvProducts(dto);
    }

    @Transactional
    public int savePcsvReturningGoods(List<SaveReq> dtos) {
        return service.savePcsvReturningGoods(dtos);
    }

}
