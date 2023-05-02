package com.kyowon.sms.wells.web.service.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniCenterUnuitmSaveInterfaceDvo;

@Mapper
public interface WsniCenterUnuitmSaveInterfaceMapper {
    int insertUnusualItem(WsniCenterUnuitmSaveInterfaceDvo dvo);

    String selectOgTpCd(String prtnrNo);
}
