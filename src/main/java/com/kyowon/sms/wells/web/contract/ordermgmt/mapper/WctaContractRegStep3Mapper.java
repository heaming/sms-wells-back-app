package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractAdrRelDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractAdrpcBasDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractDtlDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractStlmRelDvo;

@Mapper
public interface WctaContractRegStep3Mapper {
    int updateCntrDtlStep3(WctaContractDtlDvo dtl);

    int insertCntrAdrpcBasStep3(@Param("item")
    WctaContractAdrpcBasDvo dvo);

    int insertCntrStlmRelStep3(@Param("item")
    WctaContractStlmRelDvo dvo);

    int insertCntrAdrRelStep3(@Param("item")
    WctaContractAdrRelDvo dvo);

    WctaContractAdrpcBasDvo selectAdrInfoByCntrCstNo(String cntrCstNo);

    int deleteStlmRelsStep3(String cntrNo);

    int deleteAdrpcBasStep3(String cntrNo);

    int deleteAdrRelsStep3(String cntrNo);

}
