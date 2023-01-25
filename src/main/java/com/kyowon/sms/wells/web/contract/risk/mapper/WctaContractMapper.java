package com.kyowon.sms.wells.web.contract.risk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.risk.dto.WctaContractDto.SearchRes;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctaCntrAprAkDvCdDvo;

@Mapper
public interface WctaContractMapper {
    public List<SearchRes> selectApprovalAskDivides(String standardDt);

    public int removeApprovalAskDivides(WctaCntrAprAkDvCdDvo dvos);
}
