package com.kyowon.sms.wells.web.fee.simulation.service;

import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchOgPReq;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchOgPRes;
import com.kyowon.sms.wells.web.fee.simulation.mapper.WfefEstimateFeeMgtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 예상 수수료 조회
 * </pre>
 *
 * @author mj
 * @since 2023.05.09
 */
@Service
@RequiredArgsConstructor
public class WfefEstimateFeeMgtService {

    private final WfefEstimateFeeMgtMapper mapper;

    /**
     * 예상 수수료 조회 - P조직
     * @param req
     * @return
     */
    public SearchOgPRes getEstimateFeeOgP(SearchOgPReq req) {
        return new SearchOgPRes(mapper.selectBaseP(req),  mapper.selectPerformanceP(req), mapper.selectEstimateP(req), mapper.selectSaleP(req));
    }

}
