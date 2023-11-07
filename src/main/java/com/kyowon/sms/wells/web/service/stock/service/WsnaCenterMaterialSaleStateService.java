package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaCenterMaterialSaleStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaCenterMaterialSaleStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaCenterMaterialSaleStateMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaCenterMaterialSaleStateService {
    private final WsnaCenterMaterialSaleStateMapper mapper;

    public List<SearchRes> getCenterMaterialSaleStates(SearchReq dto) {
        return mapper.selectCenterMaterialSaleState(dto);
    }

}
