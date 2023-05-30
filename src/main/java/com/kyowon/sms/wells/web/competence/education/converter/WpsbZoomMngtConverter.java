package com.kyowon.sms.wells.web.competence.education.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMngtDto;
import com.kyowon.sms.wells.web.competence.education.dvo.WpsbZoomMngtDvo;

@Mapper(componentModel = "spring")
public interface WpsbZoomMngtConverter {
    WpsbZoomMngtDvo mapSaveReq(WpsbZoomMngtDto.SaveReq dto);

    WpsbZoomMngtDvo mapRemoveReq(WpsbZoomMngtDto.RemoveReq dto);
}
