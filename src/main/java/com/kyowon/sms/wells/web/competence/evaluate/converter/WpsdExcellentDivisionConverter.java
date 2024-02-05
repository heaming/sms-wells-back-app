package com.kyowon.sms.wells.web.competence.evaluate.converter;

import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExcellentDivisionDto.*;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdExcellentDivisionDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WpsdExcellentDivisionConverter {
    WpsdExcellentDivisionDvo mapToDvo(SaveContestPartnerReq req);
    WpsdExcellentDivisionDvo mapToDvo(SaveReq req);

}
