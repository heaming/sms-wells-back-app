package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncMonthBsExpcCustAgrgStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncMonthBsExpcCustAgrgStateDto.SearchRes;

@Mapper
public interface WsncMonthBsExpcCustAgrgStateMapper {

    List<SearchRes> selectMonthBsExpcCustAgrgState(SearchReq dto);

}
