package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReturningGoodsStoreDvo;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsStoreDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

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

    PagingResult<SearchRes> selectReturningGoodsStores(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectReturningGoodsStores(SearchReq dto);

    String selectNextItmOstrNo(FindItmOstrNoReq findItmOstrNoReq);

    String selectNextItmStrNo(FindItmStrNoReq findItmStrNoReq);

    String selectNextOstrAkNo(FindOstrAkNoReq findOstrAkNoReq);

    String selectHgrWareNo(WsnaReturningGoodsStoreDvo dvo);

    String selectUpHgrWareNo(WsnaReturningGoodsStoreDvo dvo);

    int insertItmOstrIz(WsnaReturningGoodsStoreDvo dvo);

    int insertItmStrIz(WsnaReturningGoodsStoreDvo dvo);

    int updateSvItmStocIz(WsnaReturningGoodsStoreDvo dvo);

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

    List<WsnaReturningGoodsStoreDvo> selectLogisticsReturningGoodsAskInfo(String itmOstrNo, String ostrSn);

    //    int insertItmOstrAkIz(WsnaReturningGoodsStoreDvo dvo);
}
