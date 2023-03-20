package com.kyowon.sms.wells.web.closing.performances.interfaces.service;

import com.kyowon.sms.wells.web.closing.performances.interfaces.dto.WdccPerformanceInterfaceDto.FindReq;
import com.kyowon.sms.wells.web.closing.performances.interfaces.dto.WdccPerformanceInterfaceDto.FindRes;
import com.kyowon.sms.wells.web.closing.performances.interfaces.mapper.WdccPerformanceInterfaceMapper;
import com.sds.sflex.common.system.service.EaiInterfaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WdccPerformanceInterfaceService {
    private final WdccPerformanceInterfaceMapper mapper;
    private final EaiInterfaceService interfaceService;

    /**
     * wells일시불(할부)-'고객응대를 위한 고객센터 wells일시불(할부)-실적정보조회 Interface에 대한 결과를 조회 전달한다.
     *
     * @param req
     * @return list
     * @programId WCLI0004
     */
    public List<FindRes> getLumpSumPerformance(FindReq req) {

        //LumpSumPerformancesReqIvo req = new LumpSumPerformancesReqIvo();
        //req.setCNTRNO(dto.CNTR_NO());
        //req.setCNTRNO(dto.CNTR_NO());

        //LumpSumPerformancesResIvo res = new interfaceService.post(LumpSumPerformancesResIvo());

        return mapper.selectLumpSumPerformance(req);
    }
}
