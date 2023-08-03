package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbCustomerPsByProductDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbCustomerPsByProductDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbCustomerPsByProductMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * <pre>
 * K-W-SV-U-0157M01 상품별 고객현황 조회
 * </pre>
 *
 * @author heymi.cho
 * @since 2023.08.02
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbCustomerPsByProductService {
    private final WsnbCustomerPsByProductMapper mapper;

    public List<SearchRes> getCustomerPsByProduct(SearchReq dto) {
        return mapper.selectCustomerPsByProduct(dto);
    }

    public PagingResult<SearchRes> getCustomerPsByProduct(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectCustomerPsByProduct(dto, pageInfo);
    }
}
