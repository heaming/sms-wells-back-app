package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualVisitPrdInqrDto.SearchCustomerVisitIzRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualVisitPrdInqrDto.SearchManagementCstInqrRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualVisitPrdInqrDto.SearchReq;

@Mapper
public interface WsnbIndividualVisitPrdInqrMapper {

    List<SearchCustomerVisitIzRes> selectCustomerVisitIzs(SearchReq dto);

    List<SearchManagementCstInqrRes> selectManagementCstInqrs(SearchReq dto);
}
