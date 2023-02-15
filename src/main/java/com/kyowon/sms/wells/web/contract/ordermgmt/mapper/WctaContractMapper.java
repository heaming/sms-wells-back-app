package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCntrAprAkDvCdDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCntrAprBaseBasDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaContractMapper {
    List<SearchRes> selectApprovalAskDivides(String standardDt);

    List<SearchConfirmAprPsicAksRes> selectConfirmAprPsicAks(String cntrNo);

    List<SearchConfirmAprPsicPrchssRes> selectConfirmAprPsicPrchss(String cntrNo);

    List<SearchConfirmApprovalBaseRes> selectConfirmApprovalBases(
        SearchConfirmApprovalBaseReq dto
    );

    PagingResult<SearchConfirmApprovalBaseRes> selectConfirmApprovalBases(
        SearchConfirmApprovalBaseReq dto, PageInfo pageInfo
    );

    int selectCountConfirmApprovalBases(WctaCntrAprBaseBasDvo dvo);

    int deleteApprovalAskDivides(WctaCntrAprAkDvCdDvo dvo);

    int insertConfirmApprovalBases(WctaCntrAprBaseBasDvo dvo);

    int updateConfirmApprovalBases(WctaCntrAprBaseBasDvo dvo);

    int deleteConfirmApprovalBases(WctaCntrAprBaseBasDvo dvo);

}
