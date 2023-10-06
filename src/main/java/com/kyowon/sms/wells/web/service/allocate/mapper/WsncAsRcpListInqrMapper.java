package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsRcpListDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAsRcpListDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsncAsRcpListInqrMapper {

    PagingResult<WsncAsRcpListDvo> selectAsRcpListInqrs(SearchReq searchReq, PageInfo pageInfo);

    List<WsncAsRcpListDvo> selectAsRcpListInqrs(SearchReq searchReq);
}
