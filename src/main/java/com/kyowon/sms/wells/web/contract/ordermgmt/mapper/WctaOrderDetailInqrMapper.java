package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailInqrDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaOrderDetailInqrMapper {
    PagingResult<WctaOrderDetailInqrDto.SearchRes> selectOrderDetailSpayCntrtPages(
        WctaOrderDetailInqrDto.SearchReq dto, PageInfo pageInfo
    );

    List<WctaOrderDetailInqrDto.SearchRes> selectOrderDetailSpayCntrtPages(
        WctaOrderDetailInqrDto.SearchReq dto
    );
}
