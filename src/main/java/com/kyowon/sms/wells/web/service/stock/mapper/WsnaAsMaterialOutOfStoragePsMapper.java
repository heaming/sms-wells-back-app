package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialOutOfStoragePsDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsMaterialOutOfStoragePsDvo;

@Mapper
public interface WsnaAsMaterialOutOfStoragePsMapper {
    List<WsnaAsMaterialOutOfStoragePsDvo> selectAsMaterialOutOfStorages(SearchReq dto);
}
