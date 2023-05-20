package com.kyowon.sms.wells.web.service.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniCenterUnusualItemInterfaceDvo;

@Mapper
public interface WsniCenterUnusualItemInterfaceMapper {
    int insertUnusualItem(WsniCenterUnusualItemInterfaceDvo dvo);

    String selectOgTpCd(String prtnrNo);
}
