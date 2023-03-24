package com.kyowon.sms.wells.web.closing.sales.service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbMonthlySalesVolumeTableDto.SearchPaymentRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbMonthlySalesVolumeTableDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbMonthlySalesVolumeTableDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbMonthlySalesVolumeTableMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbMonthlySalesVolumeTableService {
    private final WdcbMonthlySalesVolumeTableMapper mapper;

    public List<SearchRentalRes> getRentalMonthSalesQuantity(SearchReq req) {
        return mapper.selectRentalMonthSalesQuantity(req);
    }

    public List<SearchPaymentRes> getPaymentMonthSalesQuantity(SearchReq req) {
        return mapper.selectPaymentMonthSalesQuantity(req);
    }
}
