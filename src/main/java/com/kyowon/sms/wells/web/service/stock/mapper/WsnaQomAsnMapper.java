package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnDto.SearchWareReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaQomAsnCreateDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaQomAsnIndividualSearchDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaQomAsnWareRenewalDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaQomAsnMapper {

    List<WsnzWellsCodeWareHouseDvo> selectQomAsnOstrWares(String apyYm);

    List<WsnzWellsCodeWareHouseDvo> selectQomAsnStrWares(SearchWareReq dto);

    Integer selectQomAsnCount(SearchReq dto);

    int insertQomAsnFirstTnIndividuals(WsnaQomAsnCreateDvo dvo);

    int insertQomAsnIndividuals(WsnaQomAsnCreateDvo dvo);

    int insertQomAsnIndependence(WsnaQomAsnCreateDvo dvo);

    int selectItmQomAsnNoMax(String asnOjYm, String wareDtlDvCd);

    PagingResult<WsnaQomAsnIndividualSearchDvo> selectQomAsnsForIndividual(
        SearchReq dto, PageInfo pageInfo
    );

    List<WsnaQomAsnIndividualSearchDvo> selectQomAsnsForIndividual(SearchReq dto);

    PagingResult<WsnaQomAsnIndividualSearchDvo> selectQomAsnsForIndependence(SearchReq dto, PageInfo pageInfo);

    List<WsnaQomAsnIndividualSearchDvo> selectQomAsnsForIndependence(SearchReq dto);

    int updateRgbsPuItmForWareRenewal(WsnaQomAsnWareRenewalDvo dvo);

}
