package com.kyowon.sms.wells.web.service.stock.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterOutOfStorageAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaFilterOutOfStorageAgrgMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaFilterOutOfStorageAgrgService {

    private final WsnaFilterOutOfStorageAgrgMapper mapper;

    public List<HashMap<String, String>> getFilterOutOfStorageAgrgs(SearchReq dto) {
        return mapper.selectFilterOutOfStorageAgrgs(dto);
    }
}
