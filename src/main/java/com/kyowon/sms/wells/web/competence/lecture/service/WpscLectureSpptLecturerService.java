package com.kyowon.sms.wells.web.competence.lecture.service;

import com.kyowon.sms.wells.web.competence.lecture.converter.WpscLectureSpptLecturerConverter;
import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptLecturerDto.*;
import com.kyowon.sms.wells.web.competence.lecture.dvo.WpscLectureSpptLecturerDvo;
import com.kyowon.sms.wells.web.competence.lecture.mapper.WpscLectureSpptLecturerMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WpscLectureSpptLecturerService {

    private final WpscLectureSpptLecturerMapper mapper;
    private final WpscLectureSpptLecturerConverter converter;

    /**
     * 강의지원 강사관리 - 페이징 리스트 조회
     * @param dto, pageInfo
     * @return PagingResult<SearchRes>
     */
    public PagingResult<SearchRes> getLectureSpptLecturerPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectLectureSpptLecturerPages(dto, pageInfo);
    }


    public List<SearchRes> getLectureSpptLecturer(SearchReq dto) {
        return mapper.selectLectureSpptLecturerPages(dto);
    }

    /**
     * 강의지원 강사관리 - 강사 저장
     * @param reqs
     * @return processCount
     */
    @Transactional
    public int saveLectureSpptLecturer(List<SaveReq> reqs) {
        int processCount = 0;
        for(SaveReq req : reqs){
            WpscLectureSpptLecturerDvo dvo = converter.mapSaveReq(req);
            if("created".equals(dvo.getRowState())) {
                dvo.setLectrSpptLectCd(mapper.selectMaxLecturerCd());
                mapper.insertLecturerMngtHist(dvo);
                processCount = mapper.insertLecturerMngt(dvo);
                BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
            }else if("updated".equals(dvo.getRowState())) {
                mapper.insertLecturerMngtHist(dvo);
                processCount = mapper.updateLecturerMngt(dvo);
                BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
            }
            processCount += processCount;
        }
        return processCount;
    }

}
