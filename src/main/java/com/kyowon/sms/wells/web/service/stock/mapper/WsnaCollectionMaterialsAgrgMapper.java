package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaCollectionMaterialsAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaCollectionMaterialsAgrgDto.SearchRes;

@Mapper
public interface WsnaCollectionMaterialsAgrgMapper {

    List<SearchRes> selectCollectionMaterialsAgrgs(SearchReq dto);
}
