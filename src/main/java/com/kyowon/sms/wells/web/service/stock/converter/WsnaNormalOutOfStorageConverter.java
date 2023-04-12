package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageDvo;

@Mapper(componentModel = "spring")
public interface WsnaNormalOutOfStorageConverter {
    List<WsnaNormalOutOfStorageDvo> mapCreateReqToWsnaNormalOutOfStorageDvo(List<CreateReq> list);

}
