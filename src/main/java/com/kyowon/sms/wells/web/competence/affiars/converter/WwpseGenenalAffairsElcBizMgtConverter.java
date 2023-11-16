package com.kyowon.sms.wells.web.competence.affiars.converter;

import com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizMgtDto;
import com.kyowon.sms.wells.web.competence.affiars.dvo.WwpseGenenalAffairsElcBizMgtDvo;
import com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessManagerReportMgtDto;
import com.kyowon.sms.wells.web.competence.report.dvo.WwpscBusinessManagerReportMgtDvo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WwpseGenenalAffairsElcBizMgtConverter {

    List<WwpseGenenalAffairsElcBizMgtDto.PrtnrRes> dvoToPrtnrRes(List<WwpseGenenalAffairsElcBizMgtDvo> dvo);

    PagingResult<WwpseGenenalAffairsElcBizMgtDto.SearchRes> dvoToSearchRes(PagingResult<WwpseGenenalAffairsElcBizMgtDvo> dvo);

}
