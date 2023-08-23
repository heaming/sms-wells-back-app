package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvOutOfStorageMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvOutOfStorageSaveDvo;

@Mapper(componentModel = "spring")
public interface WsnaPcsvOutOfStorageSaveConverter {

    List<WsnaPcsvOutOfStorageSaveDvo> mapSaveReqToPcsvOutOfStorageDvo(List<SaveReq> dto);

    WsnaLogisticsOutStorageAskReqDvo mapPcsvOutOfStorageDvoToLogisticDvo(WsnaPcsvOutOfStorageSaveDvo dvo);
}
