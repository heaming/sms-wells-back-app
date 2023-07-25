package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRefundTotalAmountSalesStopDto.FindReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRefundTotalAmountSalesStopDto.FindRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdRefundTotalAmountSalesStopMapper {

    List<FindRes> selectSalesControl(FindReq req);

    List<FindRes> selectRefund(FindReq req);

    List<FindRes> selectSalesStop(FindReq req);
}
