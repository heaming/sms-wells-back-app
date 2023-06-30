package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbServiceProcessingConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcessingDto.FindProductRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcessingDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcessingDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbServiceProcessingMapper;
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
public class WsnbServiceProcessingService {

    private final WsnbServiceProcessingMapper mapper;

    private final WsnbServiceProcessingConverter converter;

    public List<FindProductRes> getProducts(String pdGrpCd) {
        return this.mapper.selectProducts(pdGrpCd);
    }

    public PagingResult<SearchRes> getServiceProcessings(SearchReq dto, PageInfo pageInfo) {
        return new PagingResult<>(
            converter.mapAllDvoToSearchRes(mapper.selectServiceProcessings(dto, pageInfo)), pageInfo
        );
    }

    public List<SearchRes> getServiceProcessingsForExcel(SearchReq dto) {
        return converter.mapAllDvoToSearchRes(mapper.selectServiceProcessings(dto));
    }

}
