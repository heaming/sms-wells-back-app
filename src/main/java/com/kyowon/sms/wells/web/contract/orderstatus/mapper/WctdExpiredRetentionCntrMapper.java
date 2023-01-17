package com.kyowon.sms.wells.web.contract.orderstatus.mapper;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdExpiredRetentionCntrDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctdExpiredRetentionCntrMapper {

    FindMshCntrRes getMembershipCntrInfo(
        String cntrNo,
        String cntrSn,
        List<String> canDtlCds
    );

    PagingResult<SearchCntrRes> selectExpiredRetentionCntrPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchCntrRes> selectExpiredRetentionCntrPages(
        SearchReq dto
    );
}
