package com.kyowon.sms.wells.web.service.interfaces.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterEngineerCancelDto.FindReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterEngineerCancelDto.FindRes;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniCenterEngineerCancelMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsniCenterEngineerCancelService {

    private final WsniCenterEngineerCancelMapper mapper;

    public FindRes getEngineerCancelInquiry(FindReq dto) {
        int cancelCount = mapper.selectEngineerCancelInquiry(dto);

        return new FindRes(Integer.toString(cancelCount));
    }
}
