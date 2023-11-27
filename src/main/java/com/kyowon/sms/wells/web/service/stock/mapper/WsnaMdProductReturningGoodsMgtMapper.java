package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductReturningGoodsMgtDto.FindItmOstrNoReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductReturningGoodsMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProductReturningGoodsMgtDvo;

@Mapper
public interface WsnaMdProductReturningGoodsMgtMapper {

    List<WsnaMdProductReturningGoodsMgtDvo> selectMdProductReturningGoods(SearchReq dto);

    String selectNextItmOstrNo(FindItmOstrNoReq dto);

}
