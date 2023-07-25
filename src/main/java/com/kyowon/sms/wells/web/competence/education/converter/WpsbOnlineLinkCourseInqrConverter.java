package com.kyowon.sms.wells.web.competence.education.converter;

import com.kyowon.sms.wells.web.competence.education.dto.WpsbOnlineLinkCourseInqrDto.*;
import com.kyowon.sms.wells.web.competence.education.dvo.WpsbOnlineLinkCourseDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WpsbOnlineLinkCourseInqrConverter {
    WpsbOnlineLinkCourseDvo mapToDvo(SearchReq req);
}
