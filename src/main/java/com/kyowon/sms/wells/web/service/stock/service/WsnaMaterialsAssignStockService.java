package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaMaterialsAssignStocksConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMaterialsAssignStocksDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMaterialsAssignStocksDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaMaterialsAssignStocksMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaMaterialsAssignStockService {

    private final WsnaMaterialsAssignStocksMapper mapper;
    private final WsnaMaterialsAssignStocksConverter converter;

    /**
     *
     * @param dto
     * @return
     */
    public PagingResult<SearchRes> getMaterialsAssignStocks(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectMaterialsAssignStocks(dto, pageInfo);
    }

    public List<SearchRes> getMaterialsAssignStocks(
        SearchReq dto
    ) {
        return mapper.selectMaterialsAssignStocks(dto);
    }

    @Transactional
    public int createMaterialsAssignStocks(
        List<CreateReq> list
    ) {
        List<WsnaMaterialsAssignStocksDvo> voList = converter.mapAllCreateReqToWsnaMaterialsAssignStocksDvo(list);
        return mapper.insertMaterialsAssignStocks(voList);
    }

    public List<PrtnrRes> selectPartners(
        PrtnrRes dto
    ) {
        return mapper.selectPartners(dto);
    }

    public List<OgRes> selectOrganizations(OgRes dto) {
        return mapper.selectOrganizations(dto);
    }
}
