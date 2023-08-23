package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWasteFilterCollectionPsDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWasteFilterCollectionPsDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnaWasteFilterCollectionPsMapper {

    List<SearchRes> selectWasteFilterCollections(SearchReq dto);

}
