package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaShippingManagementDto.SearchProductReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaShippingManagementDto.SearchProductRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaShippingManagementDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaShippingManagementDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaShippingMaterialDvo;

;

@Mapper
public interface WsnaShippingManagementMapper {

    List<SearchProductRes> selectProducts(SearchProductReq dto);

    List<WsnaShippingManagementDvo> selectShippingItems(SearchReq dto);

    int insertParcelFwInformation(WsnaShippingMaterialDvo materialDvo);

    String selectOstAkNo(WsnaShippingManagementDvo dvo);

    int insertOutOfStorage(WsnaShippingMaterialDvo materialDvo);

    String selectMngtUnit(String partCd);

    int insertWorkResult(WsnaShippingManagementDvo dvo);

    int updateBsPeriod(WsnaShippingManagementDvo dvo);

    int updateBsAssign(WsnaShippingManagementDvo dvo);

    int updateExecution(WsnaShippingManagementDvo dvo);

}
