package com.kyowon.sms.wells.web.competence.lecture.mapper;

import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptLecturerDto.SearchReq;
import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptLecturerDto.SearchRes;
import com.kyowon.sms.wells.web.competence.lecture.dvo.WpscLectureSpptLecturerDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WpscLectureSpptLecturerMapper {

    PagingResult<SearchRes> selectLectureSpptLecturerPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    int insertLecterMngtHist(WpscLectureSpptLecturerDvo dvo);
    int insertLecterMngt(WpscLectureSpptLecturerDvo dvo);

    int updateLecterMngt(WpscLectureSpptLecturerDvo dvo);

    String selectMaxLecterCd();
}
