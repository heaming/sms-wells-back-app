package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnDto.CreateReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnDto.SearchReq;

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

    List<WsnzWellsCodeWareHouseDvo> selectQomAsnStrWares(String apyYm, String wareDvCd, String wareDtlDvCd);

    Integer selectQomAsnCount(String asnOjYm, int cnt);

    int selectItmQomAsnNoMax(CreateReq dto);

    int insertQomAsnFirstCntForIndividual(WsnaQomAsnCreateDvo dvo);

    int insertQomAsnForIndividual(WsnaQomAsnCreateDvo dvo);

    PagingResult<WsnaQomAsnIndividualSearchDvo> selectQomAsnsForIndividual(
        SearchReq dto, PageInfo pageInfo
    );

    List<WsnaQomAsnIndividualSearchDvo> selectQomAsnsForIndividual(SearchReq dto);

    int updateRgbsPuItmForWareRenewal(WsnaQomAsnWareRenewalDvo dvo);

}
