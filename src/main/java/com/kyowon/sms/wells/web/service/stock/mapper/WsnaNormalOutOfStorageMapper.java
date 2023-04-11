package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.*;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaNormalOutOfStorageMapper {
    PagingResult<SearchRes> selectNormalOutOfStorage(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectNormalOutOfStorage(SearchReq dto);

    List<SearchWarehouse> selectWarehouses(SearchReq dto);

    PagingResult<AskRes> selectAskMaterialsHavePss(AskReq dto, PageInfo pageInfo);

    PagingResult<CenterRes> selectAskMaterialsCenterPresentState(AskReq dto, PageInfo pageInfo);

    PagingResult<DetailRes> selectNormalOutOfStoragesDetails(DetailReq dto, PageInfo pageInfo);

    int insertNormalOstrRgst(WsnaNormalOutOfStorageDvo vo);

    int insertNormalStrRgst(WsnaNormalOutOfStorageDvo vo);

    int selectNormalOstrRgstChecked(CheckedReq dto);

    HashMap<String, String> selectStrNoAndOstrNo(WsnaNormalOutOfStorageDvo vo);
}
