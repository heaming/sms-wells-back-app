package com.kyowon.sms.wells.web.contract.salesstatus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteSecProductDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WcteSecProductMapper {

    PagingResult<SearchRes> selectReservationPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectReservationPages(
        SearchReq dto
    );
}
