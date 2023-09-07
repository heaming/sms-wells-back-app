package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaWareItemReceivingAndPayingStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaWareItemReceivingAndPayingStateDto.SearchRes;

@Mapper
public interface WsnaWareItemReceivingAndPayingStateMapper {

    List<SearchRes> selectWareItemReceivingAndPayingStates(SearchReq dto);
}
