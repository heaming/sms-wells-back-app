package com.kyowon.sms.wells.web.competence.report.converter;

import com.kyowon.sms.wells.web.competence.report.dvo.WwpscBusinessManagerReportMgtDvo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessManagerReportMgtDto.*;


@Mapper(componentModel = "spring")
public interface WwpsgBusinessManagerReportMgtConverter {

    List<PrtnrRes> dvoToPrtnrRes(List<WwpscBusinessManagerReportMgtDvo> dvo);

    PagingResult<SearchRes> dvoToSearchRes(PagingResult<WwpscBusinessManagerReportMgtDvo> dvo);

}
