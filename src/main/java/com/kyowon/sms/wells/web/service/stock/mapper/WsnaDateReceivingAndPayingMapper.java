package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaDateReceivingAndPayingDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaDateReceivingAndPayingMapper {

    PagingResult<SearchRes> selectDateReceivingAndPayings(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectDateReceivingAndPayings(SearchReq dto);
}
