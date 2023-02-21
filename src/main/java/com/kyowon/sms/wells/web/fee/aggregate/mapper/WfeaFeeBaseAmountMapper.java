package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaFeeBaseAmountDto.*;

@Mapper
public interface WfeaFeeBaseAmountMapper {
    List<SearchRes> selectFeeBaseAmounts(
        SearchReq dto
    );
}
