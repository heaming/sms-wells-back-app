package com.kyowon.sms.wells.web.competence.education.mapper;

import com.kyowon.sms.wells.web.competence.education.dto.WpsbOnlineLinkCourseInqrDto.SearchReq;
import com.kyowon.sms.wells.web.competence.education.dto.WpsbOnlineLinkCourseInqrDto.SearchRes;
import com.kyowon.sms.wells.web.competence.education.dvo.WpsbOnlineLinkCourseDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WpsbOnlineLinkCourseInqrMapper {

    PagingResult<WpsbOnlineLinkCourseDvo> selectOnlineLinkCourseInqr127Pages(
        WpsbOnlineLinkCourseDvo dvo,
        PageInfo pageInfo
    );

    List<WpsbOnlineLinkCourseDvo> selectOnlineLinkCourseInqr127Pages(
        WpsbOnlineLinkCourseDvo dvo
    );
    PagingResult<WpsbOnlineLinkCourseDvo> selectOnlineLinkCourseInqr128Pages(
        WpsbOnlineLinkCourseDvo dvo,
        PageInfo pageInfo
    );

    List<WpsbOnlineLinkCourseDvo> selectOnlineLinkCourseInqr128Pages(
        WpsbOnlineLinkCourseDvo dvo
    );
    PagingResult<WpsbOnlineLinkCourseDvo> selectOnlineLinkCourseInqr135Pages(
        WpsbOnlineLinkCourseDvo dvo,
        PageInfo pageInfo
    );

    List<WpsbOnlineLinkCourseDvo> selectOnlineLinkCourseInqr135Pages(
        WpsbOnlineLinkCourseDvo dvo
    );
}
