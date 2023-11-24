package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaAsConsumablesStoreDto.*;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsConsumablesStoreDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaAsConsumablesStoreMapper {

    PagingResult<SearchRes> selectAsConsumablesStorePages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectAsConsumablesStorePages(
        SearchReq dto
    );

    int selectMcbyWareIzCount(WsnaAsConsumablesStoreDvo dvo);

    int selectItmPdCdCount(WsnaAsConsumablesStoreDvo dvo);

    int insertAsConsumablesStore(WsnaAsConsumablesStoreDvo insertDvo);

    String selectMgtUntFind(WsnaAsConsumablesStoreDvo dvo);

    int selectChkWareClose(WsnaAsConsumablesStoreDvo dvo);

    int updateAsConsumablesStore(WsnaAsConsumablesStoreDvo dvo);

    String selectNextItmStrNo(String strTpCd, String strRgstDt);

    int insertLineAsConsumablesStore(WsnaAsConsumablesStoreDvo dvo);

    int deleteAsConsumablesStores(WsnaAsConsumablesStoreDvo dvo);

    List<SearchItemRes> selectItemProductCodes(SearchItemReq dto);

    String selectWareMngtPrtnrNo(WsnaAsConsumablesStoreDvo dvo);

    int deletePitmStocAGdQty(WsnaAsConsumablesStoreDvo dvo);

    WsnaAsConsumablesStoreDvo selectItmPdCdInformation(WsnaAsConsumablesStoreDvo dvo);

    WsnaAsConsumablesStoreDvo selectMonthlyItmPdCdInformation(WsnaAsConsumablesStoreDvo dvo);

    int deletePitmStocEGdQty(WsnaAsConsumablesStoreDvo dvo);

    int deleteMonthlyPitmStocAGdQty(WsnaAsConsumablesStoreDvo dvo);

    int deleteMonthlyPitmStocEGdQty(WsnaAsConsumablesStoreDvo dvo);

    Optional<FindWareNmRes> selectStrWarehouseName(String apyYm, String strWareNo);

    Optional<FindItmPdCdNmRes> selectItmPdCdNm(String sapCd);

    Optional<FindSapCdNmRes> selectSapCdNm(String itmPdCd);
}
