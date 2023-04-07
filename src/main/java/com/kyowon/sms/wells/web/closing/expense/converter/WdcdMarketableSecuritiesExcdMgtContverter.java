package com.kyowon.sms.wells.web.closing.expense.converter;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritiesExcdMgtDto.EditReq;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritiesExcdDvo;

public interface WdcdMarketableSecuritiesExcdMgtContverter {

    WdcdMarketableSecuritiesExcdDvo mapEditReqToWdcdMarketableSecuritiesExcdDvo(EditReq req);
}
