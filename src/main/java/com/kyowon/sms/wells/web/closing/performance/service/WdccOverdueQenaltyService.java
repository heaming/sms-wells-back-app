package com.kyowon.sms.wells.web.closing.performance.service;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccOverdueQenaltyDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccOverdueQenaltyDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccOverdueQenaltyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WdccOverdueQenaltyService {

    private final WdccOverdueQenaltyMapper mapper;

    public List<SearchRes> getSalesBondAdditionalChargesAggregate(
        SearchReq req
    ) {
        return mapper.selectSalesBondAdditionalChargesAggregate(req);
    }

    public List<SearchRes> getSalesBondAdditionalChargesDates(
        SearchReq req
    ) {
        return mapper.selectSalesBondAdditionalChargesDates(req);
    }

    public List<SearchRes> getSalesBondAdditionalChargesOrders(
        SearchReq req
    ) {
        return mapper.selectSalesBondAdditionalChargesOrders(req);
    }

    public List<SearchRes> getSalesBondAdditionalChargesMembers(
        SearchReq req
    ) {
        return mapper.selectSalesBondAdditionalChargesMembers(req);
    }
}
