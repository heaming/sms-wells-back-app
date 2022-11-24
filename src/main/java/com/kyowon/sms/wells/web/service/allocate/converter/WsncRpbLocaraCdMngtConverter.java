package com.kyowon.sms.wells.web.service.allocate.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraCdMngtDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRpbLocaraCdMngtDvo;

@Mapper(componentModel = "spring")
public interface WsncRpbLocaraCdMngtConverter {

    List<WsncRpbLocaraCdMngtDto.SearchRes> mapCreateResToListDvo(List<WsncRpbLocaraCdMngtDvo> dvoList);
}
