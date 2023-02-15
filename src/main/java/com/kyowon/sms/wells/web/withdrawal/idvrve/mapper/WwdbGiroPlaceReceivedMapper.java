package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroPlaceReceivedDto.FindReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroPlaceReceivedDto.FindRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroPlaceReceivedDvo;

@Mapper
public interface WwdbGiroPlaceReceivedMapper {
    FindRes selectGiroPlaceReceived(FindReq dto);

    int insertGiroPlaceReceived(WwdbGiroPlaceReceivedDvo dvo) throws Exception;

    int updateGiroPlaceReceived(WwdbGiroPlaceReceivedDvo dvo) throws Exception;

    int insertGiroPlaceReceivedHistory(WwdbGiroPlaceReceivedDvo dvo) throws Exception;

}
