package com.kyowon.sms.wells.web.fee.standard.module;


import com.kyowon.sms.common.web.fee.standard.annotation.FeeModuleInfo;
import com.kyowon.sms.common.web.fee.standard.annotation.FeeModuleMethodInfo;
import com.kyowon.sms.common.web.fee.standard.context.ApplicationContextHolder;
import com.kyowon.sms.common.web.fee.standard.module.FeeCalculationCommonModule;
import com.kyowon.sms.wells.web.fee.standard.mapper.WfeyEngineerCalculationMapper;

import static com.kyowon.sms.common.web.fee.standard.constant.FeFeeConst.SYSTEM_PACKAGE_WELLS;

@FeeModuleInfo(systemType = SYSTEM_PACKAGE_WELLS, moduleName = "엔지니어모듈", moduleExplanation = "엔지니어 특화수당계산 모듈")
public class FeeCalculationEngineerModule extends FeeCalculationCommonModule {

    protected WfeyEngineerCalculationMapper engineerCalculationMapper;

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
    public FeeCalculationEngineerModule(String tenantId, String feeCd, String baseYm, String perfYm, String feeTcntDvCd, String perfAgrgCrtDvCd, String cntrPerfCrtDvCd) {
        super( tenantId, feeCd, baseYm, perfYm, feeTcntDvCd, perfAgrgCrtDvCd, cntrPerfCrtDvCd);
        engineerCalculationMapper = ApplicationContextHolder.getBean(WfeyEngineerCalculationMapper.class);
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
    public FeeCalculationEngineerModule(String tenantId, String feeCd, String baseYm, String feeTcntDvCd, String perfAgrgCrtDvCd, String cntrPerfCrtDvCd) {
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
    public FeeCalculationEngineerModule(String tenantId, String feeCd, String baseYm, String feeTcntDvCd, String perfAgrgCrtDvCd) {
        this(tenantId, feeCd, baseYm, baseYm, feeTcntDvCd, perfAgrgCrtDvCd, null);
    }

    /**
     * 수당일괄계산 사용자정의 처리 메소드
     * W060001, W060002, W060003, W060004, W060005, W060006, W060007, W060008, W060010, W060011, W060012 수수료 삭제
     *
     */
    @FeeModuleMethodInfo(methodName = "수당일괄계산 사용자정의 처리", methodExplanation = "W060001 ~ W060012(W060009 제외) 수당일괄계산 사용자정의 처리")
    public void runAllowanceBulkCalculation() {
        engineerCalculationMapper.deleteEngineerAllowances(baseYm, feeTcntDvCd, "DATA");
        engineerCalculationMapper.deleteEngineerAllowances(baseYm, feeTcntDvCd, "HISTORY");
        engineerCalculationMapper.insertEngineerAllowances(baseYm, feeStandard.basic().coCd(), feeStandard.basic().fnlFeeYn(), feeTcntDvCd);
        engineerCalculationMapper.insertEngineerAllowanceHistories(baseYm, feeTcntDvCd);
    }

}
