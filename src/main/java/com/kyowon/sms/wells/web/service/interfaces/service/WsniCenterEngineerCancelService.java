package com.kyowon.sms.wells.web.service.interfaces.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniCenterEngineerCancelMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsniCenterEngineerCancelService {

    private final WsniCenterEngineerCancelMapper mapper;

    public int getEngineerCancelInquiry(String userId) {
        return mapper.selectEngineerCancelInquiry(userId);
    }
}
