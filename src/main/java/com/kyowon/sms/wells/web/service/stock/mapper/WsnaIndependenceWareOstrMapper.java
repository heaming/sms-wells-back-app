package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaIndependenceWareOstrDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndependenceWareOstrDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndependenceWareOstrLgstDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskReqDvo;

@Mapper
public interface WsnaIndependenceWareOstrMapper {

    List<SearchPdRes> selectProducts();

    List<WsnzWellsCodeWareHouseDvo> selectIndependenceStrWares(SearchWareReq dto);

    List<WsnaIndependenceWareOstrDvo> selectIndependenceWareOstrs(SearchReq dto);

    String selectOstrAkNoByQomAsn(WsnaIndependenceWareOstrDvo dvo);

    String selectNewOstrAkNo(String ostrAkTpCd);

    int mergeItmOstrAkIz(WsnaIndependenceWareOstrDvo dvo);

    int updateItmQomAsnIz(WsnaIndependenceWareOstrDvo dvo);

    List<WsnaLogisticsOutStorageAskReqDvo> selectIndependenceLogisticsTransfer(WsnaIndependenceWareOstrLgstDvo dvo);

}
