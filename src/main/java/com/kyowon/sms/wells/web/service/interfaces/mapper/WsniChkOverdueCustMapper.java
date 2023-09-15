package com.kyowon.sms.wells.web.service.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniChkOverdueCustDvo;

@Mapper
public interface WsniChkOverdueCustMapper {

    WsniChkOverdueCustDvo selectWsniChkOverdueCust(WsniChkOverdueCustDvo dvo);
}
