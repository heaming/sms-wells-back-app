package com.kyowon.sms.wells.web.bond.credit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtMessageExcludeDto.SearchReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtMessageExcludeDto.SearchRes;
import com.kyowon.sms.wells.web.bond.credit.dvo.WbndBondContactExcludeIzDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WbndRentalCbMgtMessageExcludeMapper {

    PagingResult<SearchRes> getRentalCbMessageExcludePages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> getRentalCbMessageExcludePages(
        SearchReq dto
    );

    int insertRentalCbMessageExclude(
        WbndBondContactExcludeIzDvo dvo
    );

    int updateRentalCbMessageExclude(
        WbndBondContactExcludeIzDvo dvo
    );

    int deleteRentalCbMessageExclude(
        String bndCntcExcdOjId
    );
}
