package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingPackageCtrQtyRegDto.SaveReq;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedingPackageCtrQtyRegDvo;

@Mapper(componentModel = "spring")
public interface WsnaSeedingPackageCtrQtyRegConverter {

    List<WsnaSeedingPackageCtrQtyRegDvo> mapAllSaveReqToWsnaSeedingPackageCtrQtyRegDvo(List<SaveReq> dtos);

}
