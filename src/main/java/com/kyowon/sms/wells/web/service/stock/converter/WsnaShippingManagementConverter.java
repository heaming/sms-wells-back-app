package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaShippingManagementDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaShippingManagementDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaShippingManagementDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaShippingMaterialDvo;

@Mapper(componentModel = "spring")
public interface WsnaShippingManagementConverter {

    List<SearchRes> mapWsnaShippingManagementDvoToSearchRes(List<WsnaShippingManagementDvo> dvo);

    List<WsnaShippingManagementDvo> mapSaveReqToWsnaShippingManagementDvo(List<SaveReq> dtos);

    WsnaShippingMaterialDvo mapShippingManagementDvoToShippingMaterialDvo(WsnaShippingManagementDvo dvo);

    WsnaLogisticsOutStorageAskReqDvo mapMaterialDvoToLogisticDvo(WsnaShippingMaterialDvo materialDvo);

    WsnaShippingMaterialDvo mapMaterialDvoToMaterialDvo(WsnaShippingMaterialDvo materialDvo);

}
