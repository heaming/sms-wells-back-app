package com.kyowon.sms.wells.web.closing.expense.converter;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdSecuritiesExceptionMgtDto;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdSecuritiesExceptionDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WdcdSecuritiesExceptionMgtContverter {
    WdcdSecuritiesExceptionMgtDto.SearchAdjustObjectRes mapWdcdMarketableSecuritiesExcdDvoToSearchAdjustObjectRes(WdcdSecuritiesExceptionDvo dvo);

    WdcdSecuritiesExceptionDvo mapEditReqToWdcdMarketableSecuritiesExcdDvo(WdcdSecuritiesExceptionMgtDto.SaveReq req);
}
