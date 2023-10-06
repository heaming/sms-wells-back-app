package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemBaseInformationDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemBaseInformationDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemBaseInformationReturnDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemBaseInformationSearchDvo;

@Mapper
public interface WsnaItemBaseInformationMapper {

    List<WsnaItemBaseInformationReturnDvo> selectItemBaseInformations(WsnaItemBaseInformationSearchDvo searchDvo);

    List<SearchAplcRes> selectItemBaseInformationAplcLists(SearchAplcReq dto);

    List<SearchWareRes> selectItemBaseInformationWareDvCds(SearchReq dto);

    List<WsnaItemBaseInformationDvo> selectItemBaseInformationsOutOf(WsnaItemBaseInformationSearchDvo searchDvo);

    String selectOstrWareDvCd(SearchReq dto);
}
