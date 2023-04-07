package com.kyowon.sms.wells.web.closing.expense.converter;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritiesMgtDto.EditReq;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritiesDvo;

public interface WdcdMarketableSecuritiesMgtConverter {

    WdcdMarketableSecuritiesDvo mapEditReqToWdcdMarketableSecuritiesDvo(EditReq req);
}
