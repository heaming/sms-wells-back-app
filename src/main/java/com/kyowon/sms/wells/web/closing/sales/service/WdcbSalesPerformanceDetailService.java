package com.kyowon.sms.wells.web.closing.sales.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchRegularRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbSalesPerformanceDetailMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 매출 실적 현황(상세) 서비스
 * </pre>
 *
 * @author gugyeongu
 * @since 2023-10-05
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbSalesPerformanceDetailService {
    private final WdcbSalesPerformanceDetailMapper mapper;

    /**
     * 멤버십매출 상세내역 조회
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @param slClYm 기준년월
     * @return 상세내역
     */
    public SearchMembershipRes getMembershipSalesDetail(String cntrNo, int cntrSn, String slClYm) {
        return mapper.selectMembershipSalesDetail(cntrNo, cntrSn, slClYm);
    }

    /**
     * 렌탈매출 상세내역 조회
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @param slClYm 기준년월
     * @return 상세내역
     */
    public SearchRentalRes getRentalSalesDetail(String cntrNo, int cntrSn, String slClYm) {
        return mapper.selectRentalSalesDetail(cntrNo, cntrSn, slClYm)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));
    }

    /**
     * 정기배송매출 상세내역 조회
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @param slClYm 기준년월
     * @return 상세내역
     */
    public SearchRegularRes getRegularShippingDetail(String cntrNo, int cntrSn, String slClYm) {
        return mapper.selectRegularShippingDetail(cntrNo, cntrSn, slClYm);
    }
}
