package com.kyowon.sms.wells.web.fee.simulation.service;

import com.kyowon.sms.common.web.fee.simulation.service.ZfefFeeSmlCalculationService;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchEstimateReq;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchHomeRes;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchOgMRes;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchOgPRes;
import com.kyowon.sms.wells.web.fee.simulation.mapper.WfefEstimateFeeMgtMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
    private final ZfefFeeSmlCalculationService feeSmlCalculationService;
    private final WfefEstimateFeeMgtMapper mapper;

    /**
     * 예상 수수료 조회 - P조직
     * @param req
     * @return
     */
    @Transactional
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

    @Transactional
    public SearchOgPRes getEstimateFeeOgP(SearchEstimateReq req, List<Map<String, Object>> addPerformances) {
        /* 추가실적이 있다면 실적을 DB에 넣고, 시뮬레이션 계산을 수행 */
        if(CollectionUtils.isNotEmpty(addPerformances)) {
            /* 추가실적 저장 */
            /* TODO 추가실적 저장하는 로직 구현 */

            /* 파트너번호에 해당하는 직급 조회 */
            String pstnDvCd = feeSmlCalculationService.getPstnDvCdByPrtnrNo(req.perfYm(), "W01", req.sellPrtnrNo());

            String feeCalcUnitTpCd = null;

            /* 직급에 따른 수수료시뮬레이션 계산 호출 */
            if("15".equals(pstnDvCd)) {
                feeCalcUnitTpCd = "101";
            }else if("7".equals(pstnDvCd)) {
                feeCalcUnitTpCd = "102";
            }

            feeSmlCalculationService.processFeeSmlCalculation(req.perfYm(), feeCalcUnitTpCd, req.perType(), req.sellPrtnrNo());
        }
        /* 화면에 필요한 데이터 조회 */
        /* TODO 화면 데이터 조회 */

        /* 추가실적 ROLLBACK */
        if(CollectionUtils.isNotEmpty(addPerformances)) {
            feeSmlCalculationService.rollback();
        }

        /* 조회한 데이터 화면에 리턴 */
        return null;
    }

     /**
     * 예상 수수료 조회 - M조직
     * @param req
     * @return
     */
     @Transactional
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

    @Transactional
    public SearchOgMRes getEstimateFeeOgM(SearchEstimateReq req, List<Map<String, Object>> addPerformances) {
        /* 추가실적이 있다면 실적을 DB에 넣고, 시뮬레이션 계산을 수행 */
        if(CollectionUtils.isNotEmpty(addPerformances)) {
            /* 추가실적 저장 */
            /* TODO 추가실적 저장하는 로직 구현 */

                        /* 파트너번호에 해당하는 직급 조회 */
            String pstnDvCd = feeSmlCalculationService.getPstnDvCdByPrtnrNo(req.perfYm(), "W02", req.sellPrtnrNo());

            String feeCalcUnitTpCd = null;

            /* 직급에 따른 수수료시뮬레이션 계산 호출 */
            if("15".equals(pstnDvCd)) {
                feeCalcUnitTpCd = "201";
            }else if("7".equals(pstnDvCd)) {
                feeCalcUnitTpCd = "202";
            }

            feeSmlCalculationService.processFeeSmlCalculation(req.perfYm(), feeCalcUnitTpCd, req.perType(), req.sellPrtnrNo());
        }
        /* 화면에 필요한 데이터 조회 */
        /* TODO 화면 데이터 조회 */

        /* 추가실적 ROLLBACK */
        if(CollectionUtils.isNotEmpty(addPerformances)) {
            feeSmlCalculationService.rollback();
        }

        /* 조회한 데이터 화면에 리턴 */
        return null;
    }

     /**
     * 예상 수수료 조회 - 홈마스터
     * @param req
     * @return
     */
     @Transactional
    public SearchHomeRes getEstimateFeeHome(SearchEstimateReq req) {
        return new SearchHomeRes(mapper.selectBaseHome(req), mapper.selectPerformanceHome(req), mapper.selectEstimateHome(req) ,mapper.selectSaleHome(req));
    }

     @Transactional
    public SearchHomeRes getEstimateFeeHome(SearchEstimateReq req, List<Map<String, Object>> addPerformances) {
        /* 추가실적이 있다면 실적을 DB에 넣고, 시뮬레이션 계산을 수행 */
        if(CollectionUtils.isNotEmpty(addPerformances)) {
            /* 추가실적 저장 */
            /* TODO 추가실적 저장하는 로직 구현 */

            feeSmlCalculationService.processFeeSmlCalculation(req.perfYm(), "301", req.perType(), req.sellPrtnrNo());
        }
        /* 화면에 필요한 데이터 조회 */
        /* TODO 화면 데이터 조회 */

        /* 추가실적 ROLLBACK */
        if(CollectionUtils.isNotEmpty(addPerformances)) {
            feeSmlCalculationService.rollback();
        }

        /* 조회한 데이터 화면에 리턴 */
        return null;
    }
}
