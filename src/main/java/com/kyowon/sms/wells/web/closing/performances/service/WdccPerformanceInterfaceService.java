package com.kyowon.sms.wells.web.closing.performances.service;

import com.kyowon.sms.wells.web.closing.performances.dto.WdccPerformanceInterfaceDto;
import com.kyowon.sms.wells.web.closing.performances.mapper.WdccPerformanceInterfaceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WdccPerformanceInterfaceService {
    private final WdccPerformanceInterfaceMapper mapper;

    /**
     * wells일시불(할부)-'고객응대를 위한 고객센터 wells일시불(할부)-실적정보조회 Interface에 대한 결과를 조회 전달한다.
     *
     * @param req
     * @return list
     * @programId WCLI0004
     */
    public List<WdccPerformanceInterfaceDto.FindRes> getLumpSumPerformance(WdccPerformanceInterfaceDto.FindReq req) {
        return mapper.selectLumpSumPerformance(req);
    }
}
