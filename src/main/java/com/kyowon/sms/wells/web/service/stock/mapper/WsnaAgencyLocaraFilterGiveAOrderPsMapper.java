package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAgencyLocaraFilterGiveAOrderPsDto.SearchAgrgRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAgencyLocaraFilterGiveAOrderPsDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAgencyLocaraFilterGiveAOrderPsDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaAgencyLocaraFilterGiveAOrderPsMapper {
    List<SearchAgrgRes> selectAgencyLocaraFilterGiveAOrderPsAgrgs(SearchReq dto);

    PagingResult<WsnaAgencyLocaraFilterGiveAOrderPsDvo> selectAgencyLocaraFilterGiveAOrderPss(
        SearchReq dto, PageInfo pageInfo
    );

    List<WsnaAgencyLocaraFilterGiveAOrderPsDvo> selectAgencyLocaraFilterGiveAOrderPss(SearchReq dto);
}
