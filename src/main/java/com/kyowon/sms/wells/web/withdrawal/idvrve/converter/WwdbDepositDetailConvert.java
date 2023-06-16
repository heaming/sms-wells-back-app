package com.kyowon.sms.wells.web.withdrawal.idvrve.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositDetailDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbDepositDetailSearchDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WwdbDepositDetailConvert {

    WwdbDepositDetailSearchDvo mapWwdbDepositDetailSearchDvo(SearchReq dto);

}
