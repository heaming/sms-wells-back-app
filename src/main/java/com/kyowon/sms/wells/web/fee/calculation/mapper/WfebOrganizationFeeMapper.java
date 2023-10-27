package com.kyowon.sms.wells.web.fee.calculation.mapper;

import java.util.HashMap;
import java.util.List;

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

    List<HashMap<String, Object>> selectManagerFees(
        SearchMngerReq dto, List<HashMap<String, Object>> feeArticles
    );

    List<SearchMngerBfRes> selectManagerBeforeFees(
        SearchMngerReq dto
    );

    List<SearchMngerBrmgrRes> selectManagerBranchManagerFees(
        SearchMngerReq dto
    );

    List<SearchMngerBrmgrBfRes> selectManagerBranchManagerBeforeFees(
        SearchMngerReq dto
    );

    List<HashMap<String, Object>> selectManagerTotalFees(
        SearchMngerReq dto
    );

    List<HashMap<String, Object>> selectManagerTotalBeforeFees(
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

}
