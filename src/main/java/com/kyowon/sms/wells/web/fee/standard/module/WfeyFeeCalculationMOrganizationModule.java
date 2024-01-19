package com.kyowon.sms.wells.web.fee.standard.module;


import com.kyowon.sms.common.web.fee.standard.annotation.FeeModuleInfo;
import com.kyowon.sms.common.web.fee.standard.annotation.FeeModuleMethodInfo;
import com.kyowon.sms.common.web.fee.standard.context.ApplicationContextHolder;
import com.kyowon.sms.common.web.fee.standard.dto.ZfeyFeeStandardDto;
import com.kyowon.sms.common.web.fee.standard.dvo.ZfeyFeeStandardDvo;
import com.kyowon.sms.common.web.fee.standard.module.ZfeyFeeCalculationCommonModule;
import com.kyowon.sms.common.web.fee.standard.service.ZfeyFeeStandardSqlService;
import com.kyowon.sms.common.web.fee.standard.util.FeFeeUtil;
import com.kyowon.sms.wells.web.fee.standard.mapper.WfeyHomeMasterCalculationMapper;
import com.kyowon.sms.wells.web.fee.standard.mapper.WfeyMOrganizationCalculationMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static com.kyowon.sms.common.web.fee.standard.constant.FeFeeConst.FeeCalculationUnit.PARTNER_UNIT;
import static com.kyowon.sms.common.web.fee.standard.constant.FeFeeConst.REDEMPTION_OF_FEE;
import static com.kyowon.sms.common.web.fee.standard.constant.FeFeeConst.RedemptionDivideCode.*;
import static com.kyowon.sms.common.web.fee.standard.constant.FeFeeConst.SYSTEM_PACKAGE_WELLS;

@FeeModuleInfo(systemType = SYSTEM_PACKAGE_WELLS, moduleName = "M조직모듈", moduleExplanation = "M조직 특화수당계산 모듈")
public class WfeyFeeCalculationMOrganizationModule extends ZfeyFeeCalculationCommonModule {

