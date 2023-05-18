package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPifDestructionProcsDvo;

@Mapper
public interface WctaPifDestructionProcsMapper {
    int updateCntrAdrpcBas(WctaPifDestructionProcsDvo dvo);

    int updateCntrAdrChHists(WctaPifDestructionProcsDvo dvo);

    int updateCntrStlmBas(WctaPifDestructionProcsDvo dvo);

    int updateCntrStlmChHists(WctaPifDestructionProcsDvo dvo);
}
