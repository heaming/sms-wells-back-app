package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaManagerFeeMeetingAttendanceDvo;

@Mapper
public interface WfeaManagerFeeMeetingAttendanceMapper {
    int deleteManagerFeeMeetingAttendances(WfeaManagerFeeMeetingAttendanceDvo dvo);

    int insertManagerFeeMeetingAttendances(WfeaManagerFeeMeetingAttendanceDvo dvo);
}
