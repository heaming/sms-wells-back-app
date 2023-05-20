package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncContactHistoryDto;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncContactHistoryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsncContactHistoryService {
    private final WsncContactHistoryMapper mapper;

    public List<WsncContactHistoryDto.SearchRes> getContactHistories(
        WsncContactHistoryDto.SearchReq dto
    ) {
        return mapper.selectContactHistory(dto);
    }
}
