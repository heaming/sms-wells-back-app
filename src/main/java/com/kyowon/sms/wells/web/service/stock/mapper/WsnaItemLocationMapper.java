package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemLocationDvo;

@Mapper
public interface WsnaItemLocationMapper {
    List<SearchRes> selectItemLocations(SearchReq dto);

    int saveItemLocations(List<WsnaItemLocationDvo> voList);
}
