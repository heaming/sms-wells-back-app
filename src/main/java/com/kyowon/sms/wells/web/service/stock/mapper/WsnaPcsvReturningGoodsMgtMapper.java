package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvReturningGoodsSaveDvo;

@Mapper
public interface WsnaPcsvReturningGoodsMgtMapper {

    List<SearchRes> selectPcsvReturningGoods(SearchReq dto);

    List<FindLogisticsCentersRes> selectPcsvLogisticsCenters();

    List<FindProductsRes> selectPcsvProducts(FindProductsReq dto);

    String selectNextItmOstrNo(FindItmOstrNoReq dto);

    String selectNextItmStrNo(FindItmStrNoReq dto);

    List<WsnaPcsvReturningGoodsSaveDvo> selectLogisticsPcsvReturningGoodsAskInfo(String itmOstrNo, String ostrSn);
}
