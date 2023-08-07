package com.kyowon.sms.wells.web.competence.lecture.service;

import com.kyowon.sms.wells.web.competence.lecture.converter.WpscLectureSpptLectureConverter;
import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptLectureDto.*;
import com.kyowon.sms.wells.web.competence.lecture.dvo.WpscLectureSpptLectureDvo;
import com.kyowon.sms.wells.web.competence.lecture.mapper.WpscLectureSpptLectureMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WpscLectureSpptLectureService {

    private final WpscLectureSpptLectureMapper mapper;
    private final WpscLectureSpptLectureConverter converter;

    /**
     * 강의지원 강의관리 - 페이징 리스트 조회
     * @param dto, pageInfo
     * @return PagingResult<SearchRes>
     */
    public PagingResult<SearchRes> getLectureSpptLecturePages(SearchReq dto, PageInfo pageInfo) {

        return mapper.selectLectureSpptLecturePages(dto, pageInfo);
    }

    public List<SearchRes> getLectureSpptLecture(SearchReq dto) {
        return mapper.selectLectureSpptLecturePages(dto);
    }

    /**
     * 강의지원 강의관리 - 강사 저장
     * @param reqs
     * @return processCount
     */
    @Transactional
    public int saveLectureSpptLecture(List<SaveReq> reqs) {
        int processCount = 0;
        for(SaveReq req : reqs){
            WpscLectureSpptLectureDvo dvo = converter.mapSaveReq(req);
            if("created".equals(dvo.getRowState())) {
                dvo.setLectrSpptLectrCd(mapper.selectMaxLectrCd());
                mapper.insertLectrMngtHist(dvo);
                processCount = mapper.insertLectrMngt(dvo);
                BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
            }else if("updated".equals(dvo.getRowState())) {
                mapper.insertLectrMngtHist(dvo);
                processCount = mapper.updateLectrMngt(dvo);
                BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
            }
            processCount += processCount;
        }
        return processCount;
    }


}
