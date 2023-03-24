package com.kyowon.sms.wells.web.closing.sales.mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbMonthlySalesVolumeTableDto.SearchPaymentRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbMonthlySalesVolumeTableDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbMonthlySalesVolumeTableDto.SearchReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcbMonthlySalesVolumeTableMapper {

    List<SearchRentalRes> selectRentalMonthSalesQuantity(SearchReq req);

    List<SearchPaymentRes> selectPaymentMonthSalesQuantity(SearchReq req);

}
