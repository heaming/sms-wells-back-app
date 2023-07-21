package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsUseMcntAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsUseMcntAgrgDto.SearchRes;

@Mapper
public interface WsnaReturningGoodsUseMcntAgrgMapper {

    List<SearchRes> selectReturningGoodsUseMcntAgrg(
        SearchReq dto
    );

}
