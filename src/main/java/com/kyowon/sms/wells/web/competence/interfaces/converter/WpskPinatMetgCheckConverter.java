package com.kyowon.sms.wells.web.competence.interfaces.converter;

import com.kyowon.sms.wells.web.competence.interfaces.dvo.WpskPinatMetgCheckDvo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.kyowon.sms.wells.web.competence.interfaces.dto.WpskPinatMetgCheckDto.CreateReq;
import static com.kyowon.sms.wells.web.competence.interfaces.dto.WpskPinatMetgCheckDto.CreateRes;
@Mapper(componentModel = "spring")
public interface WpskPinatMetgCheckConverter {
    WpskPinatMetgCheckDvo mapToDvo(CreateReq req);

    CreateRes dvoToCreateRes(WpskPinatMetgCheckDvo res);
}
