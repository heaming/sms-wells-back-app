package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.SearchConfirmApprovalBaseReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.SearchConfirmApprovalBaseRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.SearchConfirmAprPsicAksRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.SearchConfirmAprPsicPrchssRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCntrAprAkDvCdDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCntrAprBaseBasDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaContractMapper {
    public List<SearchRes> selectApprovalAskDivides(String standardDt);

    public List<SearchConfirmAprPsicAksRes> selectConfirmAprPsicAks(String cntrNo);

    public List<SearchConfirmAprPsicPrchssRes> selectConfirmAprPsicPrchss(String cntrNo);

    public List<SearchConfirmApprovalBaseRes> selectConfirmApprovalBases(
        List<SearchConfirmApprovalBaseReq> dto
    );

    public PagingResult<SearchConfirmApprovalBaseRes> selectConfirmApprovalBases(
        List<SearchConfirmApprovalBaseReq> dto, PageInfo pageInfo
    );

    public int selectCountConfirmApprovalBases(WctaCntrAprBaseBasDvo dvo);

    public int deleteApprovalAskDivides(WctaCntrAprAkDvCdDvo dvo);

    public int insertConfirmApprovalBases(WctaCntrAprBaseBasDvo dvo);

    public int updateConfirmApprovalBases(WctaCntrAprBaseBasDvo dvo);

    public int deleteConfirmApprovalBases(WctaCntrAprBaseBasDvo dvo);

}
