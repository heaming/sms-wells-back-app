package com.kyowon.sms.wells.web.competence.education.mapper;

import com.kyowon.sms.wells.web.competence.education.dto.WpsbOnlineLinkCourseInqrDto.SearchReq;
import com.kyowon.sms.wells.web.competence.education.dto.WpsbOnlineLinkCourseInqrDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WpsbOnlineLinkCourseInqrMapper {

    PagingResult<SearchRes> selectOnlineLinkCourseInqrPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectOnlineLinkCourseInqrPages(
        SearchReq dto
    );
}
