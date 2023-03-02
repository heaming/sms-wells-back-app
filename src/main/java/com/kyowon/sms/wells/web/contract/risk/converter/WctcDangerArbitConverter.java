package com.kyowon.sms.wells.web.contract.risk.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcDangerArbitDvo;

@Mapper(componentModel = "spring")
public interface WctcDangerArbitConverter {
    @Mapping(source = "dangOcStrtmm", target = "dangOcStrtdt")
    WctcDangerArbitDvo mapSaveReqWctcDangerArbitDvo(WctcDangerArbitDto.SaveReq dto);
}
