package com.kyowon.sms.wells.web.service.common.service;

import com.kyowon.sms.wells.web.service.common.dto.WsnyPdlvDto;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyPdlvMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WsnyPdlvService {
    private final WsnyPdlvMapper mapper;

    public List<WsnyPdlvDto.SearchRes> getPlaceOfDeliveryList(){
        return mapper.selectPlaceOfDeliverys();
    }

}
