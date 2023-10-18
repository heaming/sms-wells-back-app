package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitCustomerRglvlAgrgDto;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncVisitCustomerRglvlAgrgMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncVisitCustomerRglvlAgrgService {
    private final WsncVisitCustomerRglvlAgrgMapper mapper;

    public List<WsncVisitCustomerRglvlAgrgDto.SearchRes> getVisitCustomerRglvlAgrg(WsncVisitCustomerRglvlAgrgDto.SearchReq dto){
        return mapper.selectVisitCustomerRglvlAgrgs(dto);
    }
}
