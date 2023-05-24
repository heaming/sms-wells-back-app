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
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpsbZoomMgtService {

    private final WpsbZoomMgtMapper mapper;
    private final WpsbZoomMgtConverter converter;

    public PagingResult<SearchRes> getZoomMgtPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectZoomMgtPages(dto, pageInfo);
    }

    public List<SearchRes> selectZoomMgtPages(SearchReq dto) {
        return mapper.selectZoomMgtPages(dto);
    }

    /**
     * 미팅기준관리등록
     *
     * @param dtos
     * @return processCount
     */
    @Transactional
    public int saveAllZoomMgt(List<WpsbZoomMgtDto.SaveReq> dtos) {
        int processCount = 0;

        int delCnt = mapper.deleteZoomMgt();

        for (WpsbZoomMgtDto.SaveReq dto : dtos) {
            WpsbZoomMgtDvo dvo = converter.mapSaveReq(dto);
            dvo.setDtaDlYn(DeDeductionConst.DELETE_N);
            processCount = mapper.insertZoomMgt(dvo);
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
    public int saveZoomMgt(WpsbZoomMgtDto.SaveReq dto) {
        int processCount = 0;
        WpsbZoomMgtDvo dvo = converter.mapSaveReq(dto);
        dvo.setDtaDlYn(DeDeductionConst.DELETE_N);
        if (dto.rowState().equals(CommConst.ROW_STATE_CREATED)) {
            processCount = mapper.insertZoomMgt(dvo);
        } else {
            processCount = mapper.updateZoomMgt(dvo);
        }
        return processCount;
    }

    public int removeZoomMgt(WpsbZoomMgtDto.RemoveReq dto) {
        int processCount = 0;
        WpsbZoomMgtDvo dvo = converter.mapRemoveReq(dto);
        dvo.setDtaDlYn(DeDeductionConst.DELETE_Y);
        processCount = mapper.removeZoomMgt(dvo);
        return processCount;
    }
}
