package com.kyowon.sms.wells.web.contract.changeorder.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbContractChangeMngtDvo;

@Mapper(componentModel = "spring")
public interface WctbContractChangeMngtConverter {
    WctbContractChangeMngtDvo mapEditPartnerReqToWctbContractChangeMngtDvo(
        WctbContractChangeMngtDto.EditPartnerReq dto
    );
}
