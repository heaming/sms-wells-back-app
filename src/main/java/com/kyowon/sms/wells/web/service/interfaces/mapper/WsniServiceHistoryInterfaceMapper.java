package com.kyowon.sms.wells.web.service.interfaces.mapper;

import static com.kyowon.sms.wells.web.service.interfaces.dto.WsniServiceHistoryInterfaceDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniServiceHistoryInterfaceDvo;

@Mapper
public interface WsniServiceHistoryInterfaceMapper {
    List<SearchRes> selectServiceHistory(WsniServiceHistoryInterfaceDvo dvo);

    List<WsniServiceHistoryInterfaceDvo> selectMembershipContracts(WsniServiceHistoryInterfaceDvo dvo);
}
