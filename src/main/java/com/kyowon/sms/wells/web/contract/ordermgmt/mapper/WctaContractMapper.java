package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.SearchConfirmAprPsicAksRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.SearchConfirmAprPsicPrchssRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCntrAprAkDvCdDvo;

@Mapper
public interface WctaContractMapper {
    public List<SearchRes> selectApprovalAskDivides(String standardDt);

    public List<SearchConfirmAprPsicAksRes> selectConfirmAprPsicAks(String cntrNo);

    public List<SearchConfirmAprPsicPrchssRes> selectConfirmAprPsicPrchss(String cntrNo);

    public int deleteApprovalAskDivides(WctaCntrAprAkDvCdDvo dvos);
}
