package com.kyowon.sms.wells.web.service.visit.service;

import java.util.Base64;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbServiceProcsIzQltyConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcsIzQltyDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcsIzQltyDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcsIzQltyDvo;
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
        PagingResult<WsnbServiceProcsIzQltyDvo> dvos = mapper.selectServiceProcsIzQltys(dto, pageInfo);

        for (WsnbServiceProcsIzQltyDvo dvo : dvos) {
            if (ObjectUtils.isNotEmpty(dvo.getCstSignCnByte())) {
                dvo.setCstSignCn(Base64.getEncoder().encodeToString(dvo.getCstSignCnByte()));
            }
        }

        return new PagingResult<>(converter.mapAllDvoToSearchRes(dvos), pageInfo);
    }

    public List<SearchRes> getServiceProcsIzQltysForExcel(SearchReq dto) {
        return converter.mapAllDvoToSearchRes(mapper.selectServiceProcsIzQltys(dto));
    }

}
