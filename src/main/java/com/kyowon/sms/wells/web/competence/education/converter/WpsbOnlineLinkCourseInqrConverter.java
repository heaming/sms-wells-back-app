package com.kyowon.sms.wells.web.competence.education.converter;

import com.kyowon.sms.wells.web.competence.education.dto.WpsbOnlineLinkCourseInqrDto.SearchReq;
import com.kyowon.sms.wells.web.competence.education.dto.WpsbOnlineLinkCourseInqrDto.SearchRes;
import com.kyowon.sms.wells.web.competence.education.dvo.WpsbOnlineLinkCourseDvo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WpsbOnlineLinkCourseInqrConverter {
    WpsbOnlineLinkCourseDvo mapToDvo(SearchReq req);

    List<SearchRes> dvoToSearchRes(List<WpsbOnlineLinkCourseDvo> dvos);
}
