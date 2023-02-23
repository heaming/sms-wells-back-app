package com.kyowon.sms.wells.web.fee.calculation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebOgFeeDto.*;

@Mapper
public interface WfebOgFeeMapper {

    List<SearchHmstRes> selectHomeMasterFees(
        SearchHmstReq dto
    );

    List<SearchHmstBrmgrRes> selectHomeMasterBranchManagerFees(
        SearchHmstReq dto
    );

    List<SearchMngerRes> selectManagerFees(
        SearchMngerReq dto
    );

    List<SearchPlarRes> selectPlannerFees(
        SearchPlarReq dto
    );
}
