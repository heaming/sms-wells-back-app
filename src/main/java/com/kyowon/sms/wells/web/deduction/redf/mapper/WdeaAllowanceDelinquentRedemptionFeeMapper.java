package com.kyowon.sms.wells.web.deduction.redf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceDelinquentRedemptionFeeDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WdeaAllowanceDelinquentRedemptionFeeMapper {
    /*수당 되물림 현황 조회*/
    PagingResult<WdeaAllowanceDelinquentRedemptionFeeDto.SearchRes> selectAllowanceDelinquentRedemptionFees(
        WdeaAllowanceDelinquentRedemptionFeeDto.SearchReq dto, PageInfo pageInfo
    );

    List<WdeaAllowanceDelinquentRedemptionFeeDto.SearchRes> selectAllowanceDelinquentRedemptionFees(
        WdeaAllowanceDelinquentRedemptionFeeDto.SearchReq dto
    );

}
