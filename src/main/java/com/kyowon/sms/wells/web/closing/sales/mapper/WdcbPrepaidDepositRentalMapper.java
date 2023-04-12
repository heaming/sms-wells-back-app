package com.kyowon.sms.wells.web.closing.sales.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbPrepaidDepositRentalDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbPrepaidDepositRentalDto.SearchRes;

@Mapper
public interface WdcbPrepaidDepositRentalMapper {

    SearchRes selectPrepaidDepositRental(SearchReq dto);
}