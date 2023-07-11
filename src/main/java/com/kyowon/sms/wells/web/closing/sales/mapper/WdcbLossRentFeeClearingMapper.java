package com.kyowon.sms.wells.web.closing.sales.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbLossRentFeeClearingDvo;

@Mapper
public interface WdcbLossRentFeeClearingMapper {

    int selectLossRentFee(WdcbLossRentFeeClearingDvo inputDvo);

    int insertLossRentFee(WdcbLossRentFeeClearingDvo inputDvo);

    int updateLossRentFee(WdcbLossRentFeeClearingDvo inputDvo);

}