package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemReceivingAndPayingDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReceiptsAndPaymentsDvo;

@Mapper
public interface WsnaItemReceivingAndPayingMapper {

    long selectReceiptsAndPaymentCount(WsnaReceiptsAndPaymentsDvo dvo);

    List<SearchRes> selectReceiptsAndPaymentsPages(
        WsnaReceiptsAndPaymentsDvo dvo
    );

    List<SearchRes> selectReceiptsAndPaymentsPages(
        SearchReq dto
    );

    List<SearchDateRes> selectDateReceivingAndPayings(SearchDateReq dto);
}
