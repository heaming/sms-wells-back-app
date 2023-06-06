package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsMngtSchdInqrDto;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncBsMngtSchdInqrMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsncBsMngtSchdInqrService {
    private final WsncBsMngtSchdInqrMapper mapper;

    public List<WsncBsMngtSchdInqrDto.SearchRes> getBsMngtSchdInqrAgrg(
        WsncBsMngtSchdInqrDto.SearchReq dto
    ) {
        return mapper.selectBsMngtSchdInqrAgrg(dto);
    }

    public List<WsncBsMngtSchdInqrDto.SearchRes> getBsMngtSchdInqrDtl(
        WsncBsMngtSchdInqrDto.SearchReq dto
    ) {
        return mapper.selectBsMngtSchdInqrDtl(dto);
    }
}
