package com.kyowon.sms.wells.web.competence.lecture.converter;

import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptLecturerDto.*;
import com.kyowon.sms.wells.web.competence.lecture.dvo.WpscLectureSpptLecturerDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WpscLectureSpptLecturerConverter {
    WpscLectureSpptLecturerDvo mapSaveReq(SaveReq req);
}
