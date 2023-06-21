package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialItemGradeDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsMaterialItemGradeDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsMaterialItemGradeWareDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaAsMaterialItemGradeMapper {

    List<WsnzWellsCodeWareHouseDvo> selectWareHouses(SearchWareReq dto);

    PagingResult<SearchRes> selectAsMaterialItemGradePages(
        SearchReq dto, PageInfo pageInfo
    );

    List<SearchRes> selectAsMaterialItemGradePages(SearchReq dto);

    PagingResult<SearchRes> selectAsMaterialItemGradePagesForWare(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectAsMaterialItemGradePagesForWare(SearchReq dto);

    Integer selectCstSvItmGdIzCount(WsnaAsMaterialItemGradeDvo dvo);

    List<WsnaAsMaterialItemGradeWareDvo> selectMcbyWares();

    int insertCstSvItmGd(WsnaAsMaterialItemGradeDvo dvo);

    int insertCstSvItmGdForSave(WsnaAsMaterialItemGradeDvo dvo);

}
