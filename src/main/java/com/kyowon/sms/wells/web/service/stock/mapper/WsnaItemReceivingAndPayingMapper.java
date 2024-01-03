package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemReceivingAndPayingDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnaItemReceivingAndPayingMapper {

    List<SearchRes> selectReceiptsAndPaymentsPages(
        SearchReq dto
    );

    List<SearchDateRes> selectDateReceivingAndPayings(SearchDateReq dto);
}
