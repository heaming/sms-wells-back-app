package com.kyowon.sms.wells.web.competence.educations.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.competence.educations.dto.WpsbOnlineLinkCourseInqrDto.SearchReq;
import com.kyowon.sms.wells.web.competence.educations.dto.WpsbOnlineLinkCourseInqrDto.SearchRes;
import com.kyowon.sms.wells.web.competence.educations.dvo.WpsbOnlineLinkCourseDvo;

@Mapper(componentModel = "spring")
public interface WpsbOnlineLinkCourseInqrConverter {
    WpsbOnlineLinkCourseDvo mapToDvo(SearchReq req);

    List<SearchRes> dvoToSearchRes(List<WpsbOnlineLinkCourseDvo> dvos);
}
