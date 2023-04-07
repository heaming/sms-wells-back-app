package com.kyowon.sms.wells.web.service.interfaces.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniCenterEngineerCancelFinishMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsniCenterEngineerCancelFinishService {
    private final WsniCenterEngineerCancelFinishMapper mapper;

    public String editCenterEngineerCancelFinish(String asIstOjNo) {
        int result = 0;
        String resultValue;

        result = mapper.updateCenterEngineerCancelFinish(asIstOjNo);

        if (result == 1) {
            resultValue = "S";
        } else {
            resultValue = "F";
        }

        return resultValue;
    }
}
