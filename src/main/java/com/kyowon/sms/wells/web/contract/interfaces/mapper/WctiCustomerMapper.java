package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiCustomerDvo;

@Mapper
public interface WctiCustomerMapper {
    List<WctiCustomerDvo> selectCustomers(SearchReq req);

    List<WctiCustomerDvo> selectProspactCustomers(SearchReq req);
}
