package com.kyowon.sms.wells.web.competence.educations.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.competence.educations.dvo.WpsbOnlineLinkCourseDvo;
import com.kyowon.sms.wells.web.competence.educations.dto.WpsbOnlineLinkCourseInqrDto.*;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WpsbOnlineLinkCourseInqrMapper {

    PagingResult<WpsbOnlineLinkCourseDvo> selectOnlineLinkCourseInqr127Pages(
        WpsbOnlineLinkCourseDvo dvo,
        PageInfo pageInfo
    );

    List<WpsbOnlineLinkCourseDvo> selectOnlineLinkCourseInqr127Pages(
        WpsbOnlineLinkCourseDvo dvo
    );

    PagingResult<WpsbOnlineLinkCourseDvo> selectOnlineLinkCourseInqr126Pages(
        WpsbOnlineLinkCourseDvo dvo,
        PageInfo pageInfo
    );

    List<WpsbOnlineLinkCourseDvo> selectOnlineLinkCourseInqr126Pages(
        WpsbOnlineLinkCourseDvo dvo
    );

    PagingResult<WpsbOnlineLinkCourseDvo> selectOnlineLinkCourseInqr135Pages(
        WpsbOnlineLinkCourseDvo dvo,
        PageInfo pageInfo
    );

    List<WpsbOnlineLinkCourseDvo> selectOnlineLinkCourseInqr135Pages(
        WpsbOnlineLinkCourseDvo dvo
    );

    List<SearchEducRes> selectOnlineEducationCourseList(SearchReq req);
}
