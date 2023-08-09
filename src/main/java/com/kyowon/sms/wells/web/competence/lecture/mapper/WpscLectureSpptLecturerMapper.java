package com.kyowon.sms.wells.web.competence.lecture.mapper;

import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptLecturerDto.SearchReq;
import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptLecturerDto.SearchRes;
import com.kyowon.sms.wells.web.competence.lecture.dvo.WpscLectureSpptLecturerDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WpscLectureSpptLecturerMapper {

    PagingResult<SearchRes> selectLectureSpptLecturerPages(
        SearchReq dto,
        PageInfo pageInfo
    );
    List<SearchRes> selectLectureSpptLecturerPages(SearchReq dto);

    int insertLecturerMngtHist(WpscLectureSpptLecturerDvo dvo);
    int insertLecturerMngt(WpscLectureSpptLecturerDvo dvo);

    int updateLecturerMngt(WpscLectureSpptLecturerDvo dvo);

    String selectMaxLecturerCd();
}
