package com.kyowon.sms.wells.web.closing.performance.mapper;

import com.kyowon.sms.wells.web.closing.performance.dvo.WdccSalesBondDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WdccSalesBondMapper {
    List<WdccSalesBondDvo> selectAccountsReceivableByDate(Map<String, Object> param);

    List<WdccSalesBondDvo> selectAccountsReceivableRental(Map<String, Object> param);

    List<WdccSalesBondDvo> selectAccountsReceivableLease(Map<String, Object> param);

    List<WdccSalesBondDvo> selectAccountsReceivableMembership(Map<String, Object> param);

    List<WdccSalesBondDvo> selectLumpSumOfAccountsReceivable(Map<String, Object> param);

    List<WdccSalesBondDvo> selectTradeReceivablesPeriodicDelivery(Map<String, Object> param);
}
