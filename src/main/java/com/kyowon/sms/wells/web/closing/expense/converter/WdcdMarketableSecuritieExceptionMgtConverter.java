package com.kyowon.sms.wells.web.closing.expense.converter;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritieExceptionMgttDto.AccCardInfoDetailRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritieExceptionMgttDto.SaveReq;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritieExceptionDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WdcdMarketableSecuritieExceptionMgtConverter {

    WdcdMarketableSecuritieExceptionDvo mapSaveReqToWdcdMarketableSecuritieExceptionDvo(SaveReq req);

    WdcdMarketableSecuritieExceptionDvo mapAccCardInfoDetailResToWdcdMarketableSecuritieExceptionDvo(AccCardInfoDetailRes res);
}
