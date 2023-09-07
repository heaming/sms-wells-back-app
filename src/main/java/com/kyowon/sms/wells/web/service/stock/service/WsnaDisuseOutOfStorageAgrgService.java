package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaDisuseOutOfStorageAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaDisuseOutOfStorageAgrgDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaDisuseOutOfStorageAgrgMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaDisuseOutOfStorageAgrgService {

    private final WsnaDisuseOutOfStorageAgrgMapper mapper;

    public List<SearchRes> getDisuseOutOfStorageAgrgs(SearchReq dto) {
        return mapper.selectDisuseOutOfStorageAgrgs(dto);
    }
}
