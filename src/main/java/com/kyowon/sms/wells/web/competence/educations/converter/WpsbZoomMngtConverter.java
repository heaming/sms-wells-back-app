package com.kyowon.sms.wells.web.competence.educations.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.competence.educations.dto.WpsbZoomMgtDto;
import com.kyowon.sms.wells.web.competence.educations.dvo.WpsbZoomMgtDvo;

@Mapper(componentModel = "spring")
public interface WpsbZoomMngtConverter {
    WpsbZoomMgtDvo mapSaveReq(WpsbZoomMgtDto.SaveReq dto);

    WpsbZoomMgtDvo mapRemoveReq(WpsbZoomMgtDto.RemoveReq dto);
}
