package com.kyowon.sms.wells.web.closing.expense.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdSecuritiesMgtDto;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdSecuritiesMgtDto.SaveReq;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdSecuritiesDvo;


@Mapper(componentModel = "spring")
public interface WdcdMarketableSecuritieMgtPopConverter {
    WdcdSecuritiesDvo mapSaveReqToWdcdMarketableSecuritiesDvo(SaveReq req);
    WdcdSecuritiesMgtDto.SearchAdjustObjectRes mapWdcdMarketableSecuritiesDvoToSearchAdjustObjectRes(WdcdSecuritiesDvo dvo);
}
