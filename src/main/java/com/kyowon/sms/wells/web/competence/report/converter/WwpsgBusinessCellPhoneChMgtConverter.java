package com.kyowon.sms.wells.web.competence.report.converter;

import com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessCellPhoneChMgtDto;
import com.kyowon.sms.wells.web.competence.report.dvo.WwpsgBusinessCellPhoneChMgtDvo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WwpsgBusinessCellPhoneChMgtConverter {

    List<WwpsgBusinessCellPhoneChMgtDto.BaseSearchRes> dvoToBaseSearchRes(List<WwpsgBusinessCellPhoneChMgtDvo> dvo);
    PagingResult<WwpsgBusinessCellPhoneChMgtDto.SearchRes> dvoToSearchRes(PagingResult<WwpsgBusinessCellPhoneChMgtDvo> dvo);
    List<WwpsgBusinessCellPhoneChMgtDto.SearchRes> dvoToSearchRes(List<WwpsgBusinessCellPhoneChMgtDvo> dvo);
    WwpsgBusinessCellPhoneChMgtDto.SearchRes dvoToSearchRes(WwpsgBusinessCellPhoneChMgtDvo dvo);

    WwpsgBusinessCellPhoneChMgtDto.SellPrtnrRes dvoToSellPrtnrRes(WwpsgBusinessCellPhoneChMgtDvo dvo);

    WwpsgBusinessCellPhoneChMgtDto.PopupSearchRes dvoToPopupSearchRes(WwpsgBusinessCellPhoneChMgtDvo dvo);

    WwpsgBusinessCellPhoneChMgtDvo reqToWwpsgBusinessCellPhoneChMgtDvo(WwpsgBusinessCellPhoneChMgtDto.SaveReq req);

}
