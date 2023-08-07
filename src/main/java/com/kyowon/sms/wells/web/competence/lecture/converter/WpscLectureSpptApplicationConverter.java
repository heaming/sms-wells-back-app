package com.kyowon.sms.wells.web.competence.lecture.converter;

import com.kyowon.sms.wells.web.competence.lecture.dvo.WpscLectureSpptApplicationDvo;
import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptApplicationDto.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WpscLectureSpptApplicationConverter {

    WpscLectureSpptApplicationDvo mapSaveReq(SaveReq req);
}
