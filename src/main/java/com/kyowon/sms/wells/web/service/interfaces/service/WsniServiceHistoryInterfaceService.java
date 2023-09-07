package com.kyowon.sms.wells.web.service.interfaces.service;

import static com.kyowon.sms.wells.web.service.interfaces.dto.WsniServiceHistoryInterfaceDto.SearchReq;
import static com.kyowon.sms.wells.web.service.interfaces.dto.WsniServiceHistoryInterfaceDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.converter.WsniServiceHistoryInterfaceConverter;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniServiceHistoryInterfaceDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniServiceHistoryInterfaceMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsniServiceHistoryInterfaceService {
    private final WsniServiceHistoryInterfaceMapper mapper;
    private final WsniServiceHistoryInterfaceConverter converter;

    public List<SearchRes> getServiceHistory(SearchReq dto) {
        WsniServiceHistoryInterfaceDvo dvo = converter.mapSearchReqToServiceHistoryInterfaceDvo(dto);

        List<SearchRes> res = mapper.selectServiceHistory(dvo);

        List<WsniServiceHistoryInterfaceDvo> mDvos = mapper.selectMembershipContracts(dvo);
        if (mDvos.size() > 0) {
            for (WsniServiceHistoryInterfaceDvo mDvo : mDvos) {
                List<SearchRes> mRes = mapper.selectServiceHistory(mDvo);
                res.addAll(mRes);
            }
        }

        return res;
    }
}
