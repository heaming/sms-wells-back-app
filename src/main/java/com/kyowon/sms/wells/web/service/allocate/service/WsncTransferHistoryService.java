package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTransferHistoryDto;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncTransferHistoryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsncTransferHistoryService {
    private final WsncTransferHistoryMapper mapper;

    public List<WsncTransferHistoryDto.SearchRes> getTransferHistories(
        WsncTransferHistoryDto.SearchReq dto
    ) {
        return mapper.selectTransferHistory(dto);
    }
}
