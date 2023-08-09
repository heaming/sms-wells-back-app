package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaInAggregateDto.SearchReq;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaInAggregateConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaInAggregateDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaInAggregateMapper;

import lombok.RequiredArgsConstructor;

/**
 *
 * <pre>
 * K-W-SV-U-0265M01 입고 집계
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.07
 */
@Service
@RequiredArgsConstructor
public class WsnaInAggregateService {

    private final WsnaInAggregateMapper mapper;
    private final WsnaInAggregateConverter converter;

    public List<WsnaInAggregateDvo> getInAggregate(SearchReq dto) {
        return converter.mapSearchResDvo(mapper.selectInAggregateList(dto));
    }
}
