package com.kyowon.sms.wells.web.contract.changeorder.converter;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctaAffiliateAlncDto.SaveReq;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctaAffiliateAlncDvo;

@Mapper(componentModel = "spring")
public interface WctaAffiliateAlncConverter {

    WctaAffiliateAlncDvo saveReqToWctaAffiliateAlncDvo(SaveReq dto);

}
