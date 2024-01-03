package com.kyowon.sms.wells.web.fee.calculation.service;

import com.kyowon.sms.common.web.fee.calculation.service.ZfebRedfAdsbFeeCalculationService;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static com.kyowon.sms.common.web.fee.common.dvo.FeeIdGenerator.getFeeRedemptionDetailIdSql;

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
    private final ZfebRedfAdsbFeeCalculationService redfAdsbFeeCalculationService;

    private final WfebRedemptionFeeMapper redemptionFeeMapper;

    @Value("${tenant.defaultId:TNT_BASE}")
    String defaultTenantId;

    /**
     * 되물림 생성(집계 + 취소 + 연체)
     * <p>
     * 1. 되물림데이터 집계 서비스 호출
     * 2. 취소되물림 서비스 호출
     * 3. 연체되물림 서비스 호출
     *
     * @param baseYm
     * @param cntrPerfCrtDvCd
     * @return
     */
    @Transactional
    public void saveRedemptionOfFees(String baseYm, String cntrPerfCrtDvCd) {
        /* 계약실적생성구분코드로 처리해야 하는 수수료계산단위유형코드 목록, 실적생성생성구분코드 목록 조회 */
        CodeDetailDvo code = codeService.getCodeDetails("PERF_AGRG_CRT_DV_CD").stream().filter(item -> cntrPerfCrtDvCd.equals(item.getUserDfn03())).findFirst().orElse(null);

        /* 계약실적생성구분코드값이 잘못된 경우 오류 발생 */
        BizAssert.isTrue(ObjectUtils.isNotEmpty(code), "");

        String ogTpCd = code.getUserDfn02();
        String perfAgrgCrtDvCd = code.getCodeValidityValue();

        /* 되물림 취소 실적집계 서비스 호출 */
        redemptionPerfService.aggregateRedemptionOfFeePerformance(baseYm, ogTpCd, perfAgrgCrtDvCd, cntrPerfCrtDvCd);

        /* 취소되물림 서비스 호출 */
        redfAdsbFeeCalculationService.saveRedfAdsbCalculation(baseYm, ogTpCd, perfAgrgCrtDvCd, cntrPerfCrtDvCd);

        /* 연체되물림 서비스 호출 */
        if(Arrays.asList("W01", "W02", "W05").contains(ogTpCd)) {
            saveDlqRedemptionOfFees(baseYm, ogTpCd, cntrPerfCrtDvCd);
        }
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
    @Transactional
    public Integer saveDlqRedemptionOfFees(String baseYm, String ogTpCd, String cntrPerfCrtDvCd) {
        int insertCount = 0;
        String redfAdsbDvCd = "02";
        String redfAdsbTpCd = "0203";
        /* 기생성했던 연체되물림 데이터 삭제 */
        redfAdsbFeeCalculationService.removeRedfAdsbData(baseYm, ogTpCd, redfAdsbDvCd, redfAdsbTpCd, "Y");

        switch (ogTpCd) {
            case "W01":
                /* P조직 상조 연체되물림 생성 */
                insertCount = redemptionFeeMapper.insertLifeContractRedf(baseYm, ogTpCd, getFeeRedemptionDetailIdSql(defaultTenantId, redfAdsbDvCd, baseYm, redfAdsbTpCd));
                break;
            case "W02":
                /* 계약별 연체되물림 데이터 생성 */
                insertCount = redemptionFeeMapper.insertContractDlqRedf(baseYm, cntrPerfCrtDvCd, getFeeRedemptionDetailIdSql(defaultTenantId, redfAdsbDvCd, baseYm, redfAdsbTpCd));
                break;
            case "W05":
                /* 계약별 기본수수료 연체되물림 데이터 생성 */
                insertCount = redemptionFeeMapper.insertBaseContractDlqRedf(baseYm, cntrPerfCrtDvCd, getFeeRedemptionDetailIdSql(defaultTenantId, redfAdsbDvCd, baseYm, redfAdsbTpCd), ogTpCd);
                break;}

        /* 파트너별 연체되물림 데이터 생성 */
        if (insertCount > 0) {
            redfAdsbFeeCalculationService.saveRedfAdsbCntrToBasWithHist(baseYm, ogTpCd, redfAdsbDvCd, redfAdsbTpCd);
        }
        return insertCount;
    }
}
