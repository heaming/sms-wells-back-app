package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctcPartnerInfInqrPywdDvo;

@Mapper
public interface WctcPartnerInfInqrPywdMapper {
    WctcPartnerInfInqrPywdDvo selectPartnerInfInqrPywd(String cntrNo);
}
