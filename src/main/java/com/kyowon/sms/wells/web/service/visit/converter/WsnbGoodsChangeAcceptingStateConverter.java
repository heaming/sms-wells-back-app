package com.kyowon.sms.wells.web.service.visit.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbGoodsChangeAcceptingStateDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbGoodsChangeAcceptingStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbGoodsChangeAcceptingStateDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WsnbGoodsChangeAcceptingStateConverter {
    PagingResult<SearchRes> mapAllSearchGoodsChangeAcceptingStateToDvo(List<WsnbGoodsChangeAcceptingStateDvo> dvos);

    WsnbGoodsChangeAcceptingStateDvo mapSaveReqToGoodsChangeAcceptingStateDvo(SaveReq dto);

}
