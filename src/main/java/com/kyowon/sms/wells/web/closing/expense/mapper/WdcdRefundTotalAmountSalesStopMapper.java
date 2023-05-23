package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRefundTotalAmountSalesStopDto.FindRefundRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRefundTotalAmountSalesStopDto.FindReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRefundTotalAmountSalesStopDto.FindSalesControlRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRefundTotalAmountSalesStopDto.FindSalesStopRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdRefundTotalAmountSalesStopMapper {

    List<FindSalesControlRes> selectSalesControl(FindReq req);

    List<FindRefundRes> selectRefund(FindReq req);

    List<FindSalesStopRes> selectSalesStop(FindReq req);
}
