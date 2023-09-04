package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterUseAgrgDto.FindFilterProductRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterUseAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterUseAgrgDto.SearchRes;

@Mapper
public interface WsnaFilterUseAgrgMapper {

    List<FindFilterProductRes> selectFilterProducts(SearchReq dto);

    List<SearchRes> selectFilterUseAgrgs(SearchReq dto);
}
