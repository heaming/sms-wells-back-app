package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsRegularShippingMgtDto.SearchProductReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsRegularShippingMgtDto.SearchProductRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsRegularShippingMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsRegularShippingMaterialDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsRegularShippingMgtDvo;

;

@Mapper
public interface WsnaBsRegularShippingMgtMapper {

    List<SearchProductRes> selectProducts(SearchProductReq dto);

    List<WsnaBsRegularShippingMgtDvo> selectShippingItems(SearchReq dto);

    int insertParcelFwInformation(WsnaBsRegularShippingMaterialDvo materialDvo);

    String selectOstAkNo(WsnaBsRegularShippingMgtDvo dvo);

    int insertOutOfStorage(WsnaBsRegularShippingMaterialDvo materialDvo);

    String selectMngtUnit(String partCd);

    int insertWorkResult(WsnaBsRegularShippingMgtDvo dvo);

    int updateBsPeriod(WsnaBsRegularShippingMgtDvo dvo);

    int updateBsAssign(WsnaBsRegularShippingMgtDvo dvo);

    int updateExecution(WsnaBsRegularShippingMgtDvo dvo);

}
