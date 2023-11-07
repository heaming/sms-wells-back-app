package com.kyowon.sms.wells.web.fee.calculation.service;

import com.kyowon.sms.common.web.fee.calculation.service.ZfebRedemptionFeeCalculationService;
import com.kyowon.sms.wells.web.fee.aggregate.service.WfeaRedemptionPerfService;
import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebRedemptionFeeMapper;
import com.sds.sflex.common.common.dvo.CodeDetailDvo;
import com.sds.sflex.common.common.service.CodeService;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.kyowon.sms.common.web.fee.common.dvo.FeeIdGenerator.getFeeRedemptionDetailIdSql;
import static com.kyowon.sms.common.web.fee.common.dvo.FeeIdGenerator.getRedfAdsbBaseIdPrefix;
import static com.kyowon.sms.common.web.fee.standard.constant.FeFeeConst.REDEMPTION_OF_FEE;

/**
 * <pre>
 * 되물림 계산서비스
 * </pre>
 *
 * @author jingun.jung
 * @since 2023-06-01
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WfebRedemptionFeeService {

    private final CodeService codeService;

    private final WfeaRedemptionPerfService redemptionPerfService;
    private final ZfebRedemptionFeeCalculationService redfOfFeeCalculationService;

    private final WfebRedemptionFeeMapper redemptionFeeMapper;

    @Value("${tenant.defaultId:TNT_BASE}")
    String defaultTenantId;

    /**
     * 되물림 생성(집계 + 취소 + 연체)
     *
     * 1. 되물림데이터 집계 서비스 호출
     * 2. 취소되물림 서비스 호출
     * 3. 연체되물림 서비스 호출
     * @param baseYm
     * @param cntrPerfCrtDvCd
     * @return
     */
    public void saveRedemptionOfFees(String baseYm, String cntrPerfCrtDvCd) {
        /* 계약실적생성구분코드로 처리해야 하는 수수료계산단위유형코드 목록, 실적생성생성구분코드 목록 조회 */
        CodeDetailDvo code = codeService.getCodeDetails("PERF_AGRG_CRT_DV_CD").stream().filter(item->cntrPerfCrtDvCd.equals(item.getUserDfn03())).findFirst().orElse(null);

        /* 계약실적생성구분코드값이 잘못된 경우 오류 발생 */
        BizAssert.isTrue(ObjectUtils.isNotEmpty(code), "");

        String ogTpCd = code.getUserDfn02();
        String perfAgrgCrtDvCd = code.getCodeValidityValue();

        /* 되물림 실적집계 서비스 호출 */
         redemptionPerfService.aggregateRedemptionOfFeePerformance(baseYm, ogTpCd, perfAgrgCrtDvCd, cntrPerfCrtDvCd);

        /* 취소되물림 서비스 호출 */
        redfOfFeeCalculationService.saveRedemptionOfFeeCalculation(baseYm, ogTpCd, perfAgrgCrtDvCd, cntrPerfCrtDvCd);

        /* 연체되물림 서비스 호출 */
        saveDlqRedemptionOfFees(baseYm, ogTpCd, cntrPerfCrtDvCd);
    }

    /**
     * 연체되물림 생성
     * <p>
     * 1. 기존생성 데이터 삭제 (재생성을 감안한 작업)
     * 2. 기준년월의 계약실적생성구분코드에 해당하는 계약으로
     * 지급된 비례수수료, 판매미팅수수료, 지구성과수수료의 최초수수료율 * 되물림대상금액 = 연체되물림금액을 계산하여 계약별로 연체되물림 생성
     * 3. 계약별로 생성된 연체되물림을 파트너단위로 데이터 생성
     * 4. 파트너단위 생성된 데이터의 ID를 계약별에 업데이트
     *
     * @param baseYm
     * @return
     */
    public Integer saveDlqRedemptionOfFees(String baseYm, String ogTpCd, String cntrPerfCrtDvCd) {
        int insertCount = 0;
        /* 기생성했던 연체되물림 데이터 삭제 */
        redemptionFeeMapper.deleteCommonDlqRedemptionOfFees(baseYm, ogTpCd, "TB_FEDD_FEE_REDF_ADSB_BAS");
        redemptionFeeMapper.deleteCommonDlqRedemptionOfFees(baseYm, ogTpCd, "TB_FEDD_FEE_REDF_ADSB_DTL");
        redemptionFeeMapper.deleteCommonDlqRedemptionOfFees(baseYm, ogTpCd, "TB_FEDD_FEE_REDF_ADSB_HIST");
        redemptionFeeMapper.deleteCommonDlqRedemptionOfFees(baseYm, ogTpCd, "TB_FEDD_FEE_REDF_ADSB_DTL_HIST");

        switch(ogTpCd) {
            case "W01":
                /* */
                break;
            case "W02":
                /* 계약별 연체되물림 데이터 생성 */
            insertCount = redemptionFeeMapper.insertContractDlqRedemptionOfFees(baseYm, cntrPerfCrtDvCd, getFeeRedemptionDetailIdSql(defaultTenantId, "REDF", baseYm, "0203"));
                break;
        }

        /* 파트너별 연체되물림 데이터 생성 */
        if (insertCount > 0) {
            /* 파트너별 데이터 생성 */
            redemptionFeeMapper.insertDlqRedemptionOfFees(baseYm, cntrPerfCrtDvCd, getRedfAdsbBaseIdPrefix(defaultTenantId, REDEMPTION_OF_FEE, baseYm, "0203"));
            /* 계약별 데이터에 부모데이터 ID 업데이트 */
            redemptionFeeMapper.updatePrtnrIdCntrDlqRedemptionOfFees(baseYm, cntrPerfCrtDvCd);
            /* 이력데이터 생성 */
            redemptionFeeMapper.insertContractDlqRedemptionOfFeeHistories(baseYm, cntrPerfCrtDvCd);
            redemptionFeeMapper.insertPartnerDlqRedemptionOfFeeHistories(baseYm, cntrPerfCrtDvCd);
        }
        return insertCount;
    }

}
