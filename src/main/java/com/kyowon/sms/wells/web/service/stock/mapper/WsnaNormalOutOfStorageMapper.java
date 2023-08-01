package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageCheckDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageDetailDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageStdgbDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaNormalOutOfStorageMapper {
    PagingResult<SearchRes> selectNormalOutOfStorage(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectNormalOutOfStorage(SearchReq dto);

    List<SearchWarehouse> selectWarehouses(String apyYm);

    PagingResult<AskRes> selectAskMaterialsHavePss(AskReq dto, PageInfo pageInfo);

    PagingResult<CenterRes> selectAskMaterialsCenterPresentState(CenterReq dto, PageInfo pageInfo);

    PagingResult<WsnaNormalOutOfStorageDetailDvo> selectNormalOstrRgsts(DetailReq dto, PageInfo pageInfo);

    List<WsnaNormalOutOfStorageDetailDvo> selectNormalOstrRgsts(DetailReq dto);

    PagingResult<WsnaNormalOutOfStorageDetailDvo> selectNormalOstrRgstsForRemove(
        DetailRemoveReq dto, PageInfo pageInfo
    );

    List<WsnaNormalOutOfStorageDetailDvo> selectNormalOstrRgstsForRemove(DetailRemoveReq dto);

    int insertNormalOstrRgst(WsnaNormalOutOfStorageDvo vo);

    int insertNormalStrRgst(WsnaNormalOutOfStorageDvo vo);

    int updateItmOstrAkIz(WsnaNormalOutOfStorageDvo vo);

    int updateStandardWareHouse(WsnaNormalOutOfStorageStdgbDvo dvo);

    StandardWareRes selectStandardWareHouse(StandardWareReq dto);

    SearchItmOstrAkRes selectItmOstrAk(SearchItmOstrAkReq dto);

    String selectWareCloseYn(String ostrDt, String ostrOjWareNo);

    int updateItmOstrIzForRemove(WsnaNormalOutOfStorageDvo dvo);

    int updateItmStrIzForRemove(WsnaNormalOutOfStorageDvo dvo);

    int updateItmOstrAkIzForRemove(WsnaNormalOutOfStorageDvo dvo);

    String selectNewItmStrNo(String strTpCd);

    String selectNewItmOstrNo(String ostrTpCd);

    int selectNewOstrSn(String itmOstrNo);

    int selectNewStrSn(String itmStrNo);

    int selectOstrCnfmCount(List<WsnaNormalOutOfStorageCheckDvo> dvos);

}
