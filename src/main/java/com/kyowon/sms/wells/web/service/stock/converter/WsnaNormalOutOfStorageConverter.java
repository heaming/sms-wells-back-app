package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.StandardWareReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageStdgbDvo;

@Mapper(componentModel = "spring")
public interface WsnaNormalOutOfStorageConverter {
    List<WsnaNormalOutOfStorageDvo> mapAllWsnaNormalOutOfStorageDvos(List<CreateReq> list);
    //    List<WsnaNormalOutOfStorageDvo> mapAllCreateReqToWsnaNormalOutOfStorageDvo(List<CreateReq> list);

    WsnaNormalOutOfStorageStdgbDvo mapToWsnaNormalOutOfStorageStdgbDvo(StandardWareReq dto);
}
