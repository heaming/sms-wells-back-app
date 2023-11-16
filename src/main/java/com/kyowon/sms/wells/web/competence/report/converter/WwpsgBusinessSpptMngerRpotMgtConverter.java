package com.kyowon.sms.wells.web.competence.report.converter;

import com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessSpptMngerRpotMgtDto;
import com.kyowon.sms.wells.web.competence.report.dvo.WwpsgBusinessSpptMngerRpotMgtDvo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WwpsgBusinessSpptMngerRpotMgtConverter {

    List<WwpsgBusinessSpptMngerRpotMgtDto.PrtnrRes> dvoToPrtnrRes(List<WwpsgBusinessSpptMngerRpotMgtDvo> dvo);

    PagingResult<WwpsgBusinessSpptMngerRpotMgtDto.SearchRes> dvoToSearchRes(PagingResult<WwpsgBusinessSpptMngerRpotMgtDvo> dvo);

}
