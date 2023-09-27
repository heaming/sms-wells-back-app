package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncMonthBsExpcCustAgrgStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncMonthBsExpcCustAgrgStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncMonthBsExpcCustAgrgStateMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncMonthBsExpcCustAgrgStateService {
    private final WsncMonthBsExpcCustAgrgStateMapper mapper;

    public List<SearchRes> getMonthBsExpcCustAgrgState(SearchReq req) {
        return mapper.selectMonthBsExpcCustAgrgState(req);
    }
}
