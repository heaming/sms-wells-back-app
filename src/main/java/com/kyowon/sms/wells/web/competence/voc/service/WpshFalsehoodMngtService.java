package com.kyowon.sms.wells.web.competence.voc.service;

import static com.kyowon.sms.wells.web.competence.voc.dto.WpshFalseVisitMngtDto.SearchReq;
import static com.kyowon.sms.wells.web.competence.voc.dto.WpshFalseVisitMngtDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.deduction.zcommon.constant.DeDeductionConst;
import com.kyowon.sms.wells.web.competence.voc.converter.WpshFalsehoodMngtConverter;
import com.kyowon.sms.wells.web.competence.voc.dto.WpshFalseVisitMngtDto;
import com.kyowon.sms.wells.web.competence.voc.dvo.WpshFalseVisitMngtDvo;
import com.kyowon.sms.wells.web.competence.voc.mapper.WpshFalseVisitMngtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpshFalsehoodMngtService {

    private final WpshFalseVisitMngtMapper mapper;
    private final WpshFalsehoodMngtConverter converter;

    private final static int LENGTH_12_INT_RADIX = 11;

    /**
     * wells 허위방문관리 목록조회
     *
     * @param req
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getFalsehoodMgtPages(SearchReq req, PageInfo pageInfo) {
        return mapper.selectFalsehoodMgtPages(req, pageInfo);
    }

    public List<SearchRes> getFalsehoodMgtsForExcelDownload(SearchReq dto) {
        return mapper.selectFalsehoodMgtPages(dto);
    }

    /**
     * wells 허위방문관리 등록, 수정
     *
     * @param dtos
     * @return processCount
     */
    @Transactional
    public int saveFalsevisit(List<WpshFalseVisitMngtDto.SaveReq> dtos) {
        int processCount = 0;
        for (WpshFalseVisitMngtDto.SaveReq dto : dtos) {
            WpshFalseVisitMngtDvo dvo = this.converter.mapSaveReq(dto);
            dvo.setDtaDlYn(DeDeductionConst.DELETE_N);
            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {

                    int result = this.mapper.insertFalsevisit(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    processCount += result;
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    int result = this.mapper.updateFalsevisit(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    processCount += result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }
        return processCount;

    }

    /**
     * wells 허위방문관리 삭제
     *
     * @param dtos
     * @return processCount
     */
    public int removeFalsevisit(List<WpshFalseVisitMngtDto.RemoveReq> dtos) {
        int processCount = 0;
        for (WpshFalseVisitMngtDto.RemoveReq dto : dtos) {
            WpshFalseVisitMngtDvo dvo = this.converter.mapRemoveReq(dto);
            dvo.setDtaDlYn(DeDeductionConst.DELETE_Y);
            processCount = mapper.removeFalsevisit(dvo);
        }
        return processCount;
    }
}
