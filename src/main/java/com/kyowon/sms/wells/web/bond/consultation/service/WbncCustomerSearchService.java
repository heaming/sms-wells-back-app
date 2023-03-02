package com.kyowon.sms.wells.web.bond.consultation.service;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerSearchDto.SearchReq;
import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerSearchDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncCustomerSearchMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WbncCustomerSearchService {
    private final WbncCustomerSearchMapper mapper;

    public List<SearchRes> getCustomers(SearchReq dto) {
        return mapper.selectCustomers(dto);
    }
}
