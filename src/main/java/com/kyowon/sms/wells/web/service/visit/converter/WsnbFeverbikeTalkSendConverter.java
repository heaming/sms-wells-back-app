package com.kyowon.sms.wells.web.service.visit.converter;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbFeverbikeTalkSendDto.*;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbFeverbikeTalkSendDvo;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface WsnbFeverbikeTalkSendConverter {
    List<SearchRes> mapAllDvoToRes(List<WsnbFeverbikeTalkSendDvo> dvos);
}
