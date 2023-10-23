package com.kyowon.sms.wells.web.fee.control.mapper;

import static com.kyowon.sms.wells.web.fee.control.dto.WfedIndividualFeeHomeMasterMgtDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WfedIndividualFeeHomeMasterMgtMapper {

    FindHmstEntrpRes selectHmstEntrp(
        SearchHmstReq dto
    );

    List<SearchHmstFeeRes> selectHmstFees(
        SearchHmstReq dto
    );

    FindHmstDeductionRes selectHmstDeduction(
        SearchHmstReq dto
    );

    List<SearchHmstControlRes> selectHmstControls(
        SearchHmstReq dto
    );
}
