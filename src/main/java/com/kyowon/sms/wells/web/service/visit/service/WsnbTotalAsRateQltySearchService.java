package com.kyowon.sms.wells.web.service.visit.service;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbTotalAsRateQltySearchDto.SearchReq;
import static com.kyowon.sms.wells.web.service.visit.dto.WsnbTotalAsRateQltySearchDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbTotalAsRateQltySearchConverter;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbTotalAsRateQltySearchMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0248M01 총 A/S율 현황(품질)
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.09.01
 */
@Service
@RequiredArgsConstructor
public class WsnbTotalAsRateQltySearchService {

    private final WsnbTotalAsRateQltySearchMapper mapper;
    private final WsnbTotalAsRateQltySearchConverter converter;

    public List<SearchRes> getTotalAsRateQltyList(SearchReq dto) {
        return converter.mapSearchResDvo(mapper.selectTotalAsRateQltyList(dto));
    }

}
