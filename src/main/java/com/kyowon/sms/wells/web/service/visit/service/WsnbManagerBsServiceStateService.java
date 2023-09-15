package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbManagerBsServiceStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbManagerBsServiceStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbManagerBsServiceStateMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnbManagerBsServiceStateService {
    private final WsnbManagerBsServiceStateMapper mapper;

    public List<SearchRes> getManagerBsServiceStates(SearchReq dto) {
        return mapper.selectManagerBsServiceState(dto);
    }

}
