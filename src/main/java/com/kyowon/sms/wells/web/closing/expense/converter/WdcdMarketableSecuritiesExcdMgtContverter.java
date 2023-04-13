package com.kyowon.sms.wells.web.closing.expense.converter;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdSecuritiesExceptionMgtDto.EditReq;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritiesExcdDvo;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface WdcdMarketableSecuritiesExcdMgtContverter {

    WdcdMarketableSecuritiesExcdDvo mapEditReqToWdcdMarketableSecuritiesExcdDvo(EditReq req);
}
