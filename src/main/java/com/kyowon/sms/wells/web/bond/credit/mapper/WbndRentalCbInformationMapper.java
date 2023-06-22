package com.kyowon.sms.wells.web.bond.credit.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WbndRentalCbInformationMapper {

    String getTransSeq(); //렌탈CB 기관전문 관리번호

    Map<String, Object> getTransCdMsg(Map<String, Object> map); //렌탈CB 오류사유

    int insertCBSearchTrans(Map<String, Object> map);

}
