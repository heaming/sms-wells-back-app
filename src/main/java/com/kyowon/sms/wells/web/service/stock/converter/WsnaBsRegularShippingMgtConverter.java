package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsRegularShippingMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsRegularShippingMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsRegularShippingMaterialDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsRegularShippingMgtDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskReqDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WsnaBsRegularShippingMgtConverter {

    SearchRes mapWsnaShippingManagementDvoToSearchRes(WsnaBsRegularShippingMgtDvo dvo);

    PagingResult<SearchRes> mapWsnaShippingManagementDvoToSearchRes(List<WsnaBsRegularShippingMgtDvo> dvo);

    List<WsnaBsRegularShippingMgtDvo> mapSaveReqToWsnaShippingManagementDvo(List<SaveReq> dtos);

    WsnaBsRegularShippingMaterialDvo mapShippingManagementDvoToShippingMaterialDvo(WsnaBsRegularShippingMgtDvo dvo);

    WsnaLogisticsOutStorageAskReqDvo mapMaterialDvoToLogisticDvo(WsnaBsRegularShippingMaterialDvo materialDvo);

    WsnaBsRegularShippingMaterialDvo mapMaterialDvoToMaterialDvo(WsnaBsRegularShippingMaterialDvo materialDvo);

}
