package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.FindItmOstrNoReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.FindLogisticsCentersRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvReturningGoodsDvo;

@Mapper
public interface WsnaPcsvReturningGoodsMgtMapper {

    List<WsnaPcsvReturningGoodsDvo> selectPcsvReturningGoods(SearchReq dto);

    List<FindLogisticsCentersRes> selectPcsvLogisticsCenters();

    String selectNextItmOstrNo(FindItmOstrNoReq dto);

}
