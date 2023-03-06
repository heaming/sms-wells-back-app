package com.kyowon.sms.wells.web.fee.calculation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.calculation.converter.WfebOutcomeAllowanceConverter;
import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebOutcomeAllowanceMapper;

import static com.kyowon.sms.wells.web.fee.calculation.dto.WfebOutcomeAllowanceDto.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WfebOutcomeAllowanceService {

    private final WfebOutcomeAllowanceMapper mapper;
    private final WfebOutcomeAllowanceConverter converter;

    public List<SearchRes> getOutcomeAllowances(SearchReq dto) {
        return mapper.selectOutcomeAllowances(dto);
    }

}
