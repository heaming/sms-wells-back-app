package com.kyowon.sms.wells.web.competence.lecture.service;

import com.kyowon.sms.wells.web.competence.lecture.converter.WpscLectureSpptApplicationConverter;
import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptApplicationDto.*;
import com.kyowon.sms.wells.web.competence.lecture.dvo.WpscLectureSpptApplicationDvo;
import com.kyowon.sms.wells.web.competence.lecture.mapper.WpscLectureSpptApplicationMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WpscLectureSpptApplicationService {

    private final WpscLectureSpptApplicationMapper mapper;
    private final WpscLectureSpptApplicationConverter converter;

    /**
     * 강의지원 신청 - 페이징 리스트 조회
     * @param dto, pageInfo
     * @return PagingResult<SearchRes>
     */
    public PagingResult<SearchRes> getLectureSpptApplicationPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectLectureSpptApplicationPages(dto, pageInfo);
    }

    /**
     * 강의지원 신청 - 리스트 저장
     * @param reqs
     * @return int
     */
    public int saveLectureSpptApplication(List<SaveReq> reqs) {
        int processCount = 0;
        for(SaveReq req : reqs){
            WpscLectureSpptApplicationDvo dvo = converter.mapSaveReq(req);
            if("created".equals(dvo.getRowState())) {
                processCount = mapper.insertLectureSpptApplication(dvo);
            } else if("updated".equals(dvo.getRowState())) {
                processCount = mapper.updateLectureSpptApplication(dvo);
            }
            BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
            processCount += processCount;
        }
        return processCount;
    }

    /**
     * 강의지원 신청 - 빌딩 목록 조회
     * @param req
     * @return List<SearchLevelRes>
     */
    public List<SearchLevelRes> getOrganizationBuildingCode(SearchLevelReq req) {
        return mapper.selectOrganizationBuildingCode(req);
    }

    /**
     * 강의지원 신청 - 신청 목록 삭제
     * @param reqs
     * @return List<SearchLevelRes>
     */
    public int removeLectureSpptApplicationList(List<RemoveReq> reqs){
        int processCount = 0;
        for(RemoveReq req : reqs){
            processCount += mapper.deleteLectureSpptApplication(req);
        }
        return processCount;

    }

}
