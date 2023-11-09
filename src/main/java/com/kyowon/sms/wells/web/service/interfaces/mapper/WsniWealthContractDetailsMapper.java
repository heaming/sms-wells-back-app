package com.kyowon.sms.wells.web.service.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniWealthContractDetailsDvo;

@Mapper
public interface WsniWealthContractDetailsMapper {

    WsniWealthContractDetailsDvo selectWsniWealthContractDetails(WsniWealthContractDetailsDvo dvo);
}
