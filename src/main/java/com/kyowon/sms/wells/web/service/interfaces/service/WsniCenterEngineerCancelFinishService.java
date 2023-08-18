package com.kyowon.sms.wells.web.service.interfaces.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterEngineerCancelFinishInterfaceDto.EditReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterEngineerCancelFinishInterfaceDto.EditRes;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniCenterEngineerCancelFinishMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsniCenterEngineerCancelFinishService {
    private final WsniCenterEngineerCancelFinishMapper mapper;

    public EditRes editCenterEngineerCancelFinish(EditReq dto) {
        mapper.updateCenterEngineerCancelFinish(dto);

        return new EditRes("SUCCESS", "S001");
    }
}
