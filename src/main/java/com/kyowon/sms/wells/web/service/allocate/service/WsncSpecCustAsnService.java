package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncSpecCustAsnConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncSpecCustAsnDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustAsnDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncSpecCustAsnMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsncSpecCustAsnService {

    private final WsncSpecCustAsnMapper mapper;

    private final WsncSpecCustAsnConverter converter;

    @Transactional
    public int processSpecCustAsn(WsncSpecCustAsnDvo dvo) throws Exception {
        List<WsncSpecCustAsnDvo> asnList = mapper.selectSpecCustAsn(dvo);

        for(WsncSpecCustAsnDvo asnDvo : asnList){
            mapper.insertSpecCustAsn(asnDvo);
        }

        return 1;
    }

    public int processSpecCustAsn(WsncSpecCustAsnDto.SaveProcessReq dto) throws Exception {
        WsncSpecCustAsnDvo dvo = converter.mapSaveProcessReqToSpecCustAsnDvo(dto);
        return processSpecCustAsn(dvo);
    }
}
