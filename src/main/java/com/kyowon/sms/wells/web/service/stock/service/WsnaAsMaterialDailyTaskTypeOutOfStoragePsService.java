package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialDailyTaskTypeOutOfStoragePsDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialDailyTaskTypeOutOfStoragePsDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaAsMaterialDailyTaskTypeOutOfStoragePsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaAsMaterialDailyTaskTypeOutOfStoragePsService {

    private final WsnaAsMaterialDailyTaskTypeOutOfStoragePsMapper mapper;

    public List<SearchRes> getAsMaterialDailyTaskTypeOutOfStoragePss(SearchReq dto) {
        return mapper.selectAsMaterialDailyTaskTypeOutOfStoragePss(dto);
    }
}
