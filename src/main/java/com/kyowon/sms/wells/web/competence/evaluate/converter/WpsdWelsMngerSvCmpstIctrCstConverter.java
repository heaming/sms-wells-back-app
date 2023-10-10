package com.kyowon.sms.wells.web.competence.evaluate.converter;

import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdWelsMngerSvCmpstIctrCstDto.SearchRes;
import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdWelsMngerSvCmpstIctrCstDto.SearchReq;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdWelsMngerSvCmpstIctrCstDvo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WpsdWelsMngerSvCmpstIctrCstConverter {
    WpsdWelsMngerSvCmpstIctrCstDvo mapToDvo(SearchReq req);

    List<SearchRes> dvoToSearchRes(List<WpsdWelsMngerSvCmpstIctrCstDvo> dvos);

}
