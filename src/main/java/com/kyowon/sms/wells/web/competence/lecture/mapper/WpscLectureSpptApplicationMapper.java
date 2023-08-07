package com.kyowon.sms.wells.web.competence.lecture.mapper;

import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptApplicationDto.SearchReq;
import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptApplicationDto.SearchRes;
import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptApplicationDto.RemoveReq;
import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptApplicationDto.SearchLevelReq;
import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptApplicationDto.SearchLevelRes;
import com.kyowon.sms.wells.web.competence.lecture.dvo.WpscLectureSpptApplicationDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WpscLectureSpptApplicationMapper {

    PagingResult<SearchRes> selectLectureSpptApplicationPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchLevelRes> selectOrganizationBuildingCode(SearchLevelReq req);

    int insertLectureSpptApplication(WpscLectureSpptApplicationDvo dvo);

    int updateLectureSpptApplication(WpscLectureSpptApplicationDvo dvo);

    int deleteLectureSpptApplication(RemoveReq req);
}
