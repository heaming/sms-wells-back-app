package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.*;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAskMngtDvo;
import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaOutOfStorageAskMngtMapper {

    PagingResult<SearchRes> selectOutOfStorageAsks(SearchReq dto, PageInfo pageInfo);

    FindRes selectOutOfStorageAskItms(FindReq dto);

    List<OutOfRes> selectOutOfStorageItms(SearchReq dto);

    List<SearchOstrObjectWarehouseRes> selectOstrObjectWarehouses(
        SearchOstrObjectWarehouseReq dto
    );

    String selectNewOstrAkNo(FindOstrAkNoReq findOstrAkNoReq);

    int insertOutOfStorageAskItems(WsnaOutOfStorageAskMngtDvo dvo);

    int updateOutOfStorageAskItmes(WsnaOutOfStorageAskMngtDvo dvo);

    List<WsnaOutOfStorageAskMngtDvo> selectLogisticsOutStorageAskInfo(String ostrAkNo);

    int deleteOutOfStorageAskItems(WsnaOutOfStorageAskMngtDvo dvo);

    List<WsnaOutOfStorageAskMngtDvo> selectDtaDlYnOstrAkNo(String deleteOstrAkNo);
}
