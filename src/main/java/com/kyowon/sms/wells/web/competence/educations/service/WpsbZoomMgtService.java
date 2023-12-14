package com.kyowon.sms.wells.web.competence.educations.service;

import com.kyowon.sms.common.web.deduction.zcommon.constant.DeDeductionConst;
import com.kyowon.sms.wells.web.competence.educations.converter.WpsbZoomMngtConverter;
import com.kyowon.sms.wells.web.competence.educations.dto.WpsbZoomMgtDto;
import com.kyowon.sms.wells.web.competence.educations.dto.WpsbZoomMgtDto.SaveReq;
import com.kyowon.sms.wells.web.competence.educations.dto.WpsbZoomMgtDto.SearchReq;
import com.kyowon.sms.wells.web.competence.educations.dto.WpsbZoomMgtDto.SearchRes;
import com.kyowon.sms.wells.web.competence.educations.dvo.WpsbZoomMgtDvo;
import com.kyowon.sms.wells.web.competence.educations.mapper.WpsbZoomMgtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WpsbZoomMgtService {

    private final WpsbZoomMgtMapper mapper;
    private final WpsbZoomMngtConverter converter;

    public List<SearchRes> getZooms(SearchReq dto) {
        return mapper.getZooms(dto);
    }

    /**
     * Zoom 전체등록,수정
     *
     * @param reqs
     * @return processCount
     */
    @Transactional
    public int saveZoomList(List<SaveReq> reqs) {
        int processCount = 0;

        for (SaveReq req : reqs) {
            WpsbZoomMgtDvo dvo = converter.mapSaveReq(req);
            if(StringUtils.isBlank(dvo.getSvEducMnalId())){
                processCount = mapper.insertZoom(dvo);
            }else{
                processCount = mapper.updateZoom(dvo);
            }
        }
        return processCount;
    }

    /**
     * Zoom 등록,수정
     *
     * @param dto
     * @return processCount
     */
    @Transactional
    public int saveZoom(WpsbZoomMgtDto.SaveReq dto) {
        int processCount = 0;
        WpsbZoomMgtDvo dvo = converter.mapSaveReq(dto);
        dvo.setDtaDlYn(DeDeductionConst.DELETE_N);
        if (dto.rowState().equals(CommConst.ROW_STATE_CREATED)) {
            processCount = mapper.insertZoom(dvo);
        } else {
            processCount = mapper.updateZoom(dvo);
        }
        return processCount;
    }

    public int removeZoom(WpsbZoomMgtDto.RemoveReq dto) {
        int processCount = 0;
        WpsbZoomMgtDvo dvo = converter.mapRemoveReq(dto);
        processCount = mapper.removeZoom(dvo);
        return processCount;
    }
}
