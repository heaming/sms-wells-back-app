package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaIndividualWareOstrDto.*;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndividualWareOstrDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsDeliveryKssDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskReqDvo;

@Mapper
public interface WsnaIndividualWareOstrMapper {

    List<SearchPdRes> selectProducts();

    List<WsnzWellsCodeWareHouseDvo> selectIndividualStrWares(SearchWareReq dto);

    List<WsnaIndividualWareOstrDvo> selectIndividualWareOstrs(SearchReq dto);

    int updateItmQomAsnIz(WsnaIndividualWareOstrDvo dvo);

    String selectOstrAkNoByQomAsn(WsnaIndividualWareOstrDvo dvo);

    String selectNewOstrAkNo(String ostrAkTpCd);

    BigDecimal selectItemByOstrAggQty(WsnaIndividualWareOstrDvo dvo);

    int mergeItmOstrAkIz(WsnaIndividualWareOstrDvo dvo);

    List<WsnaLogisticsOutStorageAskReqDvo> selectIndividualLogisticsTransfer(WsnaLogisticsDeliveryKssDvo dvo);

    List<WsnaIndividualWareOstrDvo> selectLogisticsTransferDatas(SearchTranferReq dto);
}
