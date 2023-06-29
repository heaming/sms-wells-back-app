package com.kyowon.sms.wells.web.contract.changeorder.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCustomerBaseBulkChDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbCustomerBaseBulkChDvo;

@Mapper(componentModel = "spring")
public interface WctbCustomerBaseBulkChConverter {

    WctbCustomerBaseBulkChDvo mapSearchReqToWctbCustomerBaseBulkChDvo(WctbCustomerBaseBulkChDto.SearchReq dto);

    WctbCustomerBaseBulkChDto.SearchCustomerRes mapWctbCustomerBaseBulkChDvoToSearchCustomerRes(
        WctbCustomerBaseBulkChDvo dvo
    );

    WctbCustomerBaseBulkChDto.SearchPartnerRes mapWctbCustomerBaseBulkChDvoToSearchPartnerRes(
        WctbCustomerBaseBulkChDvo dvo
    );

}
