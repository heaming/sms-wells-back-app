package com.kyowon.sms.wells.web.service.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSeedingShippingDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSeedingShippingDto.SearchRes;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniSeedingShippingMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-I-0019 wells홈페이지 모종배송내역 조회 API_S17
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.07.11
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsniSeedingShippingInterfaceService {

    private final WsniSeedingShippingMapper mapper;

    public List<SearchRes> getSeedingShippings(SearchReq dto) {
        return mapper.selectSeedingShippings(dto);
    }

}
