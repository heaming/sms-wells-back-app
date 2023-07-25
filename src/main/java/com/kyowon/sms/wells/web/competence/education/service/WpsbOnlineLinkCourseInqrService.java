package com.kyowon.sms.wells.web.competence.education.service;

import com.kyowon.sms.wells.web.competence.education.converter.WpsbOnlineLinkCourseInqrConverter;
import com.kyowon.sms.wells.web.competence.education.dto.WpsbOnlineLinkCourseInqrDto.SearchReq;
import com.kyowon.sms.wells.web.competence.education.dto.WpsbOnlineLinkCourseInqrDto.SearchRes;
import com.kyowon.sms.wells.web.competence.education.mapper.WpsbOnlineLinkCourseInqrMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WpsbOnlineLinkCourseInqrService {

    private final WpsbOnlineLinkCourseInqrMapper mapper;
    private final WpsbOnlineLinkCourseInqrConverter converter;

    public PagingResult<SearchRes> getOnlineLinkCourseInqrPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<SearchRes> res = new PagingResult<>();
        List<SearchRes> list =  mapper.selectOnlineLinkCourseInqrPages(dto, pageInfo);
        res.setList(list);
        res.setPageInfo(pageInfo);
        return mapper.selectOnlineLinkCourseInqrPages(dto, pageInfo);
    }

    public List<SearchRes> getOnlineLinkCourseInqrsForExcelDownload(SearchReq dto) {
        return mapper.selectOnlineLinkCourseInqrPages(dto);
    }
}
