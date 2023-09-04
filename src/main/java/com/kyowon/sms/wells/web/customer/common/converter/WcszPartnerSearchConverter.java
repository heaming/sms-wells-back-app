package com.kyowon.sms.wells.web.customer.common.converter;

import com.kyowon.sms.wells.web.customer.common.dto.WcszPartnerSearchDto;
import com.kyowon.sms.wells.web.customer.common.dvo.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WcszPartnerSearchConverter {
    WcszPartnerSearchDto.SearchPartnerByPkRes mapWcszPartnerDvoToSearchPartnerByPkRes(WcszPartnerDvo dvo);
}
