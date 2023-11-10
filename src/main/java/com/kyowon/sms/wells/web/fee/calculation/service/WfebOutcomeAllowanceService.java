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
        if (dto.thmInqr()) {
            return mapper.selectOutcomeAllowancesManagerThisMonth(dto);
        }
        //        return mapper.selectOutcomeAllowancesManager(dto);
        return mapper.selectOutcomeAllowancesManagerThisMonth(dto);
    }

    public List<SearchPlannerRes> getOutcomeAllowancesPlanner(SearchReq dto) {
        if (dto.thmInqr()) {
            return mapper.selectOutcomeAllowancesPlannerThisMonth(dto);
        }
        //        return mapper.selectOutcomeAllowancesPlanner(dto);
        return mapper.selectOutcomeAllowancesPlannerThisMonth(dto);

    }

}
