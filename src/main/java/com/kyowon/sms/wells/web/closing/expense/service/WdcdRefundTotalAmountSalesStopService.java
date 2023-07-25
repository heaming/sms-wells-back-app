package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRefundTotalAmountSalesStopDto.FindReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRefundTotalAmountSalesStopDto.FindRes;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdRefundTotalAmountSalesStopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WdcdRefundTotalAmountSalesStopService {

    private final WdcdRefundTotalAmountSalesStopMapper mapper;

    public List<FindRes> getSalesControl(FindReq req) {

        List<FindRes> res = new ArrayList<>();
        if ("1".equals(req.gubunCode())) {
            res = mapper.selectSalesControl(req);
        } else if ("2".equals(req.gubunCode())) {
            res = mapper.selectRefund(req);
        } else if ("3".equals(req.gubunCode())) {
            res = mapper.selectSalesStop(req);
        }

        return res;
    }
}
