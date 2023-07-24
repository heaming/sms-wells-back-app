package com.kyowon.sms.wells.web.closing.performance.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccEtcPerformanceInterfaceDto.FindReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccEtcPerformanceInterfaceDto.FindRes;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccEtcPerformanceInterfaceMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WdccEtcPerformanceInterfaceService {
    private final WdccEtcPerformanceInterfaceMapper mapper;

    /**
     * wells일시불외-'고객응대를 위한 고객센터 wells일시불(할부)-실적정보조회 Interface에 대한 결과를 조회
     *
     * @param req
     * @return list
     * @programId WCLI0004
     */
    public FindRes getOtherLumpSumPerformance(FindReq req) {
        return mapper.selectOtherLumpSumPerformance(req);
    }
}
