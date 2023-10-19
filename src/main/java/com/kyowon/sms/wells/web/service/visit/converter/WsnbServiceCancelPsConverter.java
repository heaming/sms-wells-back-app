package com.kyowon.sms.wells.web.service.visit.converter;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceCancelPsDvo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.mapstruct.Mapper;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceCancelPsDto.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WsnbServiceCancelPsConverter {
    PagingResult<SearchRes> mapAllDvoToSearchRes(PagingResult<WsnbServiceCancelPsDvo> dvos);
    List<SearchRes> mapAllDvoToSearchRes(List<WsnbServiceCancelPsDvo> dvos);
}
