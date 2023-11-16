package com.kyowon.sms.wells.web.fee.calculation.service;

import static com.kyowon.sms.wells.web.fee.calculation.dto.WfebOutcomeAllowanceDto.SearchManagerRes;
import static com.kyowon.sms.wells.web.fee.calculation.dto.WfebOutcomeAllowanceDto.SearchReq;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebOutcomeAllowanceDto.SearchPlannerRes;
import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebOutcomeAllowanceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WfebOutcomeAllowanceService {

    private final WfebOutcomeAllowanceMapper mapper;

    public List<SearchManagerRes> getOutcomeAllowancesManager(SearchReq dto) {
        List<SearchManagerRes> outcomeAllowances = mapper.selectOutcomeAllowancesManager(dto);
        if (outcomeAllowances.isEmpty()) {
            outcomeAllowances = mapper.selectOutcomeAllowancesManagerThisMonth(dto);
        }
        return outcomeAllowances;
    }

    public List<SearchPlannerRes> getOutcomeAllowancesPlanner(SearchReq dto) {
        List<SearchPlannerRes> outcomeAllowances = mapper.selectOutcomeAllowancesPlanner(dto);
        if (outcomeAllowances.isEmpty()) {
            outcomeAllowances = mapper.selectOutcomeAllowancesPlannerThisMonth(dto);
        }
        return outcomeAllowances;

    }

}
