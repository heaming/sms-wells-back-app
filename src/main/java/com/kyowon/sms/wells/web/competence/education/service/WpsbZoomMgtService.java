package com.kyowon.sms.wells.web.competence.education.service;

import static com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMgtDto.SearchReq;
import static com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMgtDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.deduction.zcommon.constant.DeDeductionConst;
import com.kyowon.sms.wells.web.competence.education.converter.WpsbZoomMgtConverter;
import com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMgtDto;
import com.kyowon.sms.wells.web.competence.education.dvo.WpsbZoomMgtDvo;
import com.kyowon.sms.wells.web.competence.education.mapper.WpsbZoomMgtMapper;
import com.sds.sflex.system.config.constant.CommConst;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpsbZoomMgtService {

    private final WpsbZoomMgtMapper mapper;
    private final WpsbZoomMgtConverter converter;

    public List<SearchRes> selectZooms(SearchReq dto) {
        return mapper.selectZooms(dto);
    }

    /**
     * 미팅기준관리등록
     *
     * @param dto
     * @return processCount
     */
    @Transactional
    public int saveAllZoom(WpsbZoomMgtDto.EditReq dto) {
        int processCount = 0;
        int delCnt = mapper.deleteZoom(dto.hgrSvEducMnalId());
        for (WpsbZoomMgtDto.SaveReq zoomDto : dto.treeList()) {
            boolean inSave = false;
            if (dto.hgrSvEducMnalId().equals("WELS0000000000")) {
                inSave = true;
            } else {
                if (zoomDto.hgrSvEducMnalId().contains(dto.hgrSvEducMnalId())) {
                    inSave = true;
                }
            }
            if (inSave) {
                WpsbZoomMgtDvo dvo = converter.mapSaveReq(zoomDto);
                dvo.setDtaDlYn(DeDeductionConst.DELETE_N);
                processCount = mapper.insertZoom(dvo);
            }

        }
        return processCount;
    }

    /**
    * 미팅기준관리등록,수정
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
        dvo.setDtaDlYn(DeDeductionConst.DELETE_Y);
        processCount = mapper.removeZoom(dvo);
        return processCount;
    }
}
