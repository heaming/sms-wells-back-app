package com.kyowon.sms.wells.web.fee.aggregate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaWelsMngerSettlementAwDto;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaWelsMngerSettlementAwDvo;

@Mapper(componentModel = "spring")
public interface WfeaWelsMngerSettlementAwConverter {

    WfeaWelsMngerSettlementAwDvo mapSaveOpngReqToWfeaWelsMngerSettlementAwDvo(
        WfeaWelsMngerSettlementAwDto.SaveOpngReq dto
    );

    WfeaWelsMngerSettlementAwDvo mapSaveReqToWfeaWelsMngerSettlementAwDvo(WfeaWelsMngerSettlementAwDto.SaveReq dto);

    WfeaWelsMngerSettlementAwDvo mapSaveConfirmReqToWfeaWelsMngerSettlementAwDvo(
        WfeaWelsMngerSettlementAwDto.SaveConfirmReq dto
    );
}
