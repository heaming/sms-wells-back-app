package com.kyowon.sms.wells.web.contract.risk.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.risk.dto.WctaContractDto.RemoveReq;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctaCntrAprAkDvCdDvo;

@Mapper(componentModel = "spring")
public interface WctaContractConverter {
    WctaCntrAprAkDvCdDvo mapRemoveReqToWctaCntrAprAkDvCdDvo(RemoveReq dto);
}
