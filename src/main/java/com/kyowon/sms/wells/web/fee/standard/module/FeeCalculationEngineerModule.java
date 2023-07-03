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
     * 수당일괄계산 수당데이터 삭제 전처리 메소드
     * W060001, W060002, W060003, W060004, W060005, W060006, W060007, W060008, W060010, W060011, W060012 수수료 삭제
     *
     * 홈마스터 급지수수료 후처리 메소드
     */
    @FeeModuleMethodInfo(methodName = "수당일괄계산 수당 데이터 삭제 전처리", methodExplanation = "W060001 ~ W060012(W060009 제외) 수당 데이터 삭제")
    public void runAllowanceBulkDeletionPreProcess() {
        engineerCalculationMapper.deleteAllowances(baseYm, feeTcntDvCd);
    }

}
