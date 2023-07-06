package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaIndependenceWareOstrDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaIndependenceWareOstrDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndependenceWareOstrDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WsnaIndependenceWareOstrMapper {

    PagingResult<SearchRes> selectIndependenceWareOstrs(SearchReq dto, PageInfo pageInfo);

    int insertTbSvstItmOstrAkIz(List<WsnaIndependenceWareOstrDvo> voList);

    int insertTbIfinItmOstrAkSendEtxt(List<WsnaIndependenceWareOstrDvo> voList);

    String selectNewOstrAkNoByQomOstr(@Param("ostrAkTpCd") String ostrAkTpCd);
}
