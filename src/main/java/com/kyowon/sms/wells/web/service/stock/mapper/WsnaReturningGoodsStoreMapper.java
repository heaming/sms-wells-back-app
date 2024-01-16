package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsStoreDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReturningGoodsStoreDvo;

/**
 * <pre>
 * W-SV-U-0108M01 반품입고 관리
 * </pre>
 *
 * @author SongTaeSung
 * @since 2023.05.10
 */
@Mapper
public interface WsnaReturningGoodsStoreMapper {

    List<SearchRes> selectReturningGoodsStores(SearchReq dto);

    String selectNextItmOstrNo(FindItmOstrNoReq findItmOstrNoReq);

    String selectNextItmStrNo(FindItmStrNoReq findItmStrNoReq);

    String selectHgrWareNo(WsnaReturningGoodsStoreDvo dvo);

    String selectUpHgrWareNo(WsnaReturningGoodsStoreDvo dvo);

    int insertQuantityItmOstrIz(WsnaReturningGoodsStoreDvo dvo);

    int insertQuantityItmStrIz(WsnaReturningGoodsStoreDvo dvo);

    /*리퍼출고 완료 건에 대한 INSERT*/
    int insertRefrOstrFinish(WsnaReturningGoodsStoreDvo dvo);

    int updateWkOstrIz(WsnaReturningGoodsStoreDvo dvo);

    int updateRefrOstrFinish(WsnaReturningGoodsStoreDvo dvo);

    int updateNotOstrConfDt(WsnaReturningGoodsStoreDvo dvo);

    String selectHgrWarePrtnrNo(WsnaReturningGoodsStoreDvo dvo);

    String selectUpHgrWarePrtnrNo(WsnaReturningGoodsStoreDvo dvo);

    int updateReturningGoodsStoreConfirmations(WsnaReturningGoodsStoreDvo dvo);

    int insertDiDisuseOstrIz(WsnaReturningGoodsStoreDvo dvo);

    String selectRmkCn(WsnaReturningGoodsStoreDvo dvo);

    String selectRefrRmkCn(WsnaReturningGoodsStoreDvo dvo);

    int updateReturningGoodsStrConfirm(WsnaReturningGoodsStoreDvo dvo);

    List<SearchWareRes> selectReturningGoodsStoresLoginWarehouse(String prtnrNo, String strtDt, String endDt);

}
