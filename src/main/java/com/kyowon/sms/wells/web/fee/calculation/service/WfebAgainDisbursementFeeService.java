package com.kyowon.sms.wells.web.fee.calculation.service;

import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebAgainDisbursementFeeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    private final WfebAgainDisbursementFeeMapper againDisbursementFeeMapper;

    @Value("${tenant.defaultId:TNT_BASE}")
    String defaultTenantId;

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
    public Integer saveDlqAgainDisbursementOfFees(String baseYm, String cntrPerfCrtDvCd) {
        Integer insertCount;
        /* 기생성했던 연체재지급 데이터 삭제 */
        againDisbursementFeeMapper.deleteCommonDlqAdsbs(baseYm, cntrPerfCrtDvCd, "TB_FEDD_FEE_REDF_ADSB_BAS");
        againDisbursementFeeMapper.deleteCommonDlqAdsbs(baseYm, cntrPerfCrtDvCd, "TB_FEDD_FEE_REDF_ADSB_DTL");
        againDisbursementFeeMapper.deleteCommonDlqAdsbs(baseYm, cntrPerfCrtDvCd, "TB_FEDD_FEE_REDF_ADSB_HIST");
        againDisbursementFeeMapper.deleteCommonDlqAdsbs(baseYm, cntrPerfCrtDvCd, "TB_FEDD_FEE_REDF_ADSB_DTL_HIST");
        /* 계약별 연체재지급 데이터 생성 */
        insertCount = againDisbursementFeeMapper.insertContractDlqAdsbs(baseYm, cntrPerfCrtDvCd, getFeeRedemptionDetailIdSql(defaultTenantId, AGAIN_DISBURSEMENT, baseYm, "0302"));
        /* 파트너별 연체재지급 데이터 생성 */
        if (insertCount > 0) {
            /* 파트너별 데이터 생성 */
            againDisbursementFeeMapper.insertDlqAdsbs(baseYm, cntrPerfCrtDvCd, getRedfAdsbBaseIdPrefix(defaultTenantId, AGAIN_DISBURSEMENT, baseYm, "0302"));
            /* 계약별 데이터에 부모데이터 ID 업데이트 */
            againDisbursementFeeMapper.updatePartnerIdCntrDlqAdsbs(baseYm, cntrPerfCrtDvCd);
            /* 이력데이터 생성 */
            againDisbursementFeeMapper.insertContractDlqAdsbHistories(baseYm, cntrPerfCrtDvCd);
            againDisbursementFeeMapper.insertPartnerDlqAdsbHistories(baseYm, cntrPerfCrtDvCd);
        }
        return insertCount;
    }

}
