package com.kyowon.sms.wells.web.service.interfaces.mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSidingServiceChangesDto.*;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniSidingServiceChangesDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsniSidingServiceChangesMapper {

    int insertSdingAsAkHist(SaveReq req);

    int deleteSdingAskAk(SaveReq req);

    WsniSidingServiceChangesDvo selectCustomer(SaveReq req);

    Integer selectSidingAkCount(SaveReq req);

    int updateSidingAk(SaveReq req);

    int insertSidingAk(SaveReq req);

    Integer selectBsTarget(SaveReq req);
}
