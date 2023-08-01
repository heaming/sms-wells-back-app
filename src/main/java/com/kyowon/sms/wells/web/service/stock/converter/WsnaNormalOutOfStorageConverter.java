package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.*;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageStdgbDvo;

@Mapper(componentModel = "spring")
public interface WsnaNormalOutOfStorageConverter {
    List<WsnaNormalOutOfStorageDvo> mapAllCreateReqToWsnaNormalOutOfStorageDvo(List<CreateReq> dtos);

    List<WsnaNormalOutOfStorageDvo> mapAllRemoveReqToWsnaNormalOutOfStorageDvo(List<RemoveReq> dtos);

    WsnaNormalOutOfStorageStdgbDvo mapStandardWareReqToWsnaNormalOutOfStorageStdgbDvo(StandardWareReq dto);
}
