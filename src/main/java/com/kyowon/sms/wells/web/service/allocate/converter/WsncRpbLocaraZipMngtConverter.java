package com.kyowon.sms.wells.web.service.allocate.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraZipMngtDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRpbLocaraZipMngtDvo;

@Mapper(componentModel = "spring")
public interface WsncRpbLocaraZipMngtConverter {
    List<WsncRpbLocaraZipMngtDto.SearchRes> mapCreateResToListDvo(List<WsncRpbLocaraZipMngtDvo> dvo);

}
