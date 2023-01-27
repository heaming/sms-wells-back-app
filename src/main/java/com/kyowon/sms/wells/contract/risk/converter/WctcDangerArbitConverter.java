package com.kyowon.sms.wells.contract.risk.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.contract.risk.dto.WctcDangerArbitDto;
import com.kyowon.sms.wells.contract.risk.dvo.WctcDangerArbitDvo;

@Mapper(componentModel = "spring")
public interface WctcDangerArbitConverter {
    @Mapping(source = "dangArbitCdNm", target = "dangArbitCd")
    @Mapping(source = "dangArbitOgNm", target = "dangArbitOgId")
    WctcDangerArbitDvo mapSaveReqWctcDangerArbitDvo(WctcDangerArbitDto.SaveReq dto);
}
