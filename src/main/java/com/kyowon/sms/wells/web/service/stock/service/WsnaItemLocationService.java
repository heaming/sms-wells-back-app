package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaItemLocationConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemLocationDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaItemLocationMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaItemLocationService {
    private final WsnaItemLocationMapper mapper;
    private final WsnaItemLocationConverter converter;

    public PagingResult<SearchRes> getItemLocations(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectItemLocations(dto, pageInfo);
    }

    public List<SearchRes> getItemLocations(SearchReq dto) {
        return mapper.selectItemLocations(dto);
    }

    /**
     * 품목 위치정보 저장
     * @param list
     * @return
     */
    @Transactional
    public int saveItemLocations(List<CreateReq> list) {
        List<WsnaItemLocationDvo> voList = converter.mapAllWsnaItemLocationDvos(list);
        return mapper.saveItemLocations(voList);
    }

    public List<SearchStockCdRes> getItemLocationsStockCd() {
        return mapper.selectItemLocationsStockCd();
    }

    public PagingResult<SearchLocationRes> getStockItemLocations(SearchLocationReq dto, PageInfo pageInfo) {
        return mapper.selectStockItemLocations(dto, pageInfo);
    }

    public List<SearchLocationRes> getStockItemLocationsExcelDownload(SearchLocationReq dto) {
        return mapper.selectStockItemLocations(dto);
    }

    @Transactional
    public int saveStockItemLocations(List<CreateLocationReq> list) {
        List<WsnaItemLocationDvo> voList = converter.mapAllStockWsnaItemLocationDvos(list);
        return mapper.saveStockItemLocations(voList);
    }

    @Transactional
    public int saveStandardWarehouse(CreateWareLocationReq dto) {
        WsnaItemLocationDvo dvo = this.converter.mapToStckStdGbWsnaItemLocataionDvo(dto);
        return mapper.updateStandarWarehouse(dvo);
    }
}
