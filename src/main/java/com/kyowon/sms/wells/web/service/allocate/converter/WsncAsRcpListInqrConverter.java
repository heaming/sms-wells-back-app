package com.kyowon.sms.wells.web.service.allocate.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsRcpListDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAsRcpListDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WsncAsRcpListInqrConverter {

    PagingResult<SearchRes> mapAllDvoToSearchRes(PagingResult<WsncAsRcpListDvo> dvos);

    List<SearchRes> mapAllDvoToSearchRes(List<WsncAsRcpListDvo> dvos);
}
