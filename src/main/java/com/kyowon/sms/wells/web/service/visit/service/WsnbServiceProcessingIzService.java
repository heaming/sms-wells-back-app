package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcessingIzDto.Product;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcessingIzDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcessingIzDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbServiceProcessingIzMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0054M01 서비스처리 내역
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.03.20
 */
@Service
@RequiredArgsConstructor
public class WsnbServiceProcessingIzService {

    private final WsnbServiceProcessingIzMapper mapper;

    public List<Product> getProducts(String pdGrpCd) {
        return this.mapper.selectProducts(pdGrpCd);
    }

    public PagingResult<SearchRes> getServiceProcessingItemizations(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectServiceProcessingItemizations(dto, pageInfo);
    }

}
