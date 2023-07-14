package com.kyowon.sms.wells.web.fee.standard.module;


import com.kyowon.sms.common.web.fee.standard.annotation.FeeModuleInfo;
import com.kyowon.sms.common.web.fee.standard.annotation.FeeModuleMethodInfo;
import com.kyowon.sms.common.web.fee.standard.context.ApplicationContextHolder;
import com.kyowon.sms.common.web.fee.standard.module.FeeCalculationCommonModule;
import com.kyowon.sms.wells.web.fee.standard.mapper.WfeyMOrganizationCalculationMapper;

import static com.kyowon.sms.common.web.fee.standard.constant.FeFeeConst.SYSTEM_PACKAGE_WELLS;

@FeeModuleInfo(systemType = SYSTEM_PACKAGE_WELLS, moduleName = "M조직모듈", moduleExplanation = "M조직 특화수당계산 모듈")
public class FeeCalculationMOrganizationModule extends FeeCalculationCommonModule {

    protected WfeyMOrganizationCalculationMapper mOrganizationCalculationMapper;

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
    public FeeCalculationMOrganizationModule(String tenantId, String feeCd, String baseYm, String perfYm, String feeTcntDvCd, String perfAgrgCrtDvCd, String cntrPerfCrtDvCd) {
        super( tenantId, feeCd, baseYm, perfYm, feeTcntDvCd, perfAgrgCrtDvCd, cntrPerfCrtDvCd);
        mOrganizationCalculationMapper = ApplicationContextHolder.getBean(WfeyMOrganizationCalculationMapper.class);
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
    public FeeCalculationMOrganizationModule(String tenantId, String feeCd, String baseYm, String feeTcntDvCd, String perfAgrgCrtDvCd, String cntrPerfCrtDvCd) {
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
    public FeeCalculationMOrganizationModule(String tenantId, String feeCd, String baseYm, String feeTcntDvCd, String perfAgrgCrtDvCd) {
        this(tenantId, feeCd, baseYm, baseYm, feeTcntDvCd, perfAgrgCrtDvCd, null);
    }

    /**
     * BS관리(판매자)수수료(W020084) 후처리
     *
     * 웰스서비스실적내역 테이블에 수수료계산금액 업데이트
     *
     */
    @FeeModuleMethodInfo(methodName = "BS관리(판매자)수수료(W020084) 후처리", methodExplanation = "웰스서비스실적내역 테이블에 수수료계산금액 업데이트")
    public void runBsManagementFeeForSellerPostProcess() {
        mOrganizationCalculationMapper.updateBsManagementFeeForSellerCase1(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
        mOrganizationCalculationMapper.updateBsManagementFeeForSellerCase2(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
        mOrganizationCalculationMapper.updateBsManagementFeeForSellerCase3(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
        mOrganizationCalculationMapper.updateBsManagementFeeForSellerCase4(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
    }

    /**
     * WM급지(판매자)수수료(W020085) 후처리
     *
     * 웰스서비스실적내역 테이블에 급지수수료금액 업데이트
     *
     */
    @FeeModuleMethodInfo(methodName = "WM급지(판매자)수수료(W020085) 후처리", methodExplanation = "웰스서비스실적내역 테이블에 급지수수료금액 업데이트")
    public void runRegionLevelFeeForSellerPostProcess() {
        mOrganizationCalculationMapper.updateRegionLevelFeeForSeller(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
    }

    /**
     * BS관리(지점장)수수료(W020087) 후처리
     *
     * 웰스서비스실적내역 테이블에 수수료계산금액 업데이트
     *
     */
    @FeeModuleMethodInfo(methodName = "BS관리(지점장)수수료(W020087) 후처리", methodExplanation = "웰스서비스실적내역 테이블에 수수료계산금액 업데이트")
    public void runBsManagementFeeForBrmgrPostProcess() {
        mOrganizationCalculationMapper.updateBsManagementFeeForBrmgrCase1(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
        mOrganizationCalculationMapper.updateBsManagementFeeForBrmgrCase2(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
        mOrganizationCalculationMapper.updateBsManagementFeeForBrmgrCase3(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
        mOrganizationCalculationMapper.updateBsManagementFeeForBrmgrCase4(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
        mOrganizationCalculationMapper.updateBsManagementFeeForBrmgrCase5(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
        mOrganizationCalculationMapper.updateBsManagementFeeForBrmgrCase6(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
        mOrganizationCalculationMapper.updateBsManagementFeeForBrmgrCase7(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
        mOrganizationCalculationMapper.updateBsManagementFeeForBrmgrCase8(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
    }

    /**
     * WM급지(지점장)수수료(W020088) 후처리
     *
     * 웰스서비스실적내역 테이블에 급지수수료금액 업데이트
     *
     */
    @FeeModuleMethodInfo(methodName = "WM급지(지점장)수수료(W020085) 후처리", methodExplanation = "웰스서비스실적내역 테이블에 급지수수료금액 업데이트")
    public void runRegionLevelFeeForBrmgrPostProcess() {
        mOrganizationCalculationMapper.updateUnderPerf120RegionLevelFeeForBrmgr(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
        mOrganizationCalculationMapper.updateOverPerf120RegionLevelFeeForBrmgr(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
    }

}
