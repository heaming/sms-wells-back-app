package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockContinueMonthStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockContinueMonthStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaStockContinueMonthStateMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaStockContinueMonthStateService {
    private final WsnaStockContinueMonthStateMapper mapper;

    public List<SearchRes> getStockContinueMonthState(SearchReq dto) {
        return mapper.selectStockContinueMonthState(dto);
    }

    public List<SearchRes> getBusinessTypeWorkHourExcelDownload(SearchReq dto) {
        return mapper.selectStockContinueMonthState(dto);
    }

}
