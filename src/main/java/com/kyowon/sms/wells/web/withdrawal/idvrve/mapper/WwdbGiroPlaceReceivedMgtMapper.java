package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroPlaceReceivedMgtDto.FindReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroPlaceReceivedMgtDto.FindRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroPlaceReceivedMgtDvo;

@Mapper
public interface WwdbGiroPlaceReceivedMgtMapper {
    FindRes selectGiroPlaceReceived(FindReq dto);

    int insertGiroPlaceReceived(WwdbGiroPlaceReceivedMgtDvo dvo) throws Exception;

    int updateGiroPlaceReceived(WwdbGiroPlaceReceivedMgtDvo dvo) throws Exception;

    int insertGiroPlaceReceivedHistory(WwdbGiroPlaceReceivedMgtDvo dvo) throws Exception;

    int selectGiroPlaceDupliCationReceived(FindReq dto);
}
