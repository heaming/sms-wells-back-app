package com.kyowon.sms.wells.web.service.interfaces.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSeedingShippingDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSeedingShippingDto.SearchRes;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniSeedingShippingMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

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

    public PagingResult<SearchRes> getSeedingShippings(SearchReq dto) {

        PageInfo pageinfo = new PageInfo();
        pageinfo.setPageIndex(dto.pageIndex());
        pageinfo.setPageSize(dto.pageSize());

        return mapper.selectSeedingShippings(dto, pageinfo);
    }

}
