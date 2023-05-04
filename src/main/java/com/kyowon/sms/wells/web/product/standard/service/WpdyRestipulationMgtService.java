package com.kyowon.sms.wells.web.product.standard.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.product.standard.converter.WpdyRestipulationMgtConverter;
import com.kyowon.sms.wells.web.product.standard.dto.WpdyRestipulationMgtDto.DelReq;
import com.kyowon.sms.wells.web.product.standard.dto.WpdyRestipulationMgtDto.DuplicationRes;
import com.kyowon.sms.wells.web.product.standard.dto.WpdyRestipulationMgtDto.SaveReq;
import com.kyowon.sms.wells.web.product.standard.dto.WpdyRestipulationMgtDto.SearchReq;
import com.kyowon.sms.wells.web.product.standard.dto.WpdyRestipulationMgtDto.SearchRes;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyRestipulationDvo;
import com.kyowon.sms.wells.web.product.standard.mapper.WpdyRestipulationMgtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpdyRestipulationMgtService {

    private final WpdyRestipulationMgtMapper mapper;
    private final WpdyRestipulationMgtConverter converter;

    /**
     * 재약정 목록 조회
     * @param dto
     * @return
     */
    public PagingResult<SearchRes> getRestipulationPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectRestipulationPages(dto, pageInfo);
    }

    /**
     * 재약정 목록 엑셀다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getRestipulationsForExcelDownload(SearchReq dto) {
        return mapper.selectRestipulationPages(dto);
    }

    /**
     * 재약정 입력시 데이터 중복검사
     * @param dtos
     * @return
     */
    public DuplicationRes checkDuplicationByPk(List<SaveReq> dtos) {
        DuplicationRes duplicationRes = new DuplicationRes(null, "N");
        for (SaveReq dto : dtos) {
            WpdyRestipulationDvo vo = converter.mapSaveReqToWpdyRestipulationDvo(dto);
            if (mapper.selectDuplicationByPk(vo) == 1) {
                return new DuplicationRes(vo.getPdNm(), "Y");
            }
        }
        return duplicationRes;
    }

    /**
     * 재약정 목록 저장(추가/수정)
     * @param dtos
     * @return
     */
    @Transactional
    public int saveRestipulations(List<SaveReq> dtos) {
        int processCount = 0;
        for (SaveReq dto : dtos) {
            WpdyRestipulationDvo vo = converter.mapSaveReqToWpdyRestipulationDvo(dto);
            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    processCount += mapper.insertRestipulation(vo);
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    int result = mapper.updateRestipulation(vo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    processCount += result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }
        return processCount;
    }

    /**
     * 재약정 삭제
     * @param pkArr
     * @return
     */
    @Transactional
    public int removeRestipulations(List<DelReq> dtos) {
        int processCount = 0;
        for (DelReq dto : dtos) {
            WpdyRestipulationDvo vo = converter.mapDelReqToWpdyRestipulationDvo(dto);
            int result = mapper.deleteRestipulation(vo);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }
        return processCount;
    }
}
