package com.kyowon.sms.wells.web.contract.orderstatus.mapper;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdExpiredRetentionCntrDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.orderstatus.dvo.WctdExpiredRetentionCntrDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctdExpiredRetentionCntrMapper {

    WctdExpiredRetentionCntrDvo getMembershipCntrInfo(
        String cntrNo,
        String cntrSn,
        List<String> canDtlCds
    );

    PagingResult<WctdExpiredRetentionCntrDvo> selectExpiredRetentionCntrPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<WctdExpiredRetentionCntrDvo> selectExpiredRetentionCntrPages(
        SearchReq dto
    );
}
