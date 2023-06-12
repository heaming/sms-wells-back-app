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
 * @author hyewon.kim 김혜원
 * @since 2022.12.30
 */
@Service
@RequiredArgsConstructor
public class WsnbAsVisitPsService {

    private final WsnbAsVisitPsMapper mapper;

    public PagingResult<SearchRes> getProductServices(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectProductServices(dto, pageInfo);
    }

    public List<SearchRes> getProductServicesForExcelDownload(SearchReq dto) {
        return this.mapper.selectProductServices(dto);
    }

}
