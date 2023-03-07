package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import static com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaFilterMgtFeeDto.SearchReq;
import static com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaFilterMgtFeeDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WfeaFilterMgtFeeMapper {

    List<SearchRes> selectFilterMgtFees(
        SearchReq dto
    );
}
