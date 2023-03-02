package com.kyowon.sms.wells.web.fee.aggregate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaOrganizationNetOrderDto;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaOrganizationNetOrderDvo;

@Mapper(componentModel = "spring")
public interface WfeaOrganizationNetOrderConverter {

    WfeaOrganizationNetOrderDvo mapSaveOgNetOrderReqToWfeaOrganizationNetOrderDvo(
        WfeaOrganizationNetOrderDto.SaveOgNetOrderReq dto
    );

    WfeaOrganizationNetOrderDvo mapSaveBsReqToWfeaOrganizationNetOrderDvo(WfeaOrganizationNetOrderDto.SaveBsReq dto);
}
