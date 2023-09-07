package com.kyowon.sms.wells.web.competence.lecture.mapper;

import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptAplcPtrmDto;
import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptAplcPtrmDto.SearchReq;
import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptAplcPtrmDto.SearchRes;
import com.kyowon.sms.wells.web.competence.lecture.dvo.WpscLectureSpptAplcPtrmDvo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WpscLectureSpptAplcPtrmMapper {
    SearchRes selectLectureSpptAplcPtrm(SearchReq dto);

    int updateLectureSpptAplcPtrm(WpscLectureSpptAplcPtrmDvo dvo);

    int insertLectureSpptApplicationHist(WpscLectureSpptAplcPtrmDvo dvo);

    int insertLectureSpptApplicationDeleteHist(WpscLectureSpptAplcPtrmDto.deleteReq req);

    int updateLectureSpptAplcPtrmStatus(WpscLectureSpptAplcPtrmDto.deleteReq req);
}
