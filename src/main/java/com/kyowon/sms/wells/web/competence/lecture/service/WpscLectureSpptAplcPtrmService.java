package com.kyowon.sms.wells.web.competence.lecture.service;

import com.kyowon.sms.wells.web.competence.lecture.converter.WpscLectureSpptAplcPtrmConverter;
import com.kyowon.sms.wells.web.competence.lecture.dto.WpscLectureSpptAplcPtrmDto.*;
import com.kyowon.sms.wells.web.competence.lecture.dvo.WpscLectureSpptAplcPtrmDvo;
import com.kyowon.sms.wells.web.competence.lecture.mapper.WpscLectureSpptAplcPtrmMapper;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WpscLectureSpptAplcPtrmService {

    private final WpscLectureSpptAplcPtrmMapper mapper;
    private final WpscLectureSpptAplcPtrmConverter converter;

    public SearchRes getLectureSpptAplcPtrm(SearchReq dto) {
        return mapper.selectLectureSpptAplcPtrm(dto);
    }

    @Transactional
    public int saveLectureSpptAplcPtrm(SaveReq req) {
        int processCount = 0;
        WpscLectureSpptAplcPtrmDvo dvo = converter.mapSaveReq(req);
        mapper.insertLectureSpptApplicationHist(dvo);
        processCount = mapper.updateLectureSpptAplcPtrm(dvo);
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
        return processCount;
    }

    public int editLectureSpptAplcPtrmStatus(deleteReq req) {
        int processCount = 0;
        mapper.insertLectureSpptApplicationDeleteHist(req);
        processCount = mapper.updateLectureSpptAplcPtrmStatus(req);
        return processCount;
    }
}
