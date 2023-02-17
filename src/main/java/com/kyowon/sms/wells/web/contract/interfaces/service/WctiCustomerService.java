package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerDto.SearchRes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiCustomerMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiCustomerService {

    private final WctiCustomerMapper mapper;

    public List<SearchRes> getCustomers(SearchReq req) {
        List<SearchRes> resList = new ArrayList<>();

        resList.addAll(mapper.selectCustomers(req));
        resList.addAll(mapper.selectProspactCustomers(req));

        return resList;
    }
}
