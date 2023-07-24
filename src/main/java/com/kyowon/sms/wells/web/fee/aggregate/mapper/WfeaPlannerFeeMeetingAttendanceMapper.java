package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaPlannerFeeMeetingAttendanceDvo;

@Mapper
public interface WfeaPlannerFeeMeetingAttendanceMapper {
    int deletePlannerFeeMeetingAttendances(@Param("dvo")
    WfeaPlannerFeeMeetingAttendanceDvo dvo
    );

    int insertPlannerFeeMeetingAttendances(@Param("dvo")
    WfeaPlannerFeeMeetingAttendanceDvo dvo
    );
}
