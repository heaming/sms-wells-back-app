package com.kyowon.sms.wells.web.service.allocate.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncExpProductStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncExpProductStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncExpProductStateMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncExpProductStateService {
    private final WsncExpProductStateMapper mapper;

    public PagingResult<SearchRes> getExpProductStateStates(
        SearchReq req, PageInfo pageInfo
    ) {
        return mapper.selectExpProductState(req, pageInfo);
    }

}
