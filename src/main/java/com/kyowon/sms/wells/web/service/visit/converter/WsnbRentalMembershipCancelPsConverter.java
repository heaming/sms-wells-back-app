package com.kyowon.sms.wells.web.service.visit.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbRentalMembershipCancelPsDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbRentalMembershipCancelPsDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WsnbRentalMembershipCancelPsConverter {

    PagingResult<SearchRes> mapDvoToSearchResPages(List<WsnbRentalMembershipCancelPsDvo> dvos);

    List<SearchRes> mapDvoToSearchRes(List<WsnbRentalMembershipCancelPsDvo> dvos);
}
