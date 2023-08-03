package com.kyowon.sms.wells.web.service.visit.service;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbBsOgProcsListDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbBsOgProcsListConverter;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbBsOgProcsListMapper;

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
public class WsnbBsOgProcsListService {

    private final WsnbBsOgProcsListMapper mapper;
    private final WsnbBsOgProcsListConverter converter;

    public List<SearchRes> getBsOgProcsList(SearchReq dto) {
        return converter.mapSearchResDvo(mapper.selectBsOgProcsList(dto));
    }

    public List<SearchCrdOvrRes> getCrdOvrList(SearchReq dto) {
        return converter.mapSearchCrdOvrResDvo(mapper.selectCrdOvrList(dto));
    }
}
