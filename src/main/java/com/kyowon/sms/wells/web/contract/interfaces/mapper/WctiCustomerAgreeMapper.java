package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerAgreeDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerAgreeDto.SearchRes;

@Mapper
public interface WctiCustomerAgreeMapper {
    List<SearchRes> selectCustomerAgree(SearchReq dto);
}
