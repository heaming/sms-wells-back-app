package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctaInstallationReqdDtInMapper {

    int updateInstallReqdDt(String cntrNo, String cntrSn, String istDt, String reqdDt);
}
