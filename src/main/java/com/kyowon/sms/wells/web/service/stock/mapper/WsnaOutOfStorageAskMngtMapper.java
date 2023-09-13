package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAskMngtDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAskMngtSearchDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaOutOfStorageAskMngtMapper {

    PagingResult<SearchRes> selectOutOfStorageAsks(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectOutOfStorageAsks(SearchReq dto);

    FindRes selectOutOfStorageAskItms(FindReq dto);

    List<WsnaOutOfStorageAskMngtDvo> selectOutOfStorageItms(WsnaOutOfStorageAskMngtSearchDvo searchDvo);

    List<SearchOstrObjectWarehouseRes> selectOstrObjectWarehouses(
        SearchOstrObjectWarehouseReq dto
    );

    String selectLogisticsNewOstrAkNo(WsnaOutOfStorageAskMngtDvo dvo);

    String selectNewOstrAkNo(FindOstrAkNoReq findOstrAkNoReq);

    int insertOutOfStorageAskItems(WsnaOutOfStorageAskMngtDvo dvo);

    int updateOutOfStorageAskItmes(WsnaOutOfStorageAskMngtDvo dvo);

    List<WsnaOutOfStorageAskMngtDvo> selectLogisticsOutStorageAskInfo(List<String> ostrAkNos);

    int deleteOutOfStorageAskItems(WsnaOutOfStorageAskMngtDvo dvo);

    List<WsnaOutOfStorageAskMngtDvo> selectDtaDlYnOstrAkNo(String deleteOstrAkNo);

    String selectOstrWareDvCd(SearchReq dto);

    String selectLogisticsOstrDvCd(String ostrOjWareNo);

    String selectAkWareDvCd(String strOjWareNo);

    List<WsnaOutOfStorageAskMngtDvo> selectBusinessLogisticsOutStorageAskInfo(List<String> ostrAkNos);
}
