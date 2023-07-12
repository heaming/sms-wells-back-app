package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStockStatusControlDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaStockStatusControlMapper {

    PagingResult<SearchRes> selectStockStatusControlPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectStockStatusControlPages(
        SearchReq dto
    );

    List<SearchWarehouseRes> selectStockStatusControlWarehouse(SearchWarehouseReq dto);

    FindOgNmRes selectOrganizationDeptName(String wareNo);

    List<FinditmPdCdRes> selectStockStatusItmPdCd(String itmKnd);
}
