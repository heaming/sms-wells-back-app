package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemBaseInformationDto.*;

@Mapper
public interface WsnaItemBaseInformationMapper {

    List<SearchRes> selectItemBaseInformations(SearchReq dto);

    List<OstrRes> selectItemBaseInformationsOutOf(SearchReq dto);

    List<SearchAplcRes> selectItemBaseInformationAplcLists(SearchAplcReq dto);

    List<SearchWareRes> selectItemBaseInformationWareDvCds(SearchReq dto);
}
