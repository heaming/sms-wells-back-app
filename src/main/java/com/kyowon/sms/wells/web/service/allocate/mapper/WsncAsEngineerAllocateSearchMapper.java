package com.kyowon.sms.wells.web.service.allocate.mapper;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncAsEngineerAllocateSearchDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAsEngineerAllocateSearchDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsncAsEngineerAllocateSearchMapper {

    PagingResult<WsncAsEngineerAllocateSearchDvo> selectAsEngineerAllocateSearchPages(
        SearchReq searchReq, PageInfo pageInfo
    );

    List<WsncAsEngineerAllocateSearchDvo> selectAsEngineerAllocateSearchPages(
        SearchReq searchReq
    );
}
