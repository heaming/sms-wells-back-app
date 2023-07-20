package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.SearchLocationRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaItemLocationConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemLocationDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaItemLocationMapper;

import lombok.RequiredArgsConstructor;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.*;

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

    public int saveStockItemLocations(List<CreateLocationReq> list) {
        List<WsnaItemLocationDvo> voList = converter.mapAllStockWsnaItemLocationDvos(list);
        return mapper.saveStockItemLocations(voList);
    }

    public int saveStandardWarehouse(CreateWareLocationReq dto) {
        WsnaItemLocationDvo dvo = this.converter.mapToStckStdGbWsnaItemLocataionDvo(dto);
        return mapper.updateStandarWarehouse(dvo);
    }
}
