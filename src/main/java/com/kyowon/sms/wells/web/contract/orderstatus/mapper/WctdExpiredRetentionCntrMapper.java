package com.kyowon.sms.wells.web.contract.orderstatus.mapper;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdExpiredRetentionCntrDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdExpiredRetentionCntrDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctdExpiredRetentionCntrMapper {

    PagingResult<SearchRes> selectExpiredRetentionCntrPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectExpiredRetentionCntrPages(
        SearchReq dto
    );
}
