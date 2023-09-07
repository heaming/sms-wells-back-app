package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemBaseInformationDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemBaseInformationDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemBaseInformationReturnDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemBaseInformationSearchDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaItemBaseInformationMapper {

    PagingResult<WsnaItemBaseInformationReturnDvo> selectItemBaseInformations(
        WsnaItemBaseInformationSearchDvo searchDvo, PageInfo pageInfo
    );

    List<SearchAplcRes> selectItemBaseInformationAplcLists(SearchAplcReq dto);

    List<SearchWareRes> selectItemBaseInformationWareDvCds(SearchReq dto);

    PagingResult<WsnaItemBaseInformationDvo> selectItemBaseInformationsOutOf(
        WsnaItemBaseInformationSearchDvo searchDvo, PageInfo pageInfo
    );

    String selectOstrWareDvCd(SearchReq dto);
}
