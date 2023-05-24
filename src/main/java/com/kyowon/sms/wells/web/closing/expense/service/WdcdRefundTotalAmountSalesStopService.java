package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRefundTotalAmountSalesStopDto.FindRefundRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRefundTotalAmountSalesStopDto.FindReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRefundTotalAmountSalesStopDto.FindSalesControlRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRefundTotalAmountSalesStopDto.FindSalesStopRes;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdRefundTotalAmountSalesStopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WdcdRefundTotalAmountSalesStopService {

    private final WdcdRefundTotalAmountSalesStopMapper mapper;

    public List<FindSalesControlRes> getSalesControl(FindReq req) {
        return mapper.selectSalesControl(req);
    }

    public List<FindRefundRes> getRefund(FindReq req) {
        return mapper.selectRefund(req);
    }

    public List<FindSalesStopRes> getSalesStop(FindReq req) {
        return mapper.selectSalesStop(req);
    }
}
