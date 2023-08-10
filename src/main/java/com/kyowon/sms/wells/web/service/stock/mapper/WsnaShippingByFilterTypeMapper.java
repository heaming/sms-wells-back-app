package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaShippingByFilterTypeDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaShippingByFilterTypeDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaShippingByFilterTypeMapper {

    List<SearchPdRes> selectShippingByFilterProducts();

    PagingResult<SearchRes> selectShippingByFilterTypes(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectShippingByFilterTypes(SearchReq dto);

    int updateSvWkOstrIz(WsnaShippingByFilterTypeDvo dvo);

}
