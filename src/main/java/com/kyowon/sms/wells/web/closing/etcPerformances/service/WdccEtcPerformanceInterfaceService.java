package com.kyowon.sms.wells.web.closing.etcPerformances.service;

import com.kyowon.sms.wells.web.closing.etcPerformances.dto.WdccEtcPerformanceInterfaceDto.FindReq;
import com.kyowon.sms.wells.web.closing.etcPerformances.dto.WdccEtcPerformanceInterfaceDto.FindRes;
import com.kyowon.sms.wells.web.closing.etcPerformances.mapper.WdccEtcPerformanceInterfaceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<FindRes> getOtherLumpSumPerformance(FindReq req) {
        return mapper.selectOtherLumpSumPerformance(req);
    }
}
