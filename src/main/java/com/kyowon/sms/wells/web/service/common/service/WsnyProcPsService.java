package com.kyowon.sms.wells.web.service.common.service;

import com.kyowon.sms.wells.web.service.common.dto.WsnyProcPsDto.*;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyProcPsMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * [W-SV-U-1301M01] 홈카드 - 처리 현황 Service
 * <pre>
 *
 * @author 김동엽
 * @since 2023.11.16
 */
@Service
@RequiredArgsConstructor
public class WsnyProcPsService {
    private final WsnyProcPsMapper mapper;

    /**
     * 홈카드 - 처리 현황 조회
     * @param dto
     * @return
     */
    public SearchRes selectProcPs(SearchReq dto) {
        return mapper.selectProcPs(dto);
    }

    /**
     * 홈카드 - 오늘/내일 배정건수 조회
     * @param dto
     * @return
     */
    public SearchCnt selectTodayTomorrowCnt(SearchReq dto){
        return mapper.selectTodayTomorrowCnt(dto);
    }
}
