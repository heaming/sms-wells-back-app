package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCntrAprAkDvCdDvo;

@Mapper
public interface WctaContractMapper {
    public List<SearchRes> selectApprovalAskDivides(String standardDt);

    public int deleteApprovalAskDivides(WctaCntrAprAkDvCdDvo dvos);
}
