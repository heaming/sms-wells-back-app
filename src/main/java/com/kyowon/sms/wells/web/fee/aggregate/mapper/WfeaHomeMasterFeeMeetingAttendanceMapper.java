package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaHomeMasterFeeMeetingAttendanceDvo;

@Mapper
public interface WfeaHomeMasterFeeMeetingAttendanceMapper {
    int deleteHomeMasterFeeMeetingAttendances(@Param("dvo")
    WfeaHomeMasterFeeMeetingAttendanceDvo dvo
    );

    int insertHomeMasterFeeMeetingAttendances(@Param("dvo")
    WfeaHomeMasterFeeMeetingAttendanceDvo dvo
    );
}
