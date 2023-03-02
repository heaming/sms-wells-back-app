package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaItemBaseInformationConverter;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaItemBaseInformationMapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemBaseInformationDto.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaItemBaseInformationService {

    private final WsnaItemBaseInformationMapper mapper;
    private final WsnaItemBaseInformationConverter converter;

    public List<SearchRes> getItemBaseInformations(SearchReq dto) {
        return this.mapper.selectItemBaseInformations(dto);
    }

    public List<OstrRes> getItemBaseInformationsOutOf(SearchReq dto) {
        return this.mapper.selectItemBaseInformationsOutOf(dto);
    }
}
