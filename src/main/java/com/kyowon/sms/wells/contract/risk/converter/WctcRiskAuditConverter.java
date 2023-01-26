package com.kyowon.sms.wells.contract.risk.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.contract.risk.dvo.WctcRiskAuditDvo;

@Mapper(componentModel = "spring")
public interface WctcRiskAuditConverter {
    @Mapping(source = "dangChkId", target = "dangChkId")
    WctcRiskAuditDvo mapSaveReqWctcDangerArbitDvo(String dangChkId);
}
