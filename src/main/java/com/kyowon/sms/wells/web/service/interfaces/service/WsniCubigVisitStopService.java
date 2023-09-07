package com.kyowon.sms.wells.web.service.interfaces.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.converter.WsniCubigVisitStopConverter;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCubigVisitStopDto.CreateReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCubigVisitStopDto.CreateRes;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniCubigVisitStopDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniCubigVisitStopMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsniCubigVisitStopService {
    private final WsniCubigVisitStopMapper mapper;
    private final WsniCubigVisitStopConverter converter;

    private final MessageResourceService messageService;

    public CreateRes createCubigVisitStop(CreateReq dto) {
        WsniCubigVisitStopDvo dvo = converter.mapCreatReqToCubigVisitStopDto(dto);
        mapper.insertCubigVisitStop(dvo);

        return new CreateRes("S001", messageService.getMessage("MSG_ALT_SAVE_DATA"));
    }
}
