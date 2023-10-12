package com.kyowon.sms.wells.web.closing.sales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbMonthlySalesVolumeTableDto.SearchPaymentRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbMonthlySalesVolumeTableDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbMonthlySalesVolumeTableDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbMonthlySalesVolumeTableMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 월 매출수량 집계표
 * </pre>
 *
 * @author songmi.in
 * @since 2023-03-24
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbMonthlySalesVolumeTableService {
    private final WdcbMonthlySalesVolumeTableMapper mapper;

    /**
      * 월 매출수량 집계표 - 렌탈 조회
      * @param req
      * @return 조회결과
      */
    public List<SearchRentalRes> getRentalMonthSalesQuantity(SearchReq req) {
        return mapper.selectRentalMonthSalesQuantity(req);
    }

    /**
      * 월 매출수량 집계표 - 일시불/할부 조회
      * @param req
      * @return 조회결과
      */
    public List<SearchPaymentRes> getPaymentMonthSalesQuantity(SearchReq req) {
        return mapper.selectPaymentMonthSalesQuantity(req);
    }
}
