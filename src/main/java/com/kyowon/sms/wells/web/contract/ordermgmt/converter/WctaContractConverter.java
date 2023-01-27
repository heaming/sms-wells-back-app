package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.RemoveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCntrAprAkDvCdDvo;

@Mapper(componentModel = "spring")
public interface WctaContractConverter {
    WctaCntrAprAkDvCdDvo mapRemoveReqToWctaCntrAprAkDvCdDvo(RemoveReq dto);
}
