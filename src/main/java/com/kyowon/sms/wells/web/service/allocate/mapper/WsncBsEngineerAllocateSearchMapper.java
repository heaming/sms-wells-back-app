package com.kyowon.sms.wells.web.service.allocate.mapper;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncBsEngineerAllocateSearchDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsncBsEngineerAllocateSearchMapper {
    PagingResult<SearchRes> selectBsEngineerAllocateList(
        SearchReq dto, PageInfo pageInfo
    );

    List<SearchRes> selectBsEngineerAllocateList(
        SearchReq dto
    );

    List<AggregateRes> selectBsEngineerAllocateAggregate(
        SearchReq dto
    );
}
