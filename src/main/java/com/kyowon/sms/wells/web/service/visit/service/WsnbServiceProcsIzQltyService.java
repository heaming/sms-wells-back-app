package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbServiceProcsIzQltyConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcsIzQltyDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcsIzQltyDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbServiceProcsIzQltyMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0099M01 서비스처리 내역(품질)
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.06.22
 */
@Service
@RequiredArgsConstructor
public class WsnbServiceProcsIzQltyService {

    private final WsnbServiceProcsIzQltyMapper mapper;

    private final WsnbServiceProcsIzQltyConverter converter;

    public PagingResult<SearchRes> getServiceProcsIzQltys(SearchReq dto, PageInfo pageInfo) {
        return new PagingResult<>(
            converter.mapAllDvoToSearchRes(mapper.selectServiceProcsIzQltys(dto, pageInfo)), pageInfo
        );
    }

    public List<SearchRes> getServiceProcsIzQltysForExcel(SearchReq dto) {
        return converter.mapAllDvoToSearchRes(mapper.selectServiceProcsIzQltys(dto));
    }

}
