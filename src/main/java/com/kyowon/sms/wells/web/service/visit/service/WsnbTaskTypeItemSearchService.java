package com.kyowon.sms.wells.web.service.visit.service;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbTaskTypeItemSearchDto.SearchReq;
import static com.kyowon.sms.wells.web.service.visit.dto.WsnbTaskTypeItemSearchDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbTaskTypeItemSearchConverter;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbTaskTypeItemSearchMapper;

import lombok.RequiredArgsConstructor;

/**
 *
 * <pre>
 * K-W-SV-U-0075M01 업무유형별 품목 현황
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.10
 */
@Service
@RequiredArgsConstructor
public class WsnbTaskTypeItemSearchService {

    private final WsnbTaskTypeItemSearchMapper mapper;
    private final WsnbTaskTypeItemSearchConverter converter;

    public List<SearchRes> getTaskTypeItemList(SearchReq dto) {
        return converter.mapSearchResDvo(mapper.selectTaskTypeItemList(dto));
    }

}
