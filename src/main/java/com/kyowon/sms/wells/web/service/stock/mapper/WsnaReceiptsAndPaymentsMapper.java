package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaReceiptsAndPaymentsDto.*;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSampleDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaReceiptsAndPaymentsMapper {

    PagingResult<SearchRes> selectReceiptsAndPaymentsPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectReceiptsAndPaymentsPages(
        SearchReq dto
    );
}
