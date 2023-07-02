package com.kyowon.sms.wells.web.closing.expense.converter;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdSecuritiesMgtDto;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdSecuritiesMgtDto.SaveReq;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdSecuritiesDvo;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface WdcdMarketableSecuritiesMgtConverter {

    WdcdSecuritiesMgtDto.SearchAdjustObjectRes mapWdcdMarketableSecuritiesDvoToSearchAdjustObjectRes(WdcdSecuritiesDvo dvo);

    WdcdSecuritiesDvo mapSaveReqToWdcdMarketableSecuritiesDvo(SaveReq req);
}
