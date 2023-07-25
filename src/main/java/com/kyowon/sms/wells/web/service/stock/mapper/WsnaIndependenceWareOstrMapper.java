package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaIndependenceWareOstrDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndependenceWareOstrDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaIndependenceWareOstrMapper {

    List<SearchPdRes> selectProducts();

    List<WsnzWellsCodeWareHouseDvo> selectIndependenceStrWares(SearchWareReq dto);

    PagingResult<WsnaIndependenceWareOstrDvo> selectIndependenceWareOstrs(SearchReq dto, PageInfo pageInfo);

    List<WsnaIndependenceWareOstrDvo> selectIndependenceWareOstrs(SearchReq dto);

    String selectOstrAkNoByQomAsn(WsnaIndependenceWareOstrDvo dvo);

    String selectNewOstrAkNo(String ostrAkTpCd);

    int mergeItmOstrAkIz(WsnaIndependenceWareOstrDvo dvo);

    int updateItmQomAsnIz(WsnaIndependenceWareOstrDvo dvo);

}
