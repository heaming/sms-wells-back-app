package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaIndividualWareOstrDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndividualWareOstrDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaIndividualWareOstrMapper {

    List<SearchPdRes> selectProducts();

    List<WsnzWellsCodeWareHouseDvo> selectIndividualStrWares(SearchWareReq dto);

    PagingResult<WsnaIndividualWareOstrDvo> selectIndividualWareOstrs(SearchReq dto, PageInfo pageInfo);

    List<WsnaIndividualWareOstrDvo> selectIndividualWareOstrs(SearchReq dto);

    String selectNewOstrAkNoByQomOstr(@Param("ostrAkTpCd")
    String ostrAkTpCd);

    int insertTbSvstItmOstrAkIz(List<WsnaIndividualWareOstrDvo> list);
}
