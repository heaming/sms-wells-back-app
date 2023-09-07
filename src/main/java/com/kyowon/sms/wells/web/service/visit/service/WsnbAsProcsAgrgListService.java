package com.kyowon.sms.wells.web.service.visit.service;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbAsProcsAgrgListDto.SearchReq;
import static com.kyowon.sms.wells.web.service.visit.dto.WsnbAsProcsAgrgListDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbAsProcsAgrgListConverter;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbAsProcsAgrgListMapper;

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
public class WsnbAsProcsAgrgListService {

    private final WsnbAsProcsAgrgListMapper mapper;
    private final WsnbAsProcsAgrgListConverter converter;

    public List<SearchRes> getAsProcsAgrgList(SearchReq dto) {
        if (dto.taskType().equals("4")) {
            return converter.mapAllAsProcsAgrgListDvoToSearchRes(mapper.selectBsProcsAgrgList(dto));
        } else {
            return converter.mapAllAsProcsAgrgListDvoToSearchRes(mapper.selectAsProcsAgrgList(dto));
        }
    }

}
