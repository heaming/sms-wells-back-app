package com.kyowon.sms.wells.web.bond.transfer.converter;

import com.kyowon.sms.wells.web.bond.transfer.dvo.WbnaBondPartTransferDvo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaBondPartTransferDto.CreateReq;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaBondPartTransferDto.EditReq;

@Mapper(componentModel = "spring")
public interface WbnaBondPartTransferConverter {

    @Mapping(target = "cntrNo", ignore = true)
    @Mapping(target = "cntrSn", ignore = true)
    @Mapping(target = "cstNo", ignore = true)
    WbnaBondPartTransferDvo mapCreateReqToEbnaBondPartTransferDvo(CreateReq dto);

    WbnaBondPartTransferDvo mapEditReqToEbnaBondPartTransferDvo(EditReq dto);
}
