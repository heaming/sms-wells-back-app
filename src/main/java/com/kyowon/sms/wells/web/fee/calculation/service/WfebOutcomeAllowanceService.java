package com.kyowon.sms.wells.web.fee.calculation.service;

import static com.kyowon.sms.wells.web.fee.calculation.dto.WfebOutcomeAllowanceDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebOutcomeAllowanceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WfebOutcomeAllowanceService {

    private final WfebOutcomeAllowanceMapper mapper;

    public List<SearchManagerRes> getOutcomeAllowancesManager(SearchReq dto) {
        List<SearchManagerRes> outcomeAllowances = mapper.selectOutcomeAllowancesManager(dto);
        if (outcomeAllowances.isEmpty() || dto.rtmInqr()) {
            outcomeAllowances = mapper.selectOutcomeAllowancesManagerThisMonth(dto);
        }
        return outcomeAllowances;
    }

    public List<SearchPlannerRes> getOutcomeAllowancesPlanner(SearchReq dto) {
        List<SearchPlannerRes> outcomeAllowances = mapper.selectOutcomeAllowancesPlanner(dto);
        if (outcomeAllowances.isEmpty() || dto.rtmInqr()) {
            outcomeAllowances = mapper.selectOutcomeAllowancesPlannerThisMonth(dto);
        }
        return outcomeAllowances;

    }

    public List<AwBaseInfoRes> getOutcomeAllowancesBaseInfo(String perfYm) {
        return mapper.selectAllowanceBaseInfo(perfYm);
    }

    public int saveOutcomeAllowancesManager(List<SaveMoReq> dtos) {
        if (dtos.isEmpty())
            return 0;
        mapper.deleteOutcomeAllowancesManager(dtos);
        return mapper.insertOutcomeAllowancesManager(dtos);
    }

    public int saveOutcomeAllowancesPlanner(List<SavePoReq> dtos) {
        if (dtos.isEmpty())
            return 0;
        mapper.deleteOutcomeAllowancesPlanner(dtos);
        return mapper.insertOutcomeAllowancesPlanner(dtos);
    }
}
