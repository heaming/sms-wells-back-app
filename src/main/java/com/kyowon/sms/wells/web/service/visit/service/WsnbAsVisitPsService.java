package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbAsVisitPsDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbAsVisitPsDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbAsVisitPsMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0031M01 상품별 서비스 처리 집계 현황
 * </pre>
 *
 * @author hyewon.kim
 * @since 2022.12.30
 */
@Service
@RequiredArgsConstructor
public class WsnbAsVisitPsService {

    private final WsnbAsVisitPsMapper mapper;

    /**
     * 상품별 서비스 처리 집계 현황 목록 조회
     * @param dto 조회조건
     * @param pageInfo 페이지정보
     * @return 상품별 서비스 처리 집계 현황 목록
     */
    public PagingResult<SearchRes> getProductServices(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectProductServices(dto, pageInfo);
    }

    /**
     * 상품별 서비스 처리 집계 현황 엑셀 다운로드
     * @param dto 조회조건
     * @return 상품별 서비스 처리 집계 현황 목록
     */
    public List<SearchRes> getProductServicesForExcelDownload(SearchReq dto) {
        return this.mapper.selectProductServices(dto);
    }

}
