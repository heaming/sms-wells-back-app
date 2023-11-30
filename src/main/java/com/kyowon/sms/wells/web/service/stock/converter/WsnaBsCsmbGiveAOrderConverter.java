package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbGiveAOrderDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsCsmbGiveAOrderDvo;

@Mapper(componentModel = "spring")
public interface WsnaBsCsmbGiveAOrderConverter {

    List<WsnaBsCsmbGiveAOrderDvo> mapAllCreateReqToWsnaBsCsmbGiveAOrderDvo(List<CreateReq> dtos);

}
