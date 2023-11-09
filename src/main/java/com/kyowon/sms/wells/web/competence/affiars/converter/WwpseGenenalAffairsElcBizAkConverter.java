package com.kyowon.sms.wells.web.competence.affiars.converter;

import com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkDto;
import com.kyowon.sms.wells.web.competence.affiars.dvo.WwpseGenenalAffairsElcBizAkDvo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WwpseGenenalAffairsElcBizAkConverter {

    List<WwpseGenenalAffairsElcBizAkDto.BaseSearchRes> dvoToBaseSearchRes(List<WwpseGenenalAffairsElcBizAkDvo> dvo);
    PagingResult<WwpseGenenalAffairsElcBizAkDto.SearchRes> dvoToSearchRes(PagingResult<WwpseGenenalAffairsElcBizAkDvo> dvo);
    List<WwpseGenenalAffairsElcBizAkDto.SearchRes> dvoToSearchRes(List<WwpseGenenalAffairsElcBizAkDvo> dvo);
    WwpseGenenalAffairsElcBizAkDto.SearchRes dvoToSearchRes(WwpseGenenalAffairsElcBizAkDvo dvo);
    WwpseGenenalAffairsElcBizAkDvo reqToWwpseGenenalAffairsElcBizAkDvo(WwpseGenenalAffairsElcBizAkDto.SaveReq req);

}
