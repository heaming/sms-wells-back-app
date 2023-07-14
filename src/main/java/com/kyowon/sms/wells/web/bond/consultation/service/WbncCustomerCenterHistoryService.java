package com.kyowon.sms.wells.web.bond.consultation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncCustomerCenterHistoryMapper;

import lombok.RequiredArgsConstructor;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerCenterHistoryDto.*;

@Service
@RequiredArgsConstructor
public class WbncCustomerCenterHistoryService {
    private final WbncCustomerCenterHistoryMapper mapper;

    public List<FindRes> getCustomerCenterHistories(String cstNo) {
        return mapper.selectCustomerCenterHistories(cstNo);
    }
}
