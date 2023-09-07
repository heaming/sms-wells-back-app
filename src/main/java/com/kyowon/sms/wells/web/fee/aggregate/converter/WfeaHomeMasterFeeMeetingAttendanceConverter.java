package com.kyowon.sms.wells.web.fee.aggregate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaFeeMeetingAttendanceDto;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaHomeMasterFeeMeetingAttendanceDvo;

@Mapper(componentModel = "spring")
public interface WfeaHomeMasterFeeMeetingAttendanceConverter {

    WfeaHomeMasterFeeMeetingAttendanceDvo mapSaveReqToWfeaHomeMasterFeeMeetingAttendanceDvo(
        WfeaFeeMeetingAttendanceDto.SaveReq dto
    );
}
