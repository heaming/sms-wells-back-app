package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterNeedsPsDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaFilterNeedsPsDvo;

@Mapper
public interface WsnaFilterNeedsPsMapper {

    List<WsnaFilterNeedsPsDvo> selectFilterNeedsState(SearchReq dto);

    List<WsnaFilterNeedsPsDvo> selectFilterNeedsStateForB2B(SearchReq dto);

}
