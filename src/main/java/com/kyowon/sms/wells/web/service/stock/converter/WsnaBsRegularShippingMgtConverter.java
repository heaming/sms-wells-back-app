package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsRegularShippingMgtDvo;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsRegularShippingMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsRegularShippingMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsRegularShippingMaterialDvo;

@Mapper(componentModel = "spring")
public interface WsnaBsRegularShippingMgtConverter {

    List<SearchRes> mapWsnaShippingManagementDvoToSearchRes(List<WsnaBsRegularShippingMgtDvo> dvo);

    List<WsnaBsRegularShippingMgtDvo> mapSaveReqToWsnaShippingManagementDvo(List<SaveReq> dtos);

    WsnaBsRegularShippingMaterialDvo mapShippingManagementDvoToShippingMaterialDvo(WsnaBsRegularShippingMgtDvo dvo);

    WsnaLogisticsOutStorageAskReqDvo mapMaterialDvoToLogisticDvo(WsnaBsRegularShippingMaterialDvo materialDvo);

    WsnaBsRegularShippingMaterialDvo mapMaterialDvoToMaterialDvo(WsnaBsRegularShippingMaterialDvo materialDvo);

}
