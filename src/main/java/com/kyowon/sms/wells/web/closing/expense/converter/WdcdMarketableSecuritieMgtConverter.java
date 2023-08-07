package com.kyowon.sms.wells.web.closing.expense.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritieMgtDto.AccCardInfoDetailRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritieMgtDto.SaveReq;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritieDvo;

@Mapper(componentModel = "spring")
public interface WdcdMarketableSecuritieMgtConverter {
    WdcdMarketableSecuritieDvo mapSaveReqToWdcdMarketableSecuritieDvo(SaveReq req);
    WdcdMarketableSecuritieDvo mapAccCardInfoDetailResToWdcdMarketableSecuritieDvo(AccCardInfoDetailRes res);
}
