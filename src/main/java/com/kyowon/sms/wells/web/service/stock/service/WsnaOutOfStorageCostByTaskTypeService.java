package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageCostByTaskTypeDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageCostByTaskTypeDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaOutOfStorageCostByTaskTypeMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 *  K-W-SV-U-0254M01 업무유형별 자재출고금액
 * </pre>
 *
 * @author heymi.cho
 * @since 2023.07.26
 */
@Service
@RequiredArgsConstructor
public class WsnaOutOfStorageCostByTaskTypeService {

    private final WsnaOutOfStorageCostByTaskTypeMapper mapper;

    /**
     * 업무유형별 자재출고금액 - 조회
     *
     * @param dto : { stOstrDt : 검색시작일, edOstrDt : 검색종료일, cstDvCd : 고객구분, stocTypeCd: 재고유형, taskTypeCd : 업무유형, dispTypeCd : 조회기준, itmKndCd : 품목구분, itmPdCd : 품목코드, pdGdCd : 등급코드, useSel : 사용유무  }
     * @return 조회결과
     */
    public PagingResult<SearchRes> getOutOfStorageCostByTaskType(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectOutOfStorageCostByTaskType(dto, pageInfo);
    }

    /**
     * 업무유형별 자재출고금액 - 조회
     *
     * @param dto : { stOstrDt : 검색시작일, edOstrDt : 검색종료일, cstDvCd : 고객구분, stocTypeCd: 재고유형, taskTypeCd : 업무유형, dispTypeCd : 조회기준, itmKndCd : 품목구분, itmPdCd : 품목코드, pdGdCd : 등급코드, useSel : 사용유무  }
     * @return 조회결과
     */
    public List<SearchRes> getOutOfStorageCostByTaskType(SearchReq dto) {
        return this.mapper.selectOutOfStorageCostByTaskType(dto);
    }

}
