package com.kyowon.sms.wells.web.closing.expense.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritieExceptionMgtDto.AccCardInfoDetailRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritieExceptionMgtDto.SaveReq;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritieExceptionDvo;

@Mapper(componentModel = "spring")
public interface WdcdMarketableSecuritieExceptionMgtConverter {
    WdcdMarketableSecuritieExceptionDvo mapSaveReqToWdcdMarketableSecuritieExceptionDvo(SaveReq req);
    WdcdMarketableSecuritieExceptionDvo mapAccCardInfoDetailResToWdcdMarketableSecuritieExceptionDvo(AccCardInfoDetailRes res);
}
