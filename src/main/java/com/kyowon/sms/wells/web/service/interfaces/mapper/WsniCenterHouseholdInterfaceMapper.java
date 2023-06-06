package com.kyowon.sms.wells.web.service.interfaces.mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterHouseholdInterfaceDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterHouseholdInterfaceDto.SearchRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsniCenterHouseholdInterfaceMapper {
    List<SearchRes> selectCustomerHouseholds(SearchReq dto);
}
