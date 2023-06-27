package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingOstrQtyDto.EditReq;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedingOstrQtyDvo;

@Mapper(componentModel = "spring")
public interface WsnaSeedingOstrQtyConverter {

    WsnaSeedingOstrQtyDvo mapEditReqToWsnaSeedingOstrQtyDvo(EditReq dto);

}
