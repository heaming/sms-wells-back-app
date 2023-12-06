package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryAggregateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.SearchBldRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsCsmbDeliveryAggregateDvo;

@Mapper
public interface WsnaBsCsmbDeliveryAggregateMapper {

    List<SearchBldRes> selectBuildingList(String mngtYmFrom, String mngtYmTo);

    List<WsnaBsCsmbDeliveryAggregateDvo> selectDeliveryAggregate(SearchReq dto);

}
