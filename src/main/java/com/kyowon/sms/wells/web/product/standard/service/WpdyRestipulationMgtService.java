package com.kyowon.sms.wells.web.product.standard.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.product.standard.converter.WpdyRestipulationMgtConverter;
import com.kyowon.sms.wells.web.product.standard.dto.WpdyRestipulationMgtDto.*;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyRestipulationDvo;
import com.kyowon.sms.wells.web.product.standard.mapper.WpdyRestipulationMgtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 상품 >> 재약정 기본정보 관리 Service
 * </pre>
 *
 * @author  junho.bae
 * @since 2023-07-01
 */
@Service
@RequiredArgsConstructor
public class WpdyRestipulationMgtService {

    private final WpdyRestipulationMgtMapper mapper;
    private final WpdyRestipulationMgtConverter converter;

    /**
     * 재약정 목록 조회
     * @param dto 검색조건
     * @return 재약정 목록
     */
    public PagingResult<SearchRes> getRestipulationPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectRestipulationPages(dto, pageInfo);
    }

    /**
     * 재약정 목록 엑셀다운로드
     * @param dto 검색조건
     * @return 재약정 목록
     */
    public List<SearchRes> getRestipulationsForExcelDownload(SearchReq dto) {
        return mapper.selectRestipulationPages(dto);
    }

    /**
     * 재약정 입력시 데이터 중복검사
     * @param dtos 재약정 정보
     * @return 중복 여부 관련 정보
     */
    public DuplicationRes checkDuplicationByPk(List<SaveReq> dtos) {
        DuplicationRes duplicationRes = new DuplicationRes(null, "N");
        for (SaveReq dto : dtos) {
            WpdyRestipulationDvo vo = converter.mapSaveReqToWpdyRestipulationDvo(dto);
            if (mapper.selectDuplicationByPk(vo) > 0) {
                return new DuplicationRes(vo.getPdNm(), "Y");
            }
        }
        return duplicationRes;
    }

    /**
     * 재약정 목록 저장(추가/수정)
     * @param dtos 재약정 정보
     * @return 성공여부
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
     * @param dtos  재약정 PK 배열
     * @return 성공여부
     */
    @Transactional
    public int removeRestipulations(List<RemoveReq> dtos) {
        int processCount = 0;
        for (RemoveReq dto : dtos) {
            WpdyRestipulationDvo vo = converter.mapDelReqToWpdyRestipulationDvo(dto);
            int result = mapper.deleteRestipulation(vo);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }
        return processCount;
    }
}
