package com.kyowon.sms.wells.web.competence.lecture.converter;

import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptLectureDto.*;
import com.kyowon.sms.wells.web.competence.lecture.dvo.WpscLectureSpptLectureDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WpscLectureSpptLectureConverter {
    WpscLectureSpptLectureDvo mapSaveReq(SaveReq req);
}
