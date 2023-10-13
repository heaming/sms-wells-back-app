package com.kyowon.sms.wells.web.competence.evaluate.converter;

import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdWelsMngerSvCmpstIctrDto;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdWelsMngerSvCmpstIctrDvo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WpsdWelsMngerSvCmpstIctrConverter {
    WpsdWelsMngerSvCmpstIctrDvo mapToDvo(WpsdWelsMngerSvCmpstIctrDto.SearchReq req);

    List<WpsdWelsMngerSvCmpstIctrDto.SearchRes> dvoToSearchRes(List<WpsdWelsMngerSvCmpstIctrDvo> dvos);


}
