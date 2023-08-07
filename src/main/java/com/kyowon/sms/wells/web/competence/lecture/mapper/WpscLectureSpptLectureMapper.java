package com.kyowon.sms.wells.web.competence.lecture.mapper;

import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptLectureDto.SearchReq;
import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptLectureDto.SearchRes;
import com.kyowon.sms.wells.web.competence.lecture.dvo.WpscLectureSpptLectureDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WpscLectureSpptLectureMapper {

    PagingResult<SearchRes> selectLectureSpptLecturePages(
        SearchReq dto,
        PageInfo pageInfo
    );
    List<SearchRes> selectLectureSpptLecturePages(SearchReq dto);

    int insertLectrMngtHist(WpscLectureSpptLectureDvo dvo);
    int insertLectrMngt(WpscLectureSpptLectureDvo dvo);

    int updateLectrMngt(WpscLectureSpptLectureDvo dvo);

    String selectMaxLectrCd();
}
