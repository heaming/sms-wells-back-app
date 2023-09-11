package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAgencyLocaraFilterGiveAOrderPsDto.SearchReq;

@Mapper
public interface WsnaAgencyLocaraFilterGiveAOrderPsMapper {
    List selectAgencyLocaraFilterGiveAOrderPsAgrgs(SearchReq dto);

    List selectAgencyLocaraFilterGiveAOrderPss(SearchReq dto);
}
