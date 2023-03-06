package com.kyowon.sms.wells.web.fee.calculation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.fee.calculation.dto.WfebOutcomeAllowanceDto.*;

@Mapper
public interface WfebOutcomeAllowanceMapper {

    List<SearchRes> selectOutcomeAllowances(
        SearchReq dto
    );

}
