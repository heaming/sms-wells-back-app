package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsConsumablesStoreDvo;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaAsConsumablesStoreDto.*;

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
}
