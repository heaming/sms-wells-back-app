package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractExceptionDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractExOjBasDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractExOjDtlDvo;

@Mapper(componentModel = "spring")
public interface WctaContractExceptionConverter {

    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WctaContractExOjBasDvo mapSaveReqToWctaContractExOjBasDvo(SaveReq dto);

    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WctaContractExOjDtlDvo mapSaveReqToWctaContractExOjDtlDvo(SaveReq dto);
}
