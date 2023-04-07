package com.kyowon.sms.wells.web.closing.expense.converter;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritiesMgtDto.EditReq;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritiesDvo;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface WdcdMarketableSecuritiesMgtConverter {

    WdcdMarketableSecuritiesDvo mapEditReqToWdcdMarketableSecuritiesDvo(EditReq req);
}
