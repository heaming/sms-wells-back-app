package com.kyowon.sms.wells.web.fee.calculation.service;

import com.kyowon.sms.common.web.fee.calculation.service.ZfebRedfAdsbFeeCalculationService;
import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebAgainDisbursementFeeMapper;
import com.sds.sflex.common.common.dvo.CodeDetailDvo;
import com.sds.sflex.common.common.service.CodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kyowon.sms.common.web.fee.common.dvo.FeeIdGenerator.getFeeRedemptionDetailIdSql;
import static com.kyowon.sms.common.web.fee.common.dvo.FeeIdGenerator.getRedfAdsbBaseIdPrefix;
import static com.kyowon.sms.common.web.fee.standard.constant.FeFeeConst.AGAIN_DISBURSEMENT;

/**
 * <pre>
 * 재지급 계산서비스
 * </pre>
 *
 * @author jingun.jung
 * @since 2023-06-01
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WfebAgainDisbursementFeeService {

    private final CodeService codeService;

    private final ZfebRedfAdsbFeeCalculationService redfAdsbFeeCalculationService;

    private final WfebAgainDisbursementFeeMapper againDisbursementFeeMapper;

    @Value("${tenant.defaultId:TNT_BASE}")
    String defaultTenantId;

    /**
     * 재지급수수료 생성
     * <p>
     * 1. 재지급수수료 계산 실적 계산
     * 2. 취소재지급 서비스 호출
     * 3. 연체재지급 서비스 호출
     *
     * @param baseYm
     * @param cntrPerfCrtDvCd
     */
    @Transactional
    public void saveAgainDisbursementOfFees(String baseYm, String cntrPerfCrtDvCd) {
        /* 계약실적생성구분코드로 처리해야 하는 수수료계산단위유형코드 목록, 실적생성생성구분코드 목록 조회 */
        CodeDetailDvo code = codeService.getCodeDetailByPk("CNTR_PERF_CRT_DV_CD", cntrPerfCrtDvCd);
        String ogTpCd = code.getUserDfn03();

        /* 연체재지급 서비스 호출 */
        saveDlqAgainDisbursementOfFees(baseYm, ogTpCd, cntrPerfCrtDvCd);
    }

    /**
     * 연체재지급 생성
     * <p>
     * 1. 기존생성 데이터 삭제 (재생성을 감안한 작업)
     * 2. 계약별로 생성된 연체재지급을 파트너단위로 데이터 생성
     * 3. 파트너단위 생성된 데이터의 ID를 계약별에 업데이트
     *
     * @param baseYm
     * @return
     */
    @Transactional
    public Integer saveDlqAgainDisbursementOfFees(String baseYm, String ogTpCd, String cntrPerfCrtDvCd) {
        Integer insertCount = 0;
        String redfAdsbDvCd = "03";
        String redfAdsbTpCd = "0302";
        /* 기생성했던 연체재지급 데이터 삭제 */
        redfAdsbFeeCalculationService.removeRedfAdsbData(baseYm, ogTpCd, redfAdsbDvCd, redfAdsbTpCd, "Y");

        switch (ogTpCd) {
            case "W01":
                /* 상조 연체 재지급 */
                insertCount = againDisbursementFeeMapper.insertLifeContractDlqAdsbsForPog(baseYm, ogTpCd, cntrPerfCrtDvCd, getFeeRedemptionDetailIdSql(defaultTenantId, AGAIN_DISBURSEMENT, baseYm, redfAdsbTpCd));
                break;
            case "W02":
                /* 계약별 연체재지급 데이터 생성 */
                insertCount = againDisbursementFeeMapper.insertContractDlqAdsbsForMog(baseYm, ogTpCd, cntrPerfCrtDvCd, getFeeRedemptionDetailIdSql(defaultTenantId, AGAIN_DISBURSEMENT, baseYm, redfAdsbTpCd));
                break;
            case "W05":
                /* 계약별 기본수수료 연체재지급 데이터 생성 */
                insertCount = againDisbursementFeeMapper.insertBaseContractDlqAdsbs(baseYm, ogTpCd, cntrPerfCrtDvCd, getFeeRedemptionDetailIdSql(defaultTenantId, AGAIN_DISBURSEMENT, baseYm, redfAdsbTpCd));
                break;
        }
        /* 파트너별 연체재지급 데이터 생성 */
        if (insertCount > 0) {
            redfAdsbFeeCalculationService.saveRedfAdsbCntrToBasWithHist(baseYm, ogTpCd, redfAdsbDvCd, redfAdsbTpCd);
        }
        return insertCount;
    }

}
