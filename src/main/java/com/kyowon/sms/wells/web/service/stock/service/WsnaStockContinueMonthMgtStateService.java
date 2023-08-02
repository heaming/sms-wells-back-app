package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockContinueMonthMgtStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockContinueMonthMgtStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaStockContinueMonthMgtStateMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaStockContinueMonthMgtStateService {
    private final WsnaStockContinueMonthMgtStateMapper mapper;

    public List<SearchRes> getStockContinueMonthMgtState(SearchReq dto) {
        return mapper.selectStockContinueMonthMgtState(dto);
    }

    public List<SearchRes> getStockContinueMonthMgtStateExcelDownload(SearchReq dto) {
        return mapper.selectStockContinueMonthMgtState(dto);
    }

}
