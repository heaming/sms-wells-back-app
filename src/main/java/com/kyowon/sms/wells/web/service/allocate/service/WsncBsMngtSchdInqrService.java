package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsncBsMngtSchdInqrService {
    private final WsncBsMngtSchdInqrMapper mapper;

    public List<WsncBsMngtSchdInqrDto.SearchRes> getBsMngtSchdInqrAgrg(
        WsncBsMngtSchdInqrDto.SearchReq dto
    ) {
        return mapper.selectBsMngtSchdInqr(dto);
    }
}
