package com.kyowon.sms.wells.web.fee.standard.module;


import com.kyowon.sms.common.web.fee.standard.annotation.FeeModuleInfo;
import com.kyowon.sms.common.web.fee.standard.annotation.FeeModuleMethodInfo;
import com.kyowon.sms.common.web.fee.standard.context.ApplicationContextHolder;
import com.kyowon.sms.common.web.fee.standard.module.FeeCalculationCommonModule;
import com.kyowon.sms.wells.web.fee.standard.mapper.WfeyHomeMasterCalculationMapper;

import static com.kyowon.sms.common.web.fee.standard.constant.FeFeeConst.FeeCalculationTypeCode.FEE_CALCULATION;
import static com.kyowon.sms.common.web.fee.standard.constant.FeFeeConst.SYSTEM_PACKAGE_WELLS;

@FeeModuleInfo(systemType = SYSTEM_PACKAGE_WELLS, moduleName = "홈마스터모듈", moduleExplanation = "홈마스터 특화수수료계산 모듈")
public class FeeCalculationHomeMasterModule extends FeeCalculationCommonModule {

    protected WfeyHomeMasterCalculationMapper homeMasterCalculationMapper;

    /**
     * 생성자
     *
     * @param tenantId
     * @param feeCd
     * @param baseYm
     * @param perfYm
     * @param feeTcntDvCd
     * @param perfAgrgCrtDvCd
     * @param cntrPerfCrtDvCd
     */
    public FeeCalculationHomeMasterModule(String tenantId, String feeCd, String baseYm, String perfYm, String feeTcntDvCd, String perfAgrgCrtDvCd, String cntrPerfCrtDvCd) {
        super( tenantId, feeCd, baseYm, perfYm, feeTcntDvCd, perfAgrgCrtDvCd, cntrPerfCrtDvCd);
        homeMasterCalculationMapper = ApplicationContextHolder.getBean(WfeyHomeMasterCalculationMapper.class);
    }
        /**
     * 생성자
     *
     * @param tenantId
     * @param feeCd
     * @param baseYm
     * @param feeTcntDvCd
     * @param perfAgrgCrtDvCd
     * @param cntrPerfCrtDvCd
     */
    public FeeCalculationHomeMasterModule(String tenantId, String feeCd, String baseYm, String feeTcntDvCd, String perfAgrgCrtDvCd, String cntrPerfCrtDvCd) {
        this(tenantId, feeCd, baseYm, baseYm, feeTcntDvCd, perfAgrgCrtDvCd, cntrPerfCrtDvCd);
    }

        /**
     * 생성자
     *
     * @param tenantId
     * @param feeCd
     * @param baseYm
     * @param feeTcntDvCd
     * @param perfAgrgCrtDvCd
     */
    public FeeCalculationHomeMasterModule(String tenantId, String feeCd, String baseYm, String feeTcntDvCd, String perfAgrgCrtDvCd) {
        this(tenantId, feeCd, baseYm, baseYm, feeTcntDvCd, perfAgrgCrtDvCd, null);
    }

    /**
     * 급지수수료(W030012) 후처리
     *
     * 홈마스터 급지수수료 후처리 메소드
     */
    @FeeModuleMethodInfo(methodName = "급지수수료(W030012) 후처리", methodExplanation = "서비스실적내역 테이블에 데이터 업데이트")
    public void runRglvlFeePostProcess() {
        homeMasterCalculationMapper.updateRglvlFeePostProcess(baseYm, feeStandard.basic().ogTpCd(), feeCd, feeTcntDvCd);
    }

    /**
     * 급지수수료(W030012) 후처리
     *
     * 홈마스터 급지수수료 후처리 메소드
     */
    @FeeModuleMethodInfo(methodName = "서비스현장수수료(W030007) 후처리", methodExplanation = "서비스실적내역 테이블에 데이터 업데이트")
    public void runSvSiteFeePostProcess() {
        homeMasterCalculationMapper.updateSvSiteFeePostProcess(baseYm, feeStandard.basic().ogTpCd(), feeCd, feeTcntDvCd);
    }

    /**
     * 교육수수료(W030011)계산 1단계
     *
     * 2회차 지급월에 퇴직여부에 따라 미지급(삭제)으로 상태 변경
     *
     */
    @FeeModuleMethodInfo(methodName = "교육수수료(W030011)계산 1단계", methodExplanation = "2회차 지급월에 퇴직여부에 따라 미지급(삭제)으로 상태 변경")
    public void runEducationFeeStep1() {
        homeMasterCalculationMapper.updateResignPartnerEducationFees(baseYm, feeStandard.basic().ogTpCd(), feeTcntDvCd);
        homeMasterCalculationMapper.insertResignPartnerEducationFeeHistories(baseYm, feeStandard.basic().ogTpCd(), feeTcntDvCd);
        homeMasterCalculationMapper.updateResignPartnerEducationFeePlan(baseYm, feeStandard.basic().ogTpCd(), feeCd, feeTcntDvCd);
        homeMasterCalculationMapper.insertResignPartnerEducationFeePlanHistories(baseYm, feeStandard.basic().ogTpCd(), feeCd, feeTcntDvCd);
    }

    /**
     * 교육수수료(W030011)계산 2단계
     *
     * 기준년월 수수료 데이터 생성 (수수료기본 + 수수료분할지급계획)
     *
     */
    @FeeModuleMethodInfo(methodName = "교육수수료(W030011)계산 2단계", methodExplanation = "기준년월 수수료 데이터 생성 (수수료기본 + 수수료분할지급계획)")
    public void runEducationFeeStep2() {
        homeMasterCalculationMapper.insertEducationFees(baseYm, feeStandard.basic().ogTpCd(), feeTcntDvCd, feeCd, feeStandard.basic().dtaCrtFeeCd(), feeStandard.basic().coCd(), feeStandard.basic().fnlFeeYn());
        feeCalculationMapper.insertCalcBasHistories(baseYm, feeCd, feeTcntDvCd, FEE_CALCULATION.getCode(), null, null);
        homeMasterCalculationMapper.insertEducationFeePlan(baseYm, feeStandard.basic().ogTpCd(), feeTcntDvCd, feeCd, feeStandard.basic().dtaCrtFeeCd(), feeStandard.basic().coCd(), feeStandard.basic().fnlFeeYn());
        feeCalculationMapper.insertPatDsbPlanHitories(baseYm, feeCd, feeTcntDvCd, FEE_CALCULATION.getCode(), null, null);
    }
}
