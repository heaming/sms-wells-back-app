package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAgencyLocaraFilterGiveAOrderPsDto.SearchReq;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaAgencyLocaraFilterGiveAOrderPsMapper {
    List selectAgencyLocaraFilterGiveAOrderPsAgrgs(SearchReq dto);

    PagingResult selectAgencyLocaraFilterGiveAOrderPss(SearchReq dto, PageInfo pageInfo);

    List selectAgencyLocaraFilterGiveAOrderPss(SearchReq dto);
}
