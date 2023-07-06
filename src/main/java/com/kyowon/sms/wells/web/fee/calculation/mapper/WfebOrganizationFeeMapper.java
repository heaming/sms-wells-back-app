package com.kyowon.sms.wells.web.fee.calculation.mapper;

import java.util.HashMap;
import java.util.List;

import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebOrganizationFeeDvo;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebOrganizationFeeDto.*;

@Mapper
public interface WfebOrganizationFeeMapper {

    List<SearchHmstRes> selectHomeMasterFees(
        SearchHmstReq dto
    );

    List<SearchHmstBrmgrRes> selectHomeMasterBranchManagerFees(
        SearchHmstReq dto
    );

    List<SearchMngerRes> selectManagerFees(
        SearchMngerReq dto
    );

    List<SearchMngerBrmgrRes> selectManagerBranchManagerFees(
        SearchMngerReq dto
    );

    List<SearchMngerTotalRes> selectManagerTotalFees(
        SearchMngerReq dto
    );

    List<SearchPlarRes> selectPlannerFees(
        SearchPlarReq dto
    );

    List<SearchPlarBrmgrRes> selectPlannerBranchManagerFees(
        SearchPlarReq dto
    );

    List<SearchPlarTotalRes> selectPlannerTotalFees(
        SearchPlarReq dto
    );

    List<SearchWmRes> selectWmFees(SearchWmReq dto);

    SearchDsbCnstRes selectdsbCnst(
        SearchDsbCnstReq dto
    );

    int updateDsbIz(WfebOrganizationFeeDvo dvo);

    int insertDsbCnstIz(WfebOrganizationFeeDvo dvo);

    int updateDsbCnstIz(WfebOrganizationFeeDvo dvo);

    HashMap<String, String> selectHmstFeeFormDtl(String perfYm);
}
