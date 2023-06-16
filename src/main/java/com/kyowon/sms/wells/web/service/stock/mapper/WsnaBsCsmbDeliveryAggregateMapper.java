package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryAggregateDto.SearchQtysRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryAggregateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsCsmbDeliveryAggregateDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaBsCsmbDeliveryAggregateMapper {
    List<WsnaBsCsmbDeliveryAggregateDvo> selectDeliveryAggregate(SearchReq dto);

    PagingResult<WsnaBsCsmbDeliveryAggregateDvo> selectDeliveryAggregate(SearchReq dto, PageInfo pageInfo);

    List<SearchQtysRes> selectItemQtys(String mngtYmFrom, String mngtYmTo);

    PagingResult<SearchQtysRes> selectItemQtys(SearchReq dto, PageInfo pageInfo);
}
