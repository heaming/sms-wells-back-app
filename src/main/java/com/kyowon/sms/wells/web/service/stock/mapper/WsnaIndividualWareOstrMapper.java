package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndividualWareOstrDvo;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaIndividualWareOstrDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WsnaIndividualWareOstrMapper {

    List<SearchRes> selectIndividualWareOstrs(SearchReq dto);

    List<LogisticRes> selectLogistic(LogisticReq dto);

    List<ItmRes> selectItemKndCode(ItmReq dto);

    String selectNewOstrAkNoByQomOstr(@Param("ostrAkTpCd") String ostrAkTpCd);

    int insertTbSvstItmOstrAkIz(List<WsnaIndividualWareOstrDvo> list);
}
