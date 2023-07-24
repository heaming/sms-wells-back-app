package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaManagerFeeMeetingAttendanceDvo;

import org.apache.ibatis.annotations.Param;

@Mapper
public interface WfeaManagerFeeMeetingAttendanceMapper {
    int deleteManagerFeeMeetingAttendances(@Param("dvo")
    WfeaManagerFeeMeetingAttendanceDvo dvo
    );

    int insertManagerFeeMeetingAttendances(@Param("dvo")
    WfeaManagerFeeMeetingAttendanceDvo dvo
    );
}
