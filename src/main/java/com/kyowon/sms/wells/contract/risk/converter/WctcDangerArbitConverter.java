package com.kyowon.sms.wells.contract.risk.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.contract.risk.dvo.WctcDangerArbitDvo;

@Mapper(componentModel = "spring")
public interface WctcDangerArbitConverter {
    @Mapping(source = "dangChkId", target = "dangChkId")
    WctcDangerArbitDvo mapSaveReqWctcDangerArbitDvo(String dangChkId);
}
