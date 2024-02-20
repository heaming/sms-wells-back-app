package com.kyowon.sms.wells.web.fee.simulation.service;

import com.kyowon.sms.common.web.fee.simulation.dvo.ZfefMacupCntrPerfDvo;
import com.kyowon.sms.common.web.fee.simulation.dvo.ZfefMacupPerfClDvo;
import com.kyowon.sms.common.web.fee.simulation.dvo.ZfefPrtnrPerfMmAcuClDvo;
import com.kyowon.sms.common.web.fee.simulation.service.ZfefFeeSmlCalculationService;
import com.kyowon.sms.common.web.fee.standard.dvo.ZfeyTargetPartnerConditionDvo;
import com.kyowon.sms.common.web.fee.standard.service.ZfeyFeeStandardService;
import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebMogMetDvo;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.*;
import com.kyowon.sms.wells.web.fee.simulation.mapper.WfefEstimateFeeMgtMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private final ZfeyFeeStandardService feeStandardService;
    private final ZfefFeeSmlCalculationService feeSmlCalculationService;
    private final WfefEstimateFeeMgtMapper mapper;

    /**
     * 예상 수수료 조회 - P조직
     * @param req
     * @return
     */
    @Transactional
    public SearchOgPRes getEstimateFeeOgP(SearchEstimateReq req) {
        String userDvCd = mapper.selectUserDvCd(req.perfYm(), P_OG_TP_CD, req.sellPrtnrNo());
        String feeCalcUnitTpCd = null;

        /* 직급에 따른 수수료시뮬레이션 계산 호출 */
        if ("INDV".equals(userDvCd)) {
            feeCalcUnitTpCd = "101";
        } else if ("OG".equals(userDvCd)) {
            feeCalcUnitTpCd = "102";
        }

        feeSmlCalculationService.processFeeSmlCalculation(req.perfYm(), feeCalcUnitTpCd, req.perType(), req.sellPrtnrNo());
        List<ZfeyTargetPartnerConditionDvo> feeCds = feeStandardService.getSimulationTargetFeeCodes(req.perfYm(), P_OG_TP_CD, req.sellPrtnrNo());
        return new SearchOgPRes(
            userDvCd,
            mapper.selectBaseP(req, userDvCd),
            mapper.selectMeetingP(req, userDvCd),
            mapper.selectPerformanceP(req, userDvCd),
            mapper.selectEstimatePM(req, "W01", feeCds),
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
    public List<EstimatePM> getEstimateFeeOgP(SearchEstimateReq req, Map<String, Object> addPerformances) {
        /* 추가실적이 있다면 실적을 DB에 넣고, 시뮬레이션 계산을 수행 */
        if(ObjectUtils.isNotEmpty(addPerformances)) {
            /* 파트너번호에 해당하는 직급 조회 */
            String pstnDvCd = feeSmlCalculationService.getPstnDvCdByPrtnrNo(req.perfYm(), P_OG_TP_CD, req.sellPrtnrNo());
            String feeCalcUnitTpCd = null;

            /* 직급에 따른 수수료시뮬레이션 계산 호출 */
            if ("15".equals(pstnDvCd)) {
                feeCalcUnitTpCd = "101";
            } else if ("7".equals(pstnDvCd)) {
                feeCalcUnitTpCd = "102";
            }

            /* 추가실적 저장 */
            String perfAgrgCrtDvCd = getPerfAgrgCrtDvCd(req.perType());
            addPerformances.keySet().forEach(keyName -> {
                switch(keyName) {
                    /* 가전실적(개인), 가전외실적(개인) */
                    case "W01P00003_0":
                    case "W01P00004_0":
                        /* 가전실적(개인), 가전외실적(개인)로 DUMMY 계약으로 INSERT */
                        feeSmlCalculationService.saveMacupCntrPerf(ZfefMacupCntrPerfDvo.builder()
                                .mmAcuPerfAgrgCrtDvCd(req.perType())
                                .baseYm(req.perfYm())
                                .perfAgrgCrtDvCd(perfAgrgCrtDvCd)
                                .ogTpCd(P_OG_TP_CD)
                                .prtnrNo(req.sellPrtnrNo())
                                .hooPrtnrNo(req.sellPrtnrNo())
                                .perfAtcCd(keyName.split("_")[0])
                                .perfVal((Integer)addPerformances.get(keyName)).build());
                        /* W01P00009(순주문실적) DUMMY 계약으로 INSERT */
                        feeSmlCalculationService.saveMacupCntrPerf(ZfefMacupCntrPerfDvo.builder()
                                .mmAcuPerfAgrgCrtDvCd(req.perType())
                                .baseYm(req.perfYm())
                                .perfAgrgCrtDvCd(perfAgrgCrtDvCd)
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
                                .perfAgrgCrtDvCd(perfAgrgCrtDvCd)
                                .ogTpCd(P_OG_TP_CD)
                                .prtnrNo(feeSmlCalculationService.getPrtnrNoFromPrtnrHoo(req.perfYm(), P_OG_TP_CD, req.sellPrtnrNo()))
                                .hooPrtnrNo(req.sellPrtnrNo())
                                .perfAtcCd(keyName.split("_")[0])
                                .perfVal((Integer)addPerformances.get(keyName)).build());
                        /* W01P00009(순주문실적) DUMMY 계약으로 INSERT */
                        feeSmlCalculationService.saveMacupCntrPerf(ZfefMacupCntrPerfDvo.builder()
                                .mmAcuPerfAgrgCrtDvCd(req.perType())
                                .baseYm(req.perfYm())
                                .perfAgrgCrtDvCd(perfAgrgCrtDvCd)
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
                                .perfAgrgCrtDvCd(perfAgrgCrtDvCd)
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
                                .perfAgrgCrtDvCd(perfAgrgCrtDvCd)
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
        List<ZfeyTargetPartnerConditionDvo> feeCds = feeStandardService.getSimulationTargetFeeCodes(req.perfYm(), P_OG_TP_CD, req.sellPrtnrNo());
        List<EstimatePM> response = mapper.selectEstimatePM(req, "W01", feeCds);

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
        String userDvCd = mapper.selectUserDvCd(req.perfYm(), "W02", req.sellPrtnrNo());
        String feeCalcUnitTpCd = null;

        /* 직급에 따른 수수료시뮬레이션 계산 호출 */
        if ("INDV".equals(userDvCd)) {
            feeCalcUnitTpCd = "101";
        } else if ("OG".equals(userDvCd)) {
            feeCalcUnitTpCd = "102";
        }

        feeSmlCalculationService.processFeeSmlCalculation(req.perfYm(), feeCalcUnitTpCd, req.perType(), req.sellPrtnrNo());
        List<ZfeyTargetPartnerConditionDvo> feeCds = feeStandardService.getSimulationTargetFeeCodes(req.perfYm(), M_OG_TP_CD, req.sellPrtnrNo());
        BaseM base = mapper.selectBaseM(req, userDvCd);
        List<WfebMogMetDvo> meetingPerfAtcCds = getMtPerfAtcCds(userDvCd, base.qlfDvCd());

        return new SearchOgMRes(
            userDvCd,
            base,
            mapper.selectMeetingM(req, userDvCd, base.qlfDvCd(), meetingPerfAtcCds,
                    meetingPerfAtcCds.stream().map(item -> "'" + item.getPerfAtcCd() + "' AS " + item.getPerfAtcCd()).collect(Collectors.joining(", "))),
            mapper.selectPerformanceM(req, userDvCd),
            mapper.selectBsM(req, userDvCd),
            "OG".equals(userDvCd) ? mapper.selectOgBsM(req, userDvCd) : null,
            mapper.selectEstimatePM(req, "W02", feeCds),
            mapper.selectSaleM(req, userDvCd)
        );
    }

    /**
     * 추가실적 입력 예상수수료 조회 - M추진단
     *
     * 미팅참석여부 : W02P00001, TB_FEAM_PRTNR_PERF_MM_ACU_CL
     * 스타트업교육이수여부 : W02P00115, TB_FEAM_PRTNR_PERF_MM_ACU_CL
     * 보수교육이수여부 : W02P00116, TB_FEAM_PRTNR_PERF_MM_ACU_CL
     * 프리스타트업이수여부 : W02P00117, TB_FEAM_PRTNR_PERF_MM_ACU_CL
     * 프리1정수기이수여부 : W02P00127, TB_FEAM_PRTNR_PERF_MM_ACU_CL
     * 프리2비데/기타이수여부 : W02P00128, TB_FEAM_PRTNR_PERF_MM_ACU_CL
     * 프리3세일즈이수여부 : W02P00129, TB_FEAM_PRTNR_PERF_MM_ACU_CL
     * 프리4Wells live이수여부 : W02P00130, TB_FEAM_PRTNR_PERF_MM_ACU_CL
     * 프리5Wells live이수여부 : W02P00131, TB_FEAM_PRTNR_PERF_MM_ACU_CL
     * 매니저정착1이수여부 : W02P00124, TB_FEAM_PRTNR_PERF_MM_ACU_CL
     * 매니저정착2이수여부 : W02P00125, TB_FEAM_PRTNR_PERF_MM_ACU_CL
     * 매니저정착345이수여부 : W02P00126, TB_FEAM_PRTNR_PERF_MM_ACU_CL
     * 지점장온라인이수여부 : W02P00120, TB_FEAM_PRTNR_PERF_MM_ACU_CL
     * 실활동 WM수 : W02P00111, TB_FEAM_PRTNR_PERF_MM_ACU_CL
     * 가전인정건수(개인) : W02P00002_0, TB_FEAM_MACUP_CNTR_PERF_CL, 본인실적으로 생성
     * 가전인정건수(조직) : W02P00002_2, TB_FEAM_MACUP_CNTR_PERF_CL, 지점장 소속 플래너로 생성
     * 렌탈기준가(개인) : W00P00003_0, TB_FEAM_MACUP_CNTR_PERF_CL, 본인실적으로 생성, W00P00004_0, W00P00005_0 동일계약번호로 0원 추가
     * 렌탈기준가(조직) : W00P00003_2, TB_FEAM_MACUP_CNTR_PERF_CL, 지점장 소속 플래너로 생성, W00P00004_2, W00P00005_2 동일계약번호로 0원 추가
     * 일시불기준가(개인) : W00P00004_0, TB_FEAM_MACUP_CNTR_PERF_CL, 본인실적으로 생성, W00P00003_0, W00P00005_0 동일계약번호로 0원 추가
     * 일시불기준가(조직) : W00P00004_2, TB_FEAM_MACUP_CNTR_PERF_CL, 지점장 소속 플래너로 생성, W00P00003_2, W00P00005_2 동일계약번호로 0원 추가
     * 가전외인정실적(개인) : W02P00112_0, TB_FEAM_MACUP_CNTR_PERF_CL, 본인실적으로 생성, W00P00007_0 동일계약번호로 0원 추가
     * 가전외인정실적(조직) : W02P00112_2, TB_FEAM_MACUP_CNTR_PERF_CL, 지점장 소속 플래너로 생성, W00P00007_2 동일계약번호로 0원 추가
     * 순증(조직) : W02P00113_2, TB_FEAM_MACUP_CNTR_PERF_CL, 지점장 소속 플래너로 생성
     * BS관리수수료기준금액 : W02P00096, TB_FEAM_MACUP_PERF_CL, 각 상품별 완료건수 * 기준금액의 합
     *                     BS정수기1완료건수(7000원), BS정수기2완료건수(8000원), BS정수기3완료건수(8500원), BS정수기4완료건수(10000원)
     *                   , BS비정수기완료건수(5000원), BS청정기1완료건수(6300원), BS청정기2완료건수(6700원), BS아웃소싱류완료건수(6500원)
     *                   , BS비데,연수기완료건수(5500원)
     * BS실적건수 : W02P00085_0, TB_FEAM_MACUP_PERF_CL, 각 상품별 완료건수의 합
     * BS실적비율 : W02R00095, TB_FEAM_MACUP_PERF_CL, 실적건수 / 전체 배정건수. %
     * 급지기준금액(W1급지,W2급지) : W02P00100, 1급지건수 * 1000원 + 2급지건수 * 3000원
     * 조직BS완료건수 : W02P00085_2, 지점장 조직 데이터만 업데이트
     *
     * @param req
     * @param addPerformances
     * @return
     */
    @Transactional
    public List<EstimatePM> getEstimateFeeOgM(SearchEstimateReq req, Map<String, Object> addPerformances) {
        /* 추가실적이 있다면 실적을 DB에 넣고, 시뮬레이션 계산을 수행 */
        if(ObjectUtils.isNotEmpty(addPerformances)) {
            /* 추가실적 저장 */
            String perfAgrgCrtDvCd = getPerfAgrgCrtDvCd(req.perType());
            addPerformances.keySet().forEach(keyName -> {
                switch(keyName) {
                    /* 교육이수 */
                    case "W02P00001":
                    case "W02P00115":
                    case "W02P00116":
                    case "W02P00117":
                    case "W02P00127":
                    case "W02P00128":
                    case "W02P00129":
                    case "W02P00130":
                    case "W02P00131":
                    case "W02P00124":
                    case "W02P00125":
                    case "W02P00126":
                    case "W02P00120":
                        feeSmlCalculationService.savePrtnrPerfMmAcuCl(ZfefPrtnrPerfMmAcuClDvo.builder()
                            .mmAcuPerfAgrgCrtDvCd(req.perType())
                            .baseYm(req.perfYm())
                            .perfAgrgCrtDvCd(perfAgrgCrtDvCd)
                            .ogTpCd(M_OG_TP_CD)
                            .prtnrNo(req.sellPrtnrNo())
                            .perfAtcCd(keyName)
                            .feePerfAtcVal((String) addPerformances.get(keyName))
                            .build()
                    );
                        break;
                    /* 실활동 WM 수 */
                    case "W02P00111":
                        feeSmlCalculationService.savePrtnrPerfMmAcuCl(ZfefPrtnrPerfMmAcuClDvo.builder()
                            .mmAcuPerfAgrgCrtDvCd(req.perType())
                            .baseYm(req.perfYm())
                            .perfAgrgCrtDvCd(perfAgrgCrtDvCd)
                            .ogTpCd(M_OG_TP_CD)
                            .prtnrNo(req.sellPrtnrNo())
                            .perfAtcCd(keyName)
                            .perfVal((Integer) addPerformances.get(keyName))
                            .build()
                    );
                        break;
                    /* 렌탈기준가(개인), 일시불기준가(개인), 가전외인정실적(개인) */
                    case "W00P00003_0":
                    case "W00P00004_0":
                    case "W02P00112_0":
                        List<String> addPerfAtcCds = new ArrayList<>();

                        if("W00P00003_0".equals(keyName)) {
                            addPerfAtcCds.add("W00P00004");
                            addPerfAtcCds.add("W00P00005");
                        } else if("W00P00004_0".equals(keyName)) {
                            addPerfAtcCds.add("W00P00003");
                            addPerfAtcCds.add("W00P00005");
                        } else if("W02P00112_0".equals(keyName)) {
                            addPerfAtcCds.add("W00P00007");
                        }
                        feeSmlCalculationService.saveMacupCntrPerf(ZfefMacupCntrPerfDvo.builder()
                                .mmAcuPerfAgrgCrtDvCd(req.perType())
                                .baseYm(req.perfYm())
                                .perfAgrgCrtDvCd(perfAgrgCrtDvCd)
                                .ogTpCd(M_OG_TP_CD)
                                .prtnrNo(req.sellPrtnrNo())
                                .hooPrtnrNo(req.sellPrtnrNo())
                                .perfAtcCd(keyName.split("_")[0])
                                .perfVal((Integer)addPerformances.get(keyName)).build(), addPerfAtcCds);
                        break;
                    /* 가전인정건수(개인) */
                    case "W02P00002_0":
                        /* 가전인정건수(개인)로 DUMMY 계약으로 INSERT */
                        feeSmlCalculationService.saveMacupCntrPerf(ZfefMacupCntrPerfDvo.builder()
                                .mmAcuPerfAgrgCrtDvCd(req.perType())
                                .baseYm(req.perfYm())
                                .perfAgrgCrtDvCd(perfAgrgCrtDvCd)
                                .ogTpCd(M_OG_TP_CD)
                                .prtnrNo(req.sellPrtnrNo())
                                .hooPrtnrNo(req.sellPrtnrNo())
                                .perfAtcCd(keyName.split("_")[0])
                                .perfVal((Integer)addPerformances.get(keyName)).build());
                        break;
                    /* 렌탈기준가(조직), 일시불기준가(조직), 가전외인정실적(조직) */
                    case "W00P00003_2":
                    case "W00P00004_2":
                    case "W02P00112_2":
                        List<String> addOgPerfAtcCds = new ArrayList<>();

                        if("W00P00003_2".equals(keyName)) {
                            addOgPerfAtcCds.add("W00P00004");
                            addOgPerfAtcCds.add("W00P00005");
                        } else if("W00P00004_2".equals(keyName)) {
                            addOgPerfAtcCds.add("W00P00003");
                            addOgPerfAtcCds.add("W00P00005");
                        } else if("W02P00112_2".equals(keyName)) {
                            addOgPerfAtcCds.add("W00P00007");
                        }
                        /* 일시불기준가(조직),가전외인정실적(조직), 가전외인정실적(조직)로 DUMMY 계약으로 INSERT */
                        feeSmlCalculationService.saveMacupCntrPerf(ZfefMacupCntrPerfDvo.builder()
                                .mmAcuPerfAgrgCrtDvCd(req.perType())
                                .baseYm(req.perfYm())
                                .perfAgrgCrtDvCd(perfAgrgCrtDvCd)
                                .ogTpCd(M_OG_TP_CD)
                                .prtnrNo(feeSmlCalculationService.getPrtnrNoFromPrtnrHoo(req.perfYm(), M_OG_TP_CD, req.sellPrtnrNo()))
                                .hooPrtnrNo(req.sellPrtnrNo())
                                .perfAtcCd(keyName.split("_")[0])
                                .perfVal((Integer)addPerformances.get(keyName)).build(), addOgPerfAtcCds);
                        break;
                    /* 가전인정건수(조직), 순증(조직) */
                    case "W02P00002_2":
                    case "W02P00113_2":
                        /* 가전인정건수(조직), 순증(조직)로 DUMMY 계약으로 INSERT */
                        feeSmlCalculationService.saveMacupCntrPerf(ZfefMacupCntrPerfDvo.builder()
                                .mmAcuPerfAgrgCrtDvCd(req.perType())
                                .baseYm(req.perfYm())
                                .perfAgrgCrtDvCd(perfAgrgCrtDvCd)
                                .ogTpCd(M_OG_TP_CD)
                                .prtnrNo(feeSmlCalculationService.getPrtnrNoFromPrtnrHoo(req.perfYm(), M_OG_TP_CD, req.sellPrtnrNo()))
                                .hooPrtnrNo(req.sellPrtnrNo())
                                .perfAtcCd(keyName.split("_")[0])
                                .perfVal((Integer)addPerformances.get(keyName)).build());
                        break;
                    case "W02P00095": // BS실적비율
                    case "W02P00096": // BS관리수수료기준금액
                    case "W02P00100": // 급지기준금액
                        feeSmlCalculationService.saveMacupPerfCl(ZfefMacupPerfClDvo.builder()
                                .mmAcuPerfAgrgCrtDvCd(req.perType())
                                .baseYm(req.perfYm())
                                .perfAgrgCrtDvCd(perfAgrgCrtDvCd)
                                .ogTpCd(M_OG_TP_CD)
                                .prtnrNo(req.sellPrtnrNo())
                                .perfAtcCd(keyName)
                                .perfVal((Integer)addPerformances.get(keyName)).build());
                        break;
                    case "W02P00085_0": // BS실적건수(개인)
                    case "W02P00085_2": // Bs실적건수(조직)
                        feeSmlCalculationService.saveMacupPerfCl(ZfefMacupPerfClDvo.builder()
                                .mmAcuPerfAgrgCrtDvCd(req.perType())
                                .baseYm(req.perfYm())
                                .perfAgrgCrtDvCd(perfAgrgCrtDvCd)
                                .ogTpCd(M_OG_TP_CD)
                                .prtnrNo(req.sellPrtnrNo())
                                .perfAtcCd(keyName.split("_")[0])
                                .perfDvCd(keyName.split("_")[1])
                                .perfVal((Integer)addPerformances.get(keyName)).build());
                        break;
                }
            });

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
        List<ZfeyTargetPartnerConditionDvo> feeCds = feeStandardService.getSimulationTargetFeeCodes(req.perfYm(), M_OG_TP_CD, req.sellPrtnrNo());
        List<EstimatePM> response = mapper.selectEstimatePM(req, "W02", feeCds);

        /* 추가실적 ROLLBACK */
        if(ObjectUtils.isNotEmpty(addPerformances)) {
            feeSmlCalculationService.rollback();
        }

        /* 조회한 데이터 화면에 리턴 */
        return response;
    }

     /**
     * 예상 수수료 조회 - 홈마스터
     * @param req
     * @return
     */
     @Transactional
    public SearchHomeRes getEstimateFeeHome(SearchEstimateReq req) {
         feeSmlCalculationService.processFeeSmlCalculation(req.perfYm(), "301", req.perType(), req.sellPrtnrNo());
        List<ZfeyTargetPartnerConditionDvo> feeCds = feeStandardService.getSimulationTargetFeeCodes(req.perfYm(), HOME_OG_TP_CD, req.sellPrtnrNo());
        String pivotColumns = feeCds.stream().map(item -> "'" + item.getDtaCrtFeeCd() + "' AS " + item.getDtaCrtFeeCd()).collect(Collectors.joining(", "));
        return new SearchHomeRes(mapper.selectBaseHome(req), mapper.selectPerformanceHome(req), mapper.selectEstimateHome(req, feeCds, pivotColumns) ,mapper.selectSaleHome(req));
    }

    /**
     * 추가실적 입력 예상수수료 조회 - 홈마스터
     *
     * 가전인정건수 : 'W03P00002', TB_FEAM_MACUP_PERF_CL
     * 일시불 : 'W00P00080', TB_FEAM_MACUP_CNTR_PERF_CL
     * 전체처리건 : 'W03P00085', TB_FEAM_MACUP_PERF_CL
     * 가전처리건 : 'W03P00117', TB_FEAM_MACUP_PERF_CL
     * 교육수료 : 'W03P00111', 'W03P00112' TB_FEAM_PRTNR_PERF_MM_ACU_CL
     *
     * @param req
     * @param addPerformances
     * @return
     */
     @Transactional
    public List<Map<String, Object>> getEstimateFeeHome(SearchEstimateReq req, Map<String, Object> addPerformances) {
        /* 추가실적이 있다면 실적을 DB에 넣고, 시뮬레이션 계산을 수행 */
        if(ObjectUtils.isNotEmpty(addPerformances)) {
            /* 추가실적 저장 */
            String perfAgrgCrtDvCd = getPerfAgrgCrtDvCd(req.perType());
            addPerformances.keySet().forEach(keyName -> {
                switch(keyName) {
                    /* 가전인정건수, 전체처리건, 가전처리건 */
                    case "W03P00002":
                    case "W03P00085":
                    case "W03P00117":
                        /* 가전인정건수, 전체처리건, 가전처리건로 파트너단위 업데이트 */
                        feeSmlCalculationService.saveMacupPerfCl(ZfefMacupPerfClDvo.builder()
                                .mmAcuPerfAgrgCrtDvCd(req.perType())
                                .baseYm(req.perfYm())
                                .perfAgrgCrtDvCd(perfAgrgCrtDvCd)
                                .ogTpCd(HOME_OG_TP_CD)
                                .prtnrNo(req.sellPrtnrNo())
                                .perfAtcCd(keyName)
                                .perfVal((Integer)addPerformances.get(keyName)).build());
                        break;
                    /* 일시불 */
                    case "W00P00080":
                        /* 일시불로 DUMMY 계약으로 INSERT */
                        feeSmlCalculationService.saveMacupCntrPerf(ZfefMacupCntrPerfDvo.builder()
                                .mmAcuPerfAgrgCrtDvCd(req.perType())
                                .baseYm(req.perfYm())
                                .perfAgrgCrtDvCd(perfAgrgCrtDvCd)
                                .ogTpCd(HOME_OG_TP_CD)
                                .prtnrNo(req.sellPrtnrNo())
                                .hooPrtnrNo(req.sellPrtnrNo())
                                .perfAtcCd(keyName)
                                .perfVal((Integer)addPerformances.get(keyName)).build());
                        break;
                    /* 교육수료 SUM */
                    case "W03P00111":
                    case "W03P00112":
                        /* 신입교육, 동행교육 파트너 실적으로 업데이트 */
                        feeSmlCalculationService.savePrtnrPerfMmAcuCl(ZfefPrtnrPerfMmAcuClDvo.builder()
                                .mmAcuPerfAgrgCrtDvCd(req.perType())
                                .baseYm(req.perfYm())
                                .perfAgrgCrtDvCd(perfAgrgCrtDvCd)
                                .ogTpCd(HOME_OG_TP_CD)
                                .prtnrNo(req.sellPrtnrNo())
                                .perfAtcCd(keyName)
                                .feePerfAtcVal(String.valueOf(addPerformances.get(keyName))).build());
                        break;
                }
            });

            feeSmlCalculationService.processFeeSmlCalculation(req.perfYm(), "301", req.perType(), req.sellPrtnrNo());
        }
        /* 화면에 필요한 데이터 조회 */
        List<ZfeyTargetPartnerConditionDvo> feeCds = feeStandardService.getSimulationTargetFeeCodes(req.perfYm(), HOME_OG_TP_CD, req.sellPrtnrNo());
        String pivotColumns = feeCds.stream().map(item -> "'" + item.getDtaCrtFeeCd() + "' AS " + item.getDtaCrtFeeCd()).collect(Collectors.joining(", "));
        List<Map<String, Object>> response = mapper.selectEstimateHome(req, feeCds, pivotColumns);

        /* 추가실적 ROLLBACK */
        if(ObjectUtils.isNotEmpty(addPerformances)) {
            feeSmlCalculationService.rollback();
        }

        /* 조회한 데이터 화면에 리턴 */
        return response;
    }

    private static String getPerfAgrgCrtDvCd(String perType) {
        String perfAgrgCrtDvCd = null;
        if ("00".equals(perType)) {
            perfAgrgCrtDvCd = "999";
        } else {
             perfAgrgCrtDvCd = "101";
         }
        return perfAgrgCrtDvCd;
    }

    @Transactional
    public List<WfebMogMetDvo> getMtPerfAtcCds(SearchEstimateReq req) {
         String userDvCd = mapper.selectUserDvCd(req.perfYm(), "W02", req.sellPrtnrNo());
         BaseM base = mapper.selectBaseM(req, userDvCd);
         return getMtPerfAtcCds(userDvCd, base.qlfDvCd());
    }

    /**
     * M조직 미팅그리드 실적항목코드 가변목록 조회
     * @param userDvCd 사용자구분코드
     * @param qlfDvCd 자격구분코드
     * @return
     */
    @Transactional
    public List<WfebMogMetDvo> getMtPerfAtcCds(String userDvCd, String qlfDvCd) {
        List<WfebMogMetDvo> meetingPerfAtcCds = new ArrayList<>();
        /* 미팅관련 실적항목코드, 교육코드 설정*/
         if ("INDV".equals(userDvCd)) {
             switch (qlfDvCd) {
                 /* 프리매니저 */
                 case "2":
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("METG").perfAtcCd("W02P00001").perfColNm("FEE_PERF_ATC_VAL").eduCd("").build());
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("EDUC").perfAtcCd("W02P00115").perfColNm("FEE_PERF_ATC_VAL").eduCd("96").build());
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("EDUC").perfAtcCd("W02P00116").perfColNm("FEE_PERF_ATC_VAL").eduCd("129").build());
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("EDUC").perfAtcCd("W02P00117").perfColNm("FEE_PERF_ATC_VAL").eduCd("143").build());
                     break;
                 /* BS프리매니저 */
                 case "6":
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("METG").perfAtcCd("W02P00001").perfColNm("FEE_PERF_ATC_VAL").eduCd("").build());
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("EDUC").perfAtcCd("W02P00115").perfColNm("FEE_PERF_ATC_VAL").eduCd("96").build());
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("EDUC").perfAtcCd("W02P00116").perfColNm("FEE_PERF_ATC_VAL").eduCd("129").build());
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("EDUC").perfAtcCd("W02P00117").perfColNm("FEE_PERF_ATC_VAL").eduCd("143").build());
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("EDUC").perfAtcCd("W02P00127").perfColNm("FEE_PERF_ATC_VAL").eduCd("144").build());
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("EDUC").perfAtcCd("W02P00128").perfColNm("FEE_PERF_ATC_VAL").eduCd("145").build());
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("EDUC").perfAtcCd("W02P00129").perfColNm("FEE_PERF_ATC_VAL").eduCd("146").build());
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("EDUC").perfAtcCd("W02P00130").perfColNm("FEE_PERF_ATC_VAL").eduCd("147").build());
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("EDUC").perfAtcCd("W02P00131").perfColNm("FEE_PERF_ATC_VAL").eduCd("148").build());
                     break;
                 /* 웰스매니저 */
                 case "3":
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("METG").perfAtcCd("W02P00001").perfColNm("FEE_PERF_ATC_VAL").eduCd("").build());
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("EDUC").perfAtcCd("W02P00115").perfColNm("FEE_PERF_ATC_VAL").eduCd("96").build());
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("EDUC").perfAtcCd("W02P00116").perfColNm("FEE_PERF_ATC_VAL").eduCd("129").build());
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("EDUC").perfAtcCd("W02P00117").perfColNm("FEE_PERF_ATC_VAL").eduCd("143").build());
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("EDUC").perfAtcCd("W02P00124").perfColNm("FEE_PERF_ATC_VAL").eduCd("4").build());
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("EDUC").perfAtcCd("W02P00125").perfColNm("FEE_PERF_ATC_VAL").eduCd("119").build());
                     meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("EDUC").perfAtcCd("W02P00126").perfColNm("FEE_PERF_ATC_VAL").eduCd("140").build());
                     break;
             }
         } else {
             meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("EDUC").perfAtcCd("W02P00120").perfColNm("FEE_PERF_ATC_VAL").eduCd("135").build());
             meetingPerfAtcCds.add(WfebMogMetDvo.builder().type("PERF").perfAtcCd("W02P00111").perfColNm("PERF_VAL").eduCd("").build());
         }

         return meetingPerfAtcCds;
    }
}
