package com.kyowon.sms.wells.web.service.visit.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualServicePsDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualServicePsDto.SearchHouseholdRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualServicePsDto.SearchStateRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbIndividualServicePsDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WsnbIndividualServicePsConverter {
    WsnbIndividualServicePsDvo mapWsnbIndividualServicePsDvoToSaveReq(SaveReq dto);
    List<SearchHouseholdRes> mapSearchHouseholdToDvo(List<WsnbIndividualServicePsDvo> dvos);
    PagingResult<SearchStateRes> mapAllSearchStateToDvo(List<WsnbIndividualServicePsDvo> dvos);
    List<SearchStateRes> mapAllSearchStateResToDvo(List<WsnbIndividualServicePsDvo> dvos);
}
