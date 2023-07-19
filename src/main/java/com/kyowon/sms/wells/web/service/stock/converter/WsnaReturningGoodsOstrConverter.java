package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskReqDvo;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrDto.RemoveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrDto.ReturningGoods;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReturningGoodsDvo;

/**
 * <pre>
 * W-SV-U-0001M01 반품출고 등록
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.02.14
 */
@Mapper(componentModel = "spring")
public interface WsnaReturningGoodsOstrConverter {

    List<ReturningGoods> mapAllReturningGoodsDvoToReturningGoods(List<WsnaReturningGoodsDvo> dvos);

    WsnaReturningGoodsDvo mapSaveReqToReturningGoodsDvo(SaveReq dto);

    WsnaReturningGoodsDvo mapRemoveReqToReturningGoodsDvo(RemoveReq dto);

    List<WsnaLogisticsInStorageAskReqDvo> mapAllReturningGoodsDvoToLogisticsInStorageAskReqDvo(
        List<WsnaReturningGoodsDvo> dvos
    );

    List<WsnaLogisticsInStorageAskReqDvo> mapAllRemoveReturningGoodsDvoToLogisticsInStorageAskReqDvo(
        List<WsnaReturningGoodsDvo> dvos
    );

}
