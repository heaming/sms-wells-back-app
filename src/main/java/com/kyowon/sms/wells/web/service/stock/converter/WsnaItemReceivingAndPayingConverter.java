package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemReceivingAndPayingDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemReceivingAndPayingDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReceiptsAndPaymentsDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WsnaItemReceivingAndPayingConverter {

    WsnaReceiptsAndPaymentsDvo mapSearchReqToWsnaReceiptsAndPaymentsDvo(SearchReq dto);
}
