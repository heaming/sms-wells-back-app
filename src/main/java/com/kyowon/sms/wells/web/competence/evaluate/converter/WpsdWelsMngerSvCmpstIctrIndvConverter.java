package com.kyowon.sms.wells.web.competence.evaluate.converter;

import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdWelsMngerSvCmpstIctrIndvDto;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdWelsMngerSvCmpstIctrIndvDvo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WpsdWelsMngerSvCmpstIctrIndvConverter {
    WpsdWelsMngerSvCmpstIctrIndvDvo mapToDvo(WpsdWelsMngerSvCmpstIctrIndvDto.SearchReq req);

    List<WpsdWelsMngerSvCmpstIctrIndvDto.SearchRes> dvoToSearchRes(List<WpsdWelsMngerSvCmpstIctrIndvDvo> dvos);


}
