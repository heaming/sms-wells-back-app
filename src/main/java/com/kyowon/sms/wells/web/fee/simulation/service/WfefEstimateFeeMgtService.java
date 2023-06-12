package com.kyowon.sms.wells.web.fee.simulation.service;

import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchEstimateReq;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchHomeRes;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchOgMRes;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchOgPRes;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchTabletHomeRes;
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
    public SearchOgPRes getEstimateFeeOgP(SearchEstimateReq req) {
        return new SearchOgPRes(mapper.selectBaseP(req),  mapper.selectPerformanceP(req), mapper.selectEstimateP(req), mapper.selectSaleP(req));
    }

     /**
     * 예상 수수료 조회 - M조직
     * @param req
     * @return
     */
    public SearchOgMRes getEstimateFeeOgM(SearchEstimateReq req) {
        return new SearchOgMRes(mapper.selectBaseM(req),  mapper.selectPerformanceM(req), mapper.selectBsM(req), mapper.selectEstimateM(req), mapper.selectSaleM(req));
    }

     /**
     * 예상 수수료 조회 - 홈마스터
     * @param req
     * @return
     */
    public SearchHomeRes getEstimateFeeHome(SearchEstimateReq req) {
        return new SearchHomeRes(mapper.selectBaseHome(req), mapper.selectPerformanceHome(req), mapper.selectEstimateHome(req) ,mapper.selectSaleHome(req));
    }

     /**
     * 예상 수수료 테블릿 조회 - 홈마스터
     * @param req
     * @return
     */
    public SearchTabletHomeRes getEstimateFeeTabletHome(SearchEstimateReq req) {
        return new SearchTabletHomeRes(mapper.selectTabletBaseHome(req), mapper.selectPerformanceTabletHome(req), mapper.selectEstimateTabletHome(req) ,mapper.selectSaleHome(req));
    }


}
