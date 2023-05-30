package com.kyowon.sms.wells.web.competence.education.service;

import static com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMngtDto.SearchReq;
import static com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMngtDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.deduction.zcommon.constant.DeDeductionConst;
import com.kyowon.sms.wells.web.competence.education.converter.WpsbZoomMngtConverter;
import com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMngtDto;
import com.kyowon.sms.wells.web.competence.education.dvo.WpsbZoomMngtDvo;
import com.kyowon.sms.wells.web.competence.education.mapper.WpsbZoomMngtMapper;
import com.sds.sflex.system.config.constant.CommConst;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpsbZoomMngtService {

    private final WpsbZoomMngtMapper mapper;
    private final WpsbZoomMngtConverter converter;

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
    public int saveAllZoom(WpsbZoomMngtDto.EditReq dto) {
        int processCount = 0;
        int delCnt = mapper.deleteZoom(dto.hgrSvEducMnalId());
        for (WpsbZoomMngtDto.SaveReq zoomDto : dto.treeList()) {
            boolean inSave = false;
            if (dto.hgrSvEducMnalId().equals("WELS0000000000")) {
                inSave = true;
            } else {
                if (zoomDto.hgrSvEducMnalId().contains(dto.hgrSvEducMnalId())) {
                    inSave = true;
                }
            }
            if (inSave) {
                WpsbZoomMngtDvo dvo = converter.mapSaveReq(zoomDto);
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
    public int saveZoom(WpsbZoomMngtDto.SaveReq dto) {
        int processCount = 0;
        WpsbZoomMngtDvo dvo = converter.mapSaveReq(dto);
        dvo.setDtaDlYn(DeDeductionConst.DELETE_N);
        if (dto.rowState().equals(CommConst.ROW_STATE_CREATED)) {
            processCount = mapper.insertZoom(dvo);
        } else {
            processCount = mapper.updateZoom(dvo);
        }
        return processCount;
    }

    public int removeZoom(WpsbZoomMngtDto.RemoveReq dto) {
        int processCount = 0;
        WpsbZoomMngtDvo dvo = converter.mapRemoveReq(dto);
        dvo.setDtaDlYn(DeDeductionConst.DELETE_Y);
        processCount = mapper.removeZoom(dvo);
        return processCount;
    }
}
