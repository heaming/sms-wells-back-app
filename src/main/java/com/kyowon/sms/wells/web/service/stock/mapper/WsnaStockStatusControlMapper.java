package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStockStatusControlDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockStatusControlDvo;
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

    List<SearchItmPdCdRes> selectStockStatusItmPdCd(SearchItmPdCdReq dto);

    List<SearchWarehouseItmPdCdRes> selectStockStatusWarehouseItmPdCd(SearchWarehouseItmPdCdReq dto);

    List<SearchWarehouseItmPdCdRes> selectStatusProductItmPdCd(SearchStatusProductReq dto);

    SearchPdCdQtyRes selectItmPdCdQty(SearchPdCdQtyReq dto);

    int insertStockStatusControls(WsnaStockStatusControlDvo dvo);

    int updateStockStatusControlsForRemove(WsnaStockStatusControlDvo dvo);
}
