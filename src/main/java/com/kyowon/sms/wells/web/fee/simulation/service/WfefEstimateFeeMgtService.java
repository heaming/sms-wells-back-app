package com.kyowon.sms.wells.web.fee.simulation.service;

import com.kyowon.sms.common.web.fee.simulation.dvo.ZfefMacupCntrPerfDvo;
import com.kyowon.sms.common.web.fee.simulation.service.ZfefFeeSmlCalculationService;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.*;
import com.kyowon.sms.wells.web.fee.simulation.mapper.WfefEstimateFeeMgtMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public static final String P_OG_TP_CD = "W01";
    public static final String M_OG_TP_CD = "W02";
    public static final String HOME_OG_TP_CD = "W03";
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
        String userDvCd = mapper.selectUserDvCd(req.perfYm(), P_OG_TP_CD, req.sellPrtnrNo());
        String feeCalcUnitTpCd = null;

            /* 직급에 따른 수수료시뮬레이션 계산 호출 */
            if ("INDV".equals(userDvCd)) {
                feeCalcUnitTpCd = "101";
            } else if ("OG".equals(userDvCd)) {
                feeCalcUnitTpCd = "102";
            }
        feeSmlCalculationService.processFeeSmlCalculation(req.perfYm(), feeCalcUnitTpCd, req.perType(), req.sellPrtnrNo());
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
     * 추가실적 입력 예상 수수료 조회 - P조직
     *
     * 가전실적(개인) : W01P00003_0. 본인의 파트너번호로 생성. W01P00009(순주문실적) 실적도 생성
     * 가전외실적(개인) : W01P00004_0. 본인의 파트너번호로 생성. W01P00009(순주문실적) 실적도 생성
     * 가전실적(조직) : W01P00003_2. 지점장 하위 플래너번호로 생성. W01P00009(순주문실적) 실적도 생성
     * 가전외실적(조직) : W01P00004_2. 지점장 하위 플래너번호로 생성. W01P00009(순주문실적) 실적도 생성
     * 상조429실적(개인), 상조599실적(개인) SUM : W01P00010. 본인의 파트너번호로 생성. TB_IFIN_LIF_ALNC_FEE_CNTR_IZ 테이블의 LIF_PD_CD = '2178'
     * 상조429실적(조직), 상조599실적(조직) SUM : W01P00034. 지점장 하위 플래너번호로 생성. TB_IFIN_LIF_ALNC_FEE_CNTR_IZ 테이블의 LIF_PD_CD = '2178'
     *
     * @param req
     * @param addPerformances
     * @return
     */
    @Transactional
    public EstimateP getEstimateFeeOgP(SearchEstimateReq req, Map<String, Object> addPerformances) {

        /* 추가실적이 있다면 실적을 DB에 넣고, 시뮬레이션 계산을 수행 */
        if(ObjectUtils.isNotEmpty(addPerformances)) {
            /* 파트너번호에 해당하는 직급 조회 */
            String pstnDvCd = feeSmlCalculationService.getPstnDvCdByPrtnrNo(req.perfYm(), P_OG_TP_CD, req.sellPrtnrNo());
            String feeCalcUnitTpCd = null;
            String perfAgrgCrtDvCd = null;

            /* 직급에 따른 수수료시뮬레이션 계산 호출 */
            if ("15".equals(pstnDvCd)) {
                feeCalcUnitTpCd = "101";
                perfAgrgCrtDvCd = "101";
            } else if ("7".equals(pstnDvCd)) {
                feeCalcUnitTpCd = "102";
                perfAgrgCrtDvCd = "102";
            }

            /* 추가실적 저장 */
            String finalPerfAgrgCrtDvCd = perfAgrgCrtDvCd;
            addPerformances.keySet().forEach(keyName -> {
                switch(keyName) {
                    /* 가전실적(개인), 가전외실적(개인) */
                    case "W01P00003_0":
                    case "W01P00004_0":
                        /* 가전실적(개인), 가전외실적(개인)로 DUMMY 계약으로 INSERT */
                        feeSmlCalculationService.saveMacupCntrPerf(ZfefMacupCntrPerfDvo.builder()
                                .mmAcuPerfAgrgCrtDvCd(req.perType())
                                .baseYm(req.perfYm())
                                .perfAgrgCrtDvCd(finalPerfAgrgCrtDvCd)
                                .ogTpCd(P_OG_TP_CD)
                                .prtnrNo(req.sellPrtnrNo())
                                .hooPrtnrNo(req.sellPrtnrNo())
                                .perfAtcCd(keyName.split("_")[0])
                                .perfVal((Integer)addPerformances.get(keyName)).build());
                        /* W01P00009(순주문실적) DUMMY 계약으로 INSERT */
                        feeSmlCalculationService.saveMacupCntrPerf(ZfefMacupCntrPerfDvo.builder()
                                .mmAcuPerfAgrgCrtDvCd(req.perType())
                                .baseYm(req.perfYm())
                                .perfAgrgCrtDvCd(finalPerfAgrgCrtDvCd)
                                .ogTpCd(P_OG_TP_CD)
                                .prtnrNo(req.sellPrtnrNo())
                                .hooPrtnrNo(req.sellPrtnrNo())
                                .perfAtcCd("W01P00009")
                                .perfVal((Integer)addPerformances.get(keyName)).build());
                        break;
                    /* 가전실적(조직), 가전외실적(조직) */
                    case "W01P00003_2":
                    case "W01P00004_2":
                        /* 가전실적(조직), 가전외실적(조직)로 DUMMY 계약으로 INSERT */
                        feeSmlCalculationService.saveMacupCntrPerf(ZfefMacupCntrPerfDvo.builder()
                                .mmAcuPerfAgrgCrtDvCd(req.perType())
                                .baseYm(req.perfYm())
                                .perfAgrgCrtDvCd(finalPerfAgrgCrtDvCd)
                                .ogTpCd(P_OG_TP_CD)
                                .prtnrNo(feeSmlCalculationService.getPrtnrNoFromPrtnrHoo(req.perfYm(), P_OG_TP_CD, req.sellPrtnrNo()))
                                .hooPrtnrNo(req.sellPrtnrNo())
                                .perfAtcCd(keyName.split("_")[0])
                                .perfVal((Integer)addPerformances.get(keyName)).build());
                        /* W01P00009(순주문실적) DUMMY 계약으로 INSERT */
                        feeSmlCalculationService.saveMacupCntrPerf(ZfefMacupCntrPerfDvo.builder()
                                .mmAcuPerfAgrgCrtDvCd(req.perType())
                                .baseYm(req.perfYm())
                                .perfAgrgCrtDvCd(finalPerfAgrgCrtDvCd)
                                .ogTpCd(P_OG_TP_CD)
                                .prtnrNo(feeSmlCalculationService.getPrtnrNoFromPrtnrHoo(req.perfYm(), P_OG_TP_CD, req.sellPrtnrNo()))
                                .hooPrtnrNo(req.sellPrtnrNo())
                                .perfAtcCd("W01P00009")
                                .perfVal((Integer)addPerformances.get(keyName)).build());
                        break;
                    /* 상조429실적(개인), 상조599실적(개인) SUM */
                    case "W01P00010":
                        /* 상조429실적(개인), 상조599실적(개인) SUM로 DUMMY 계약으로 INSERT */
                        feeSmlCalculationService.saveMacupCntrPerf(ZfefMacupCntrPerfDvo.builder()
                                .mmAcuPerfAgrgCrtDvCd(req.perType())
                                .baseYm(req.perfYm())
                                .perfAgrgCrtDvCd(finalPerfAgrgCrtDvCd)
                                .ogTpCd(P_OG_TP_CD)
                                .prtnrNo(req.sellPrtnrNo())
                                .hooPrtnrNo(req.sellPrtnrNo())
                                .perfAtcCd(keyName)
                                .perfVal((Integer)addPerformances.get(keyName)).build());
                        break;
                    /* 상조429실적(조직), 상조599실적(조직) SUM */
                    case "W01P00034":
                        feeSmlCalculationService.saveMacupCntrPerf(ZfefMacupCntrPerfDvo.builder()
                                .mmAcuPerfAgrgCrtDvCd(req.perType())
                                .baseYm(req.perfYm())
                                .perfAgrgCrtDvCd(finalPerfAgrgCrtDvCd)
                                .ogTpCd(P_OG_TP_CD)
                                .prtnrNo(feeSmlCalculationService.getPrtnrNoFromPrtnrHoo(req.perfYm(), P_OG_TP_CD, req.sellPrtnrNo()))
                                .hooPrtnrNo(req.sellPrtnrNo())
                                .perfAtcCd(keyName)
                                .perfVal((Integer)addPerformances.get(keyName)).build());
                        break;

                }
            });

            feeSmlCalculationService.processFeeSmlCalculation(req.perfYm(), feeCalcUnitTpCd, req.perType(), req.sellPrtnrNo());
        }

        /* 화면에 필요한 데이터 조회 */
        String userDvCd = mapper.selectUserDvCd(req.perfYm(), P_OG_TP_CD, req.sellPrtnrNo());
        EstimateP response = mapper.selectEstimateP(req, userDvCd);

        if(ObjectUtils.isNotEmpty(addPerformances)) {
             /* 추가실적 ROLLBACK */
            feeSmlCalculationService.rollback();
        }

        /* 조회한 데이터 화면에 리턴 */
        return response;
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

    /**
     * 추가실적 입력 예상수수료 조회 - M추진단
     *
     * 가전인정건수(개인) :
     * 가전인정건수(조직) :
     * 렌탈기준가(개인) :
     * 렌탈기준가(조직) :
     * 일시불기준가(개인) :
     * 일시불기준가(조직) :
     * 가전외인정실적(개인) :
     * 가전외인정실적(조직) :
     * 순증(개인) :
     * 순증(조직) :
     * BS정수기1완료건수 :
     * BS정수기2완료건수 :
     * BS정수기3완료건수 :
     * BS정수기4완료건수 :
     * BS비정수기완료건수 :
     * BS청정기1완료건수 :
     * BS청정기2완료건수 :
     * BS아웃소싱류완료건수 :
     * BS비데,연수기완료건수 :
     * W1급지 :
     * W2급지 :
     * 조직BS완료건수 :
     * @param req
     * @param addPerformances
     * @return
     */
    @Transactional
    public SearchOgMRes getEstimateFeeOgM(SearchEstimateReq req, Map<String, Object> addPerformances) {
        /* 추가실적이 있다면 실적을 DB에 넣고, 시뮬레이션 계산을 수행 */
        if(ObjectUtils.isNotEmpty(addPerformances)) {
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
        if(ObjectUtils.isNotEmpty(addPerformances)) {
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

    /**
     * 추가실적 입력 예상수수료 조회 - 홈마스터
     *
     * 가전인정건수 :
     * 일시불 :
     * 전체처리건 :
     * 가전처리건 :
     * 교육수료 :
     *
     * @param req
     * @param addPerformances
     * @return
     */
     @Transactional
    public SearchHomeRes getEstimateFeeHome(SearchEstimateReq req, Map<String, Object> addPerformances) {
        /* 추가실적이 있다면 실적을 DB에 넣고, 시뮬레이션 계산을 수행 */
        if(ObjectUtils.isNotEmpty(addPerformances)) {
            /* 추가실적 저장 */
            /* TODO 추가실적 저장하는 로직 구현 */

            feeSmlCalculationService.processFeeSmlCalculation(req.perfYm(), "301", req.perType(), req.sellPrtnrNo());
        }
        /* 화면에 필요한 데이터 조회 */
        /* TODO 화면 데이터 조회 */

        /* 추가실적 ROLLBACK */
        if(ObjectUtils.isNotEmpty(addPerformances)) {
            feeSmlCalculationService.rollback();
        }

        /* 조회한 데이터 화면에 리턴 */
        return null;
    }
}
