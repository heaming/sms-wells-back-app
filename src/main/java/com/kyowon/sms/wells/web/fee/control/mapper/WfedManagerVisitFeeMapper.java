package com.kyowon.sms.wells.web.fee.control.mapper;

import static com.kyowon.sms.wells.web.fee.control.dto.WfedManagerVisitFeeDto.SearchReq;
import static com.kyowon.sms.wells.web.fee.control.dto.WfedManagerVisitFeeDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WfedManagerVisitFeeMapper {
    List<SearchRes> selectManagerVisitFees(
        SearchReq dto
    );
}
