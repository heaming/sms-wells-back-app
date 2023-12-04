package com.kyowon.sms.wells.web.fee.control.mapper;

import static com.kyowon.sms.wells.web.fee.control.dto.WfedManagerVisitFeeDto.SearchReq;
import static com.kyowon.sms.wells.web.fee.control.dto.WfedManagerVisitFeeDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.control.dvo.WfedManagerVisitFeeDvo;

@Mapper
public interface WfedManagerVisitFeeMapper {

    int selectManagerVisitFeeAgrgYn(SearchReq dto);

    List<SearchRes> selectManagerVisitFees(
        WfedManagerVisitFeeDvo dvo
    );
}
