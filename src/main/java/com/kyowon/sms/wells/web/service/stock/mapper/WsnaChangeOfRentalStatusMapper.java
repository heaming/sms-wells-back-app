package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaChangeOfRentalStatusDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaChangeOfRentalStatusMapper {

    PagingResult<SearchRes> selectChangeOfRentalStatusPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectChangeOfRentalStatusPages(
        SearchReq dto
    );

    List<SearchItmPdCdRes> selectChangeOfRentalStatusItmPdCd();

    List<SearchWarehouseRes> selectChangeOfRentalStatusWarehouse(SearchWarehouseReq dto);
}
