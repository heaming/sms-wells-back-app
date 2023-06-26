package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvOutOfStorageMgtDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvOutOfStorageDvo;

@Mapper
public interface WsnaPcsvOutOfStorageMgtMapper {
    List<WsnaPcsvOutOfStorageDvo> selectPcsvOutOfStorages(SearchReq dto);

    String selectPcsvOutOfStorageStockQty(SearchReq dto);

    List<FindLogisticsCentersRes> selectPcsvLogisticsCenters();

    List<FindProductsRes> selectPcsvProducts(FindProductsReq dto);

    List<FindIvcPrntSnRes> selectPcsvIvcPrntSns(SearchReq dto);
}
