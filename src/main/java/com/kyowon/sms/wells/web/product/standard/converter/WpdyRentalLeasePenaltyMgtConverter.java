package com.kyowon.sms.wells.web.product.standard.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.product.standard.dto.WpdyRentalLeasePenaltyMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyCancelChargeBaseDvo;

@Mapper(componentModel = "spring")
public interface WpdyRentalLeasePenaltyMgtConverter {
    List<WpdyCancelChargeBaseDvo> mapAllCancelChargeBaseDtoToCancelChargeBaseDvo(List<WpdyRentalLeasePenaltyMgtDto.CancelChargeBase> dtos);
}
