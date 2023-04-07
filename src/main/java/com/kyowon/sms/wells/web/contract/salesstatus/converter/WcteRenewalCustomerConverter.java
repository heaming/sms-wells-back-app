package com.kyowon.sms.wells.web.contract.salesstatus.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteRenewalCustomerDto.SearchRes;
import com.kyowon.sms.wells.web.contract.salesstatus.dvo.WcteRenewalCustomerDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WcteRenewalCustomerConverter {
    PagingResult<SearchRes> mapWcteRenewalCustomerDvoToSearchRes(PagingResult<WcteRenewalCustomerDvo> dvo);

    List<SearchRes> mapWcteRenewalCustomerDvoToSearchRes(List<WcteRenewalCustomerDvo> dvo);

}
