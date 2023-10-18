package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReturningGoodsDvo;

/**
 * <pre>
 * W-SV-U-0001M01 반품출고 등록
 * </pre>
 *
 * @author hyewon.kim
 * @author SongTaeSung
 * @since 2023.02.14
 */
@Mapper
public interface WsnaReturningGoodsOstrMapper {

    List<SearchWarehouseRes> selectWareHouses(SearchWarehouseReq dto);

    List<WsnaReturningGoodsDvo> selectReturningGoodsOstrs(String itmOstrNo);

    ItemOutOfStorage selectItemOutOfStorage(SearchReq itmOstrNo);

    String selectIsClosedByPk(String itmOstrNo);

    int insertItemForwardingHistory(WsnaReturningGoodsDvo dvo);

    int insertItemReceivingHistory(WsnaReturningGoodsDvo dvo);

    int deleteItemForwardingHistory(WsnaReturningGoodsDvo dvo);

    int deleteItemReceivingHistory(WsnaReturningGoodsDvo dvo);

    String selectNextItmOstrNo(FindItmOstrNoReq dto);

    String selectNextItmStrNo(FindItmStrNoReq dto);

    List<WsnaReturningGoodsDvo> selectLogisticsReturningGoodsAskInfo(
        String itmOstrNo, List<String> ostrSns, String trnspnCd
    );

    List<WsnaReturningGoodsDvo> selectLogisticsRemoveReturn(String itmOstrNo, List<String> deleteOstrSns);

    List<SearchPitmStockRes> selectPitmStocks(String wareNo, List<String> itmPdCds, String itmGdCd);
}
