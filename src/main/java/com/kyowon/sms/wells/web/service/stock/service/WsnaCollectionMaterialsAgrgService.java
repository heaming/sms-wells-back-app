package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaCollectionMaterialsAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaCollectionMaterialsAgrgDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaCollectionMaterialsAgrgMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaCollectionMaterialsAgrgService {

    private final WsnaCollectionMaterialsAgrgMapper mapper;

    public List<SearchRes> getFilterOutOfStorageAgrgs(SearchReq dto) {
        return mapper.selectCollectionMaterialsAgrgs(dto);
    }
}