    protected ZfeyFeeStandardSqlService feeStandardSqlService;
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
    public WfeyFeeCalculationMOrganizationModule(ZfeyFeeStandardDto.SearchFeeStandardDetailRes feeStandardDetail, String tenantId, String feeCd, String baseYm, String perfYm, String feeTcntDvCd, String perfAgrgCrtDvCd, String cntrPerfCrtDvCd) {
        super(feeStandardDetail, tenantId, feeCd, baseYm, perfYm, feeTcntDvCd, perfAgrgCrtDvCd, cntrPerfCrtDvCd);
        mOrganizationCalculationMapper = ApplicationContextHolder.getBean(WfeyMOrganizationCalculationMapper.class);
        feeStandardSqlService = ApplicationContextHolder.getBean(ZfeyFeeStandardSqlService.class);
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
    public WfeyFeeCalculationMOrganizationModule(ZfeyFeeStandardDto.SearchFeeStandardDetailRes feeStandardDetail, String tenantId, String feeCd, String baseYm, String feeTcntDvCd, String perfAgrgCrtDvCd, String cntrPerfCrtDvCd) {
        this(feeStandardDetail, tenantId, feeCd, baseYm, baseYm, feeTcntDvCd, perfAgrgCrtDvCd, cntrPerfCrtDvCd);
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
    public WfeyFeeCalculationMOrganizationModule(ZfeyFeeStandardDto.SearchFeeStandardDetailRes feeStandardDetail, String tenantId, String feeCd, String baseYm, String feeTcntDvCd, String perfAgrgCrtDvCd) {
        this(feeStandardDetail, tenantId, feeCd, baseYm, baseYm, feeTcntDvCd, perfAgrgCrtDvCd, null);
    }

    /**
     * 생성자
     *
     * @param tenantId
     * @param baseYm
     * @param mmAcuPerfAgrgCrtDvCd
     * @param prtnrNo
     */
    public WfeyFeeCalculationMOrganizationModule(
        ZfeyFeeStandardDto.SearchFeeStandardDetailRes feeStandardDetail, String tenantId, String baseYm, String mmAcuPerfAgrgCrtDvCd, String prtnrNo
    ) {
        super(feeStandardDetail, tenantId, baseYm, mmAcuPerfAgrgCrtDvCd, prtnrNo);
        mOrganizationCalculationMapper = ApplicationContextHolder.getBean(WfeyMOrganizationCalculationMapper.class);
        feeStandardSqlService = ApplicationContextHolder.getBean(ZfeyFeeStandardSqlService.class);
    }

    /**
     * BS관리(판매자)수수료(W020084) 후처리
     * <p>
     * 웰스서비스실적내역 테이블에 수수료계산금액 업데이트
     */
    @FeeModuleMethodInfo(methodTypeCode =  "01", methodName = "BS관리(판매자)수수료(W020084) 후처리", methodExplanation = "웰스서비스실적내역 테이블에 수수료계산금액 업데이트")
    public void runBsManagementFeeForSellerPostProcess() {
        mOrganizationCalculationMapper.updateBsManagementFeeForSellerCase1(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
        mOrganizationCalculationMapper.updateBsManagementFeeForSellerCase2(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
        mOrganizationCalculationMapper.updateBsManagementFeeForSellerCase3(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
        mOrganizationCalculationMapper.updateBsManagementFeeForSellerCase4(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
    }

    /**
     * WM급지(판매자)수수료(W020085) 후처리
     * <p>
     * 웰스서비스실적내역 테이블에 급지수수료금액 업데이트
     */
    @FeeModuleMethodInfo(methodTypeCode =  "01", methodName = "WM급지(판매자)수수료(W020085) 후처리", methodExplanation = "웰스서비스실적내역 테이블에 급지수수료금액 업데이트")
    public void runRegionLevelFeeForSellerPostProcess() {
        mOrganizationCalculationMapper.updateRegionLevelFeeForSeller(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
    }

    /**
     * BS관리(지점장)수수료(W020087) 후처리
     * <p>
     * 웰스서비스실적내역 테이블에 수수료계산금액 업데이트
     */
    @FeeModuleMethodInfo(methodTypeCode =  "01", methodName = "BS관리(지점장)수수료(W020087) 후처리", methodExplanation = "웰스서비스실적내역 테이블에 수수료계산금액 업데이트")
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
     * <p>
     * 웰스서비스실적내역 테이블에 급지수수료금액 업데이트
     */
    @FeeModuleMethodInfo(methodTypeCode =  "01", methodName = "WM급지(지점장)수수료(W020088) 후처리", methodExplanation = "웰스서비스실적내역 테이블에 급지수수료금액 업데이트")
    public void runRegionLevelFeeForBrmgrPostProcess() {
        mOrganizationCalculationMapper.updateUnderPerf120RegionLevelFeeForBrmgr(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
        mOrganizationCalculationMapper.updateOverPerf120RegionLevelFeeForBrmgr(baseYm, feeCd, feeTcntDvCd, basic.ogTpCd());
    }

    /**
     * M조직 특화 정액 사용자정의되물림 계산
     * <p>
     * 계약단위로 생성하여 파트너단위로 SUM하는 되물림 금액 계산
     */
    @FeeModuleMethodInfo(methodTypeCode =  "02", methodName = "M조직 특화 정액 사용자정의되물림 계산", methodExplanation = "M조직 특화 정액 사용자정의되물림 계산 생성")
    public void runFixedAmountRedemptionCalculate() {
        /* 사용자정의되물림인지 확인 */
        BizAssert.isTrue(USER_DEFINE_REDF.getCode().equals(basic.redfDvCd()), "MSG_ALT_WRONG_REDF_TP"); /* 되물림유형이 맞지 않습니다. */
        /* 되물림 실적변수가 1개가 아니면 오류 */
        BizAssert.isTrue(feeStandard.redfPerformVarbs().size() == 1, "MSG_ALT_WRONG_PERF_VARB_CNT"); /* 실적변수 개수가 맞지 않습니다. */
        /* 실적변수는 테이블은 TB_FEAM_NTORP_PERF_MM_CL, TB_FEAM_NTORP_CNTR_MM_CL 이어야 함 */
        BizAssert.isTrue(Arrays.asList("TB_FEAM_NTORP_PERF_MM_CL", "TB_FEAM_NTORP_CNTR_MM_CL").contains(feeStandard.redfPerformVarbs().get(0).perfVarbTblCd()), "MSG_ALT_WORNG_PERF_VARB_TBL"); /* 실적변수 테이블명이 맞지 않습니다. */

        runDeletionRedfData();

        int insertCount = 0;

        if (PARTNER_UNIT.getCode().equals(basic.feeCalcUnitCd())) {
            /* 계산식 생성 */
            List<ZfeyFeeStandardDvo.ZfeyPerformVarbsDvo> redfPerformVarbs = feeStandardSqlService.convertPerformanceVariableDtoToZfeyPerformVarbsDvo(feeStandard.redfPerformVarbs());
            List<ZfeyFeeStandardDvo.ZfeyConditionVarbsDvo> redfConvertedConditionVarbs = FeFeeUtil.convertConditionVariable(feeStandard.redfCondVarbs(), redfPerformVarbs);
            String calcExpr = FeFeeUtil.getFeeAmtProcString(basic.feeAmtProcsUnitCd(), basic.feeAmtDigtCd(), FeFeeUtil.getFeeCalculateFomula(feeStandard.basic().redfCalfCn(), redfPerformVarbs, redfConvertedConditionVarbs), "SUM");

            insertCount = mOrganizationCalculationMapper.insertFxamRedfPartnerData(baseYm, feeCd, basic.dtaCrtFeeCd(), perfAgrgCrtDvCd, basic.apyStrtYm(), basic.apyEndYm(), feeStandard.redfPerformVarbs().get(0).perfVarbColVal(), feeStandard.redfPerformVarbs().get(0).indvPerfYn(), calcExpr);
        }

        /* 생성된 데이터가있으면 이력 생성 */
        if (insertCount > 0) {
            /* 되물림 이력 생성 */
            runInsertionRedfDataHistoriesStep();
        }
    }
}
