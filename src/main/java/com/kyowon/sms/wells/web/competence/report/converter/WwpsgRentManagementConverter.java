package com.kyowon.sms.wells.web.competence.report.converter;

import com.kyowon.sms.wells.web.competence.report.dto.WwpsgRentManagementDto;
import com.kyowon.sms.wells.web.competence.report.dvo.WwpsgRentManagementDvo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WwpsgRentManagementConverter {

    List<WwpsgRentManagementDto.BaseSearchRes> dvoToBaseSearchRes(List<WwpsgRentManagementDvo> dvo);
    PagingResult<WwpsgRentManagementDto.SearchRes> dvoToSearchRes(PagingResult<WwpsgRentManagementDvo> dvo);
    List<WwpsgRentManagementDto.SearchRes> dvoToSearchRes(List<WwpsgRentManagementDvo> dvo);
    WwpsgRentManagementDto.SearchRes dvoToSearchRes(WwpsgRentManagementDvo dvo);
    WwpsgRentManagementDto.PopupSearchRes dvoToPopupSearchRes(WwpsgRentManagementDvo dvo);
    WwpsgRentManagementDvo reqToWwpsgRentManagementDvo(WwpsgRentManagementDto.SaveReq req);

}
