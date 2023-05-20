package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;

@Mapper
public interface WctaContractRegStep3Mapper {
    int updateCntrDtlStep3(WctaContractDtlDvo dtl);

    int insertCntrAdrpcBasStep3(@Param("item")
    WctaContractAdrpcBasDvo dvo);

    int insertCntrStlmRelStep3(@Param("item")
    WctaContractStlmRelDvo dvo);

    int insertCntrAdrRelStep3(@Param("item")
    WctaContractAdrRelDvo dvo);

    WctaContractRegStep3Dvo selectAdrInfoByCntrCstNo(String cntrCstNo);

    int deleteStlmRelsStep3(String cntrNo);

    int deleteAdrpcBasStep3(String cntrNo);

    int deleteAdrRelsStep3(String cntrNo);

}
