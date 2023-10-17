package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncExpProductDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncExpProductDto.SearchRes;

@Mapper
public interface WsncExpProductMapper {

    List<SearchRes> selectExpProduct(SearchReq dto);

}
