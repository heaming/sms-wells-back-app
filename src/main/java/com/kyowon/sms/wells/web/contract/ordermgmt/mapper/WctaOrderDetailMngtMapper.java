package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailMngtDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailMngtDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaOrderDetailMngtMapper {
    PagingResult<SearchRes> selectOrderDetailRentalPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectOrderDetailRentalPages(
        SearchReq dto
    );
}
