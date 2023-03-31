package com.kyowon.sms.wells.web.withdrawal.interfaces.converter;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbRentalMembershipPrepaymentInterfaceDto.SearchExpectedInfoRes;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdbRentalMembershipPrepaymentInterfaceDvo;
import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WwdbRentalMembershipPrepaymentConverter {


    List<SearchExpectedInfoRes> mapWwdbRentalMembershipPrepaymentDvoToSearchRes(List<WwdbRentalMembershipPrepaymentInterfaceDvo> dvo);
}
