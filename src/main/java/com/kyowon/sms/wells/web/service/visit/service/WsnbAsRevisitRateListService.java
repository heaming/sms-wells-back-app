package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbAsRevisitRateListConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbAsRevisitRateListDto;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbAsRevisitRateListMapper;

import lombok.RequiredArgsConstructor;

/**
 *
 * <pre>
 * K-W-SV-U-0025M01 A/S 처리 현황
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.07.21
 */
@Service
@RequiredArgsConstructor
public class WsnbAsRevisitRateListService {

    private final WsnbAsRevisitRateListMapper mapper;
    private final WsnbAsRevisitRateListConverter converter;

    public List<WsnbAsRevisitRateListDto.SearchRes> getAsRevisitRateList(WsnbAsRevisitRateListDto.SearchReq dto) {
        return converter.mapAllAsRevisitRateListDvoToSearchRes(mapper.selectAsRevisitRateList(dto));
    }

}
