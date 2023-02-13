package com.kyowon.sms.wells.web.contract.risk.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcDangerArbitDvo;

@Mapper(componentModel = "spring")
public interface WctcDangerArbitConverter {
    @Mapping(source = "dangOjPrtnrOgNm", target = "dangMngtOgTpCd")
    @Mapping(source = "dangOjPrtnrNo", target = "dangMngtPrtnrNo")
    @Mapping(source = "dangOcStrtmm", target = "dangOcStrtdt")
    @Mapping(source = "dangOjPrtnrPstnDvNm", target = "dangOjPstnDvCd")
    @Mapping(source = "dangChkNm", target = "dangUncvrCn")
    WctcDangerArbitDvo mapSaveReqWctcDangerArbitDvo(WctcDangerArbitDto.SaveReq dto);
}
