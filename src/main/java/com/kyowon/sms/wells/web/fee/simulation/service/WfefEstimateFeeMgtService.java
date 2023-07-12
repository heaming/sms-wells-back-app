package com.kyowon.sms.wells.web.fee.simulation.service;

import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchEstimateReq;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchHomeRes;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchOgMRes;
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
    public SearchOgPRes getEstimateFeeOgP(SearchEstimateReq req) {
        // @todo 조직관련 데이터 확정되면 데이터 0박아놓은거 정리 필요
        String userDvCd = mapper.selectUserDvCd(req.perfYm(), "W01", req.sellPrtnrNo());
        return new SearchOgPRes(
            userDvCd,
            mapper.selectBaseP(req, userDvCd),
            mapper.selectMeetingP(req, userDvCd),
            mapper.selectPerformanceP(req, userDvCd),
            mapper.selectEstimateP(req, userDvCd),
            mapper.selectSaleP(req, userDvCd)
        );
    }

     /**
     * 예상 수수료 조회 - M조직
     * @param req
     * @return
     */
    public SearchOgMRes getEstimateFeeOgM(SearchEstimateReq req) {
        // @todo 조직관련 데이터 확정되면 데이터 0박아놓은거 정리 필요
        String userDvCd = mapper.selectUserDvCd(req.perfYm(), "W02", req.sellPrtnrNo());
        return new SearchOgMRes(
            userDvCd,
            mapper.selectBaseM(req, userDvCd),
            mapper.selectMeetingM(req, userDvCd),
            mapper.selectPerformanceM(req, userDvCd),
            mapper.selectBsM(req, userDvCd),
            "OG".equals(userDvCd) ? mapper.selectOgBsM(req, userDvCd) : null,
            mapper.selectEstimateM(req, userDvCd),
            mapper.selectSaleM(req, userDvCd)
        );
    }

     /**
     * 예상 수수료 조회 - 홈마스터
     * @param req
     * @return
     */
    public SearchHomeRes getEstimateFeeHome(SearchEstimateReq req) {
        return new SearchHomeRes(mapper.selectBaseHome(req), mapper.selectPerformanceHome(req), mapper.selectEstimateHome(req) ,mapper.selectSaleHome(req));
    }
}
