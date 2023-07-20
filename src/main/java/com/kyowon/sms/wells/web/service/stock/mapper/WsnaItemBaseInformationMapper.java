package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemBaseInformationDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemBaseInformationReturnDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemBaseInformationSearchDvo;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemBaseInformationDto.*;

@Mapper
public interface WsnaItemBaseInformationMapper {

    List<WsnaItemBaseInformationReturnDvo> selectItemBaseInformations(WsnaItemBaseInformationSearchDvo searchDvo);

    //    List<OstrRes> selectItemBaseInformationsOutOf(SearchReq dto);

    List<SearchAplcRes> selectItemBaseInformationAplcLists(SearchAplcReq dto);

    List<SearchWareRes> selectItemBaseInformationWareDvCds(SearchReq dto);

    List<WsnaItemBaseInformationDvo> selectItemBaseInformationsOutOf(WsnaItemBaseInformationSearchDvo searchDvo);

    String selectOstrWareDvCd(SearchReq dto);
}
