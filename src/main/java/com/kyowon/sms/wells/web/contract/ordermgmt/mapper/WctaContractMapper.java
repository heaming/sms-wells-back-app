package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCntrAprAkDvCdDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCntrAprBaseBasDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaContractMapper {
    PagingResult<SearchCntrNoRes> selectContractNumberInqrPages(
        SearchCntrNoReq dto,
        PageInfo pageInfo
    );

    List<SearchHomecareContractsRes> selectHomecareContracts(List<SearchHomecareContractsReq> dtos);

    int updateHomecareContractsDuedt(SaveHomecareContractsReq dto);

    int updateHomecareContractsCandt(SaveHomecareContractsReq dto);

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
