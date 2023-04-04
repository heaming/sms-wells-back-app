package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailInqrDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailInqrDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaOrderDetailInqrMapper {
    PagingResult<WctaOrderDetailInqrDvo> selectOrderDetailSpayCntrtPages(
        WctaOrderDetailInqrDto.SearchReq dto, PageInfo pageInfo
    );

    List<WctaOrderDetailInqrDvo> selectOrderDetailSpayCntrtPages(
        WctaOrderDetailInqrDto.SearchReq dto
    );
}
