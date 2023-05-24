package com.kyowon.sms.wells.web.competence.education.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMgtDto;
import com.kyowon.sms.wells.web.competence.education.dvo.WpsbZoomMgtDvo;

@Mapper(componentModel = "spring")
public interface WpsbZoomMgtConverter {
    WpsbZoomMgtDvo mapSaveReq(WpsbZoomMgtDto.SaveReq dto);

    WpsbZoomMgtDvo mapRemoveReq(WpsbZoomMgtDto.RemoveReq dto);
}
