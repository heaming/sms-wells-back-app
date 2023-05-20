package com.kyowon.sms.wells.web.closing.performance.service;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccDelinquentAdditionalChargesDto.SearchAggregateRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccDelinquentAdditionalChargesDto.SearchOrderUnitRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccDelinquentAdditionalChargesDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccDelinquentAdditionalChargesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WdccDelinquentAdditionalChargesService {

    private final WdccDelinquentAdditionalChargesMapper mapper;

    public List<SearchAggregateRes> getDelinquentAdditionalChargesAggregate(
        SearchReq req
    ) {

        List<SearchAggregateRes> res = new ArrayList<SearchAggregateRes>();

        if ("2".equals(req.sellTpCd()) && ("21".equals(req.sellTpDtlCd()) || "23".equals(req.sellTpDtlCd()))) {

            res = mapper.selectRentalAggregate(req);

        } else if ("2".equals(req.sellTpCd()) && ("22".equals(req.sellTpDtlCd()) || "24".equals(req.sellTpDtlCd()))) {

            res = mapper.selectLeaseAggregate(req);

        } else if ("3".equals(req.sellTpCd())) {

            res = mapper.selectMemberShipAggregate(req);

        } else if ("6".equals(req.sellTpCd())) {

            // 정기배송 집계
            res = mapper.selectRegularDeliveryAggregate(req);
        }

        return res;
    }

    public List<SearchOrderUnitRes> getDelinquentAdditionalCharges(
        SearchReq req
    ) {

        List<SearchOrderUnitRes> res = new ArrayList<SearchOrderUnitRes>();

        if ("2".equals(req.sellTpCd()) && ("21".equals(req.sellTpDtlCd()) || "23".equals(req.sellTpDtlCd()))) {

            res = mapper.selectRentalOrderUnit(req);
        } else if ("2".equals(req.sellTpCd()) && ("22".equals(req.sellTpDtlCd()) || "24".equals(req.sellTpDtlCd()))) {

            res = mapper.selectLeaseOrderUnit(req);
        } else if ("3".equals(req.sellTpCd())) {

            res = mapper.selectMemberShipOrderUnit(req);
        } else if ("6".equals(req.sellTpCd())) {
            
            res = mapper.selectRegularDeliveryOrderUnit(req);
        }

        return res;
    }
}
