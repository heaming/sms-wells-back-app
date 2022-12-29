package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAfterServiceAssignPsDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAfterServiceAssignPsDvo;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface WsncAfterServiceAssignPsConverter {
    List<WsncAfterServiceAssignPsDto.SearchRes> mapAllListDvoToListRes(List<WsncAfterServiceAssignPsDvo> dvos);

    WsncAfterServiceAssignPsDto.SearchRes mapDvoToLRes(WsncAfterServiceAssignPsDvo dvo);
}
