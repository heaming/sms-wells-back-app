package com.kyowon.sms.wells.web.competence.educations.service;

import com.kyowon.sms.wells.web.competence.educations.converter.WpsbOnlineLinkCourseInqrConverter;
import com.kyowon.sms.wells.web.competence.educations.dto.WpsbOnlineLinkCourseInqrDto.SearchEducRes;
import com.kyowon.sms.wells.web.competence.educations.dto.WpsbOnlineLinkCourseInqrDto.SearchReq;
import com.kyowon.sms.wells.web.competence.educations.dto.WpsbOnlineLinkCourseInqrDto.SearchRes;
import com.kyowon.sms.wells.web.competence.educations.dvo.WpsbOnlineLinkCourseDvo;
import com.kyowon.sms.wells.web.competence.educations.mapper.WpsbOnlineLinkCourseInqrMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WpsbOnlineLinkCourseInqrService {

    private final WpsbOnlineLinkCourseInqrMapper mapper;
    private final WpsbOnlineLinkCourseInqrConverter converter;

    public PagingResult<SearchRes> getOnlineLinkCourseInqrPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<SearchRes> res = new PagingResult<>();
        WpsbOnlineLinkCourseDvo dvo = converter.mapToDvo(dto);
        List<WpsbOnlineLinkCourseDvo> list = new ArrayList<>();
        if ("127".equals(dvo.getEducCrseNo()) || "15".equals(dvo.getEducCrseNo())) {
            list = mapper.selectOnlineLinkCourseInqr127Pages(dvo, pageInfo);
        } else if ("126".equals(dvo.getEducCrseNo())) {
            list = mapper.selectOnlineLinkCourseInqr126Pages(dvo, pageInfo);
        } else if ("135".equals(dvo.getEducCrseNo()) || "17".equals(dvo.getEducCrseNo())) {
            list = mapper.selectOnlineLinkCourseInqr135Pages(dvo, pageInfo);
        }
        res.setList(converter.dvoToSearchRes(list));
        res.setPageInfo(pageInfo);
        return res;
    }

    public List<SearchRes> getOnlineLinkCourseInqrsForExcelDownload(SearchReq dto) {
        WpsbOnlineLinkCourseDvo dvo = converter.mapToDvo(dto);
        List<WpsbOnlineLinkCourseDvo> list = new ArrayList<>();
        if ("127".equals(dvo.getEducCrseNo()) || "15".equals(dvo.getEducCrseNo())) {
            list = mapper.selectOnlineLinkCourseInqr127Pages(dvo);
        } else if ("126".equals(dvo.getEducCrseNo())) {
            list = mapper.selectOnlineLinkCourseInqr126Pages(dvo);
        } else if ("135".equals(dvo.getEducCrseNo()) || "17".equals(dvo.getEducCrseNo())) {
            list = mapper.selectOnlineLinkCourseInqr135Pages(dvo);
        }
        return converter.dvoToSearchRes(list);
    }

    public List<SearchEducRes> getOnlineEducationCourseList(SearchReq req) {
        return mapper.selectOnlineEducationCourseList(req);
    }
}
