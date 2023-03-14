package com.kyowon.sms.wells.web.contract.salesstatus.service;

import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WctePartnerAggregationDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WctePartnerAggregationDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.salesstatus.mapper.WctePartnerAggregationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctePartnerAggregationService {

    private final WctePartnerAggregationMapper mapper;

    public List<SearchRes> getPartnerAggregations(SearchReq dto) {
        return mapper.selectPartnerAggregations(dto, mapper.selectBmno());
    }
}
