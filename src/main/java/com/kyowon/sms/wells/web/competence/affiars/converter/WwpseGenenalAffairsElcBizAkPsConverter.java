package com.kyowon.sms.wells.web.competence.affiars.converter;

import com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkPsDto;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkPsDto.*;

import com.kyowon.sms.wells.web.competence.affiars.dvo.WwpseGenenalAffairsElcBizAkPsDvo;

@Mapper(componentModel = "spring")
public interface WwpseGenenalAffairsElcBizAkPsConverter {

    PagingResult<WwpseGenenalAffairsElcBizAkPsDto.SearchRes> dvoToSearchRes(PagingResult<WwpseGenenalAffairsElcBizAkPsDvo> dvo);

}
