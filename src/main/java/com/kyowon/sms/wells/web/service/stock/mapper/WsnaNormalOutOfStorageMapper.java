package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageStdgbDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaNormalOutOfStorageMapper {
    PagingResult<SearchRes> selectNormalOutOfStorage(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectNormalOutOfStorage(SearchReq dto);

    List<SearchWarehouse> selectWarehouses(SearchReq dto);

    PagingResult<AskRes> selectAskMaterialsHavePss(AskReq dto, PageInfo pageInfo);

    PagingResult<CenterRes> selectAskMaterialsCenterPresentState(AskReq dto, PageInfo pageInfo);

    PagingResult<DetailRes> getNormalOstrRgsts(DetailReq dto, PageInfo pageInfo);
    PagingResult<DetailRes> removeNormalOstrRgsts(DetailReq dto, PageInfo pageInfo);

    int insertNormalOstrRgst(WsnaNormalOutOfStorageDvo vo);

    int insertNormalStrRgst(WsnaNormalOutOfStorageDvo vo);

    int selectNormalOstrRgstChecked(CheckedReq dto);

    int updateOstrAkIz(WsnaNormalOutOfStorageDvo vo);

    int updateOstrAkIzAfter(WsnaNormalOutOfStorageDvo vo);

    StrNoAndOstrNoRes selectStrNoAndOstrNo(WsnaNormalOutOfStorageDvo vo);

    int updateStandardWareHouse(WsnaNormalOutOfStorageStdgbDvo dvo);

    StandardWareRes selectStandardWareHouse(StandardWareReq dto);

    SearchItmOstrAkRes selectItmOstrAk(SearchItmOstrAkReq dto);

    int removeNormalStr(WsnaNormalOutOfStorageDvo vo);
    int removeNormalOstr(WsnaNormalOutOfStorageDvo vo);
    int updateRemoveOstrAkIzAfter(WsnaNormalOutOfStorageDvo vo);
}
