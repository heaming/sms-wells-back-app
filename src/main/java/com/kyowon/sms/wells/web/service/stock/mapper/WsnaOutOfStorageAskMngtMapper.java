package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;
import java.util.Optional;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskRgstDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.Warehouse;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.WarehouseReq;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.*;

@Mapper
public interface WsnaOutOfStorageAskMngtMapper {

    List<SearchRes> selectOutOfStorageAsks(SearchReq dto);

    List<Warehouse> selectWarehouses(WarehouseReq dto);

    Optional<FindRes> selectOutOfStorageAskItms(FindReq dto);

    PagingResult<WsnaOutOfStorageAskMngtDto.OutOfRes> selectOutOfStorageItms(
        WsnaOutOfStorageAskMngtDto.SearchReq dto, PageInfo pageInfo
    );
}
