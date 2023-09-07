package com.kyowon.sms.wells.web.fee.aggregate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaFeeMeetingAttendanceDto;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaPlannerFeeMeetingAttendanceDvo;

@Mapper(componentModel = "spring")
public interface WfeaPlannerFeeMeetingAttendanceConverter {

    WfeaPlannerFeeMeetingAttendanceDvo mapSaveReqToWfeaPlannerFeeMeetingAttendanceDvo(
        WfeaFeeMeetingAttendanceDto.SaveReq dto
    );
}
