package com.kyowon.sms.wells.web.competence.lecture.converter;

import com.kyowon.sms.wells.web.competence.lecture.dvo.WpscLectureSpptAplcPtrmDvo;
import org.mapstruct.Mapper;

import static com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptAplcPtrmDto.SaveReq;

@Mapper(componentModel = "spring")
public interface WpscLectureSpptAplcPtrmConverter {
    WpscLectureSpptAplcPtrmDvo mapSaveReq(SaveReq req);
}
