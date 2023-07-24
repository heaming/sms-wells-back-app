package com.kyowon.sms.wells.web.fee.aggregate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaFeeMeetingAttendanceDto;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaManagerFeeMeetingAttendanceDvo;

@Mapper(componentModel = "spring")
public interface WfeaManagerFeeMeetingAttendanceConverter {

    WfeaManagerFeeMeetingAttendanceDvo mapSaveReqToWfeaManagerFeeMeetingAttendanceDvo(
        WfeaFeeMeetingAttendanceDto.SaveReq dto
    );
}
