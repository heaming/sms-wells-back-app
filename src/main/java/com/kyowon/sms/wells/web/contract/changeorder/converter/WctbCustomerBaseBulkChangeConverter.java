package com.kyowon.sms.wells.web.contract.changeorder.converter;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCustomerBaseBulkChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbCustomerBaseBulkChangeDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WctbCustomerBaseBulkChangeConverter {

    WctbCustomerBaseBulkChangeDvo mapSearchReqToWctbCustomerBaseBulkChangeDvo(
        WctbCustomerBaseBulkChangeDto.SearchReq dto
    );

    WctbCustomerBaseBulkChangeDto.SearchCustomerRes mapWctbCustomerBaseBulkChangeDvoToSearchCustomerRes(
        WctbCustomerBaseBulkChangeDvo dvo
    );

    WctbCustomerBaseBulkChangeDto.SearchPartnerRes mapWctbCustomerBaseBulkChangeDvoToSearchPartnerRes(
        WctbCustomerBaseBulkChangeDvo dvo
    );

}
