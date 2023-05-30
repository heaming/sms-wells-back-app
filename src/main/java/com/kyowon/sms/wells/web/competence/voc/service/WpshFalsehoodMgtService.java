package com.kyowon.sms.wells.web.competence.voc.service;

import static com.kyowon.sms.wells.web.competence.voc.dto.WpshFalseVisitMgtDto.SearchReq;
import static com.kyowon.sms.wells.web.competence.voc.dto.WpshFalseVisitMgtDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.deduction.zcommon.constant.DeDeductionConst;
import com.kyowon.sms.wells.web.competence.voc.converter.WpshFalsehoodMgtConverter;
import com.kyowon.sms.wells.web.competence.voc.dto.WpshFalseVisitMgtDto;
import com.kyowon.sms.wells.web.competence.voc.dvo.WpshFalseVisitMgtDvo;
import com.kyowon.sms.wells.web.competence.voc.mapper.WpshFalseVisitMgtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpshFalsehoodMgtService {

    private final WpshFalseVisitMgtMapper mapper;
    private final WpshFalsehoodMgtConverter converter;

    private final static int LENGTH_12_INT_RADIX = 11;

    /**
     * wells 허위방문관리 목록조회
     *
     * @param req
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getFalsehoodPages(SearchReq req, PageInfo pageInfo) {
        return mapper.selectFalsehoodPages(req, pageInfo);
    }

    public List<SearchRes> getFalsehoodsForExcelDownload(SearchReq dto) {
        return mapper.selectFalsehoodPages(dto);
    }

    /**
     * wells 허위방문관리 등록, 수정
     *
     * @param dtos
     * @return processCount
     */
    @Transactional
    public int saveFalsevisit(List<WpshFalseVisitMgtDto.SaveReq> dtos) {
        int processCount = 0;
        for (WpshFalseVisitMgtDto.SaveReq dto : dtos) {
            WpshFalseVisitMgtDvo dvo = this.converter.mapSaveReq(dto);
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
    public int removeFalsevisit(List<WpshFalseVisitMgtDto.RemoveReq> dtos) {
        int processCount = 0;
        for (WpshFalseVisitMgtDto.RemoveReq dto : dtos) {
            WpshFalseVisitMgtDvo dvo = this.converter.mapRemoveReq(dto);
            dvo.setDtaDlYn(DeDeductionConst.DELETE_Y);
            processCount = mapper.removeFalsevisit(dvo);
        }
        return processCount;
    }
}
