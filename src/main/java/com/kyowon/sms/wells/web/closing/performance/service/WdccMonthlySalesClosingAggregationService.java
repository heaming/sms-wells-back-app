package com.kyowon.sms.wells.web.closing.performance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kyowon.sms.common.web.bond.zcommon.constants.BnBondConst;
import com.kyowon.sms.wells.web.closing.performance.dvo.WdccMonthlySalesClosingAggregationDvo;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccMonthlySalesClosingAggregationMapper;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * WELLS 매출월마감내역집계 서비스
 * </pre>
 *
 * @author gugyeongu
 * @since 2023-10-13
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WdccMonthlySalesClosingAggregationService {
    private final WdccMonthlySalesClosingAggregationMapper mapper;

    /**
     * 매출 월마감 내역 집계(select -> merge(update,insert))
     * @param excnDt 실행일(ex>20231013)
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     */
    @Transactional
    public int createSalesMonthlyClosingDetailsTally(String excnDt, String cntrNo, String cntrSn) {
        Map<String, Object> parameterValues = new HashMap<>();
        parameterValues.put("excnDt", excnDt);
        parameterValues.put("excnType", "day");
        parameterValues.put("cntrNo", cntrNo);
        parameterValues.put("cntrSn", cntrSn);

        int result = 0;
        List<WdccMonthlySalesClosingAggregationDvo> dvos = mapper.selectSalesMonthEnd(parameterValues);
        for (WdccMonthlySalesClosingAggregationDvo dvo : dvos) {
            result = mapper.mergeMonthlySalesClosingAggregation(dvo);
            BizAssert.isTrue(result == 1, BnBondConst.MSG_ALT_SVE_ERR);
        }
        return result;
    }

}
