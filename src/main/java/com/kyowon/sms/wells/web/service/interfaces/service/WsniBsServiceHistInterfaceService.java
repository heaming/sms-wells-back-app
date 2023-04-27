package com.kyowon.sms.wells.web.service.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniBsServiceHistInterfaceDto;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniBsServiceHistInterfaceMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsniBsServiceHistInterfaceService {

    private final WsniBsServiceHistInterfaceMapper mapper;

    public List<WsniBsServiceHistInterfaceDto.SearchRes> getBsServiceHistories(
        WsniBsServiceHistInterfaceDto.SearchReq dto
    ) {
        return mapper.selectBsServiceHistory(dto);
    }
}
