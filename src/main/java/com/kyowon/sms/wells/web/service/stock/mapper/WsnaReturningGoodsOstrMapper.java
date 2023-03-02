package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrDto.ItemOutOfStorage;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrDto.SearchWarehouseReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrDto.SearchWarehouseRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReturningGoodsDvo;

/**
 * <pre>
 * W-SV-U-0001M01 반품출고 등록
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.02.14
 */
@Mapper
public interface WsnaReturningGoodsOstrMapper {

    List<SearchWarehouseRes> selectWareHouses(SearchWarehouseReq dto);

    List<WsnaReturningGoodsDvo> selectReturningGoodsOstrs(String itmOstrNo);

    ItemOutOfStorage selectItemOutOfStorage(String itmOstrNo);

    String selectIsClosedByPk(String itmOstrNo);

    int insertItemForwardingHistory(WsnaReturningGoodsDvo dvo);

    int insertItemReceivingHistory(WsnaReturningGoodsDvo dvo);

    int deleteItemForwardingHistory(WsnaReturningGoodsDvo dvo);

    int deleteItemReceivingHistory(WsnaReturningGoodsDvo dvo);

}
