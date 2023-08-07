package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaHomeMasterFeeMeetingAttendanceDvo;

@Mapper
public interface WfeaHomeMasterFeeMeetingAttendanceMapper {
    int deleteHomeMasterFeeMeetingAttendances(WfeaHomeMasterFeeMeetingAttendanceDvo dvo);

    int insertHomeMasterFeeMeetingAttendances(WfeaHomeMasterFeeMeetingAttendanceDvo dvo);
}
