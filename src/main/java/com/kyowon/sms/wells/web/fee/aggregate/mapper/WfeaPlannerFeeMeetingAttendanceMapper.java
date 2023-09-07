package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaPlannerFeeMeetingAttendanceDvo;

@Mapper
public interface WfeaPlannerFeeMeetingAttendanceMapper {
    int deletePlannerFeeMeetingAttendances(WfeaPlannerFeeMeetingAttendanceDvo dvo);

    int insertPlannerFeeMeetingAttendances(WfeaPlannerFeeMeetingAttendanceDvo dvo);
}
