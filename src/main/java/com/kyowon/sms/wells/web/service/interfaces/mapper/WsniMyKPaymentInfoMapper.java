package com.kyowon.sms.wells.web.service.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniMyKPaymentInfoDvo;

@Mapper
public interface WsniMyKPaymentInfoMapper {

    WsniMyKPaymentInfoDvo selectWsniMyKPaymentInfo(WsniMyKPaymentInfoDvo dvo);
}
