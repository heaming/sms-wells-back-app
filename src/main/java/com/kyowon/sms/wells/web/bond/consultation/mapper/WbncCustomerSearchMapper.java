package com.kyowon.sms.wells.web.bond.consultation.mapper;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerSearchDto.SearchReq;
import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerSearchDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WbncCustomerSearchMapper {
    List<SearchRes> selectCustomers(SearchReq dto);
}
