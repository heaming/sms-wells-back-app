package com.kyowon.sms.wells.web.fee.calculation.mapper;

import static com.kyowon.sms.wells.web.fee.calculation.dto.WfebOutcomeAllowanceDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WfebOutcomeAllowanceMapper {

    List<SearchManagerRes> selectOutcomeAllowancesManagerThisMonth(
        SearchReq dto
    );

    List<SearchManagerRes> selectOutcomeAllowancesManager(
        SearchReq dto
    );

    List<SearchPlannerRes> selectOutcomeAllowancesPlannerThisMonth(
        SearchReq dto
    );

    List<SearchPlannerRes> selectOutcomeAllowancesPlanner(
        SearchReq dto
    );

    List<AwBaseInfoRes> selectAllowanceBaseInfo(
        String perfYm
    );

    void deleteOutcomeAllowancesManager(
        List<SaveMoReq> dtos
    );

    int insertOutcomeAllowancesManager(
        List<SaveMoReq> dtos
    );

    void deleteOutcomeAllowancesPlanner(
        List<SavePoReq> dtos
    );

    int insertOutcomeAllowancesPlanner(
        List<SavePoReq> dtos
    );
}
