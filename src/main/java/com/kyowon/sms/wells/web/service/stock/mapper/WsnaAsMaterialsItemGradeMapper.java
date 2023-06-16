package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialsItemGradeDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsMaterialsItemGradeDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsMaterialsItemGradeWareDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaAsMaterialsItemGradeMapper {

    PagingResult<WsnaAsMaterialsItemGradeDto.SearchRes> selectAsMaterialsItemGradePages(
        WsnaAsMaterialsItemGradeDto.SearchReq dto, PageInfo pageInfo
    );

    PagingResult<WsnaAsMaterialsItemGradeDto.SearchRes> selectAsMaterialsItemGradePagesForWare(
        WsnaAsMaterialsItemGradeDto.SearchReq dto, PageInfo pageInfo
    );

    Integer selectCstSvItmGdIzCount(WsnaAsMaterialsItemGradeDvo dvo);

    List<WsnaAsMaterialsItemGradeWareDvo> selectMcbyWareList();

    int insertCstSvItmGd(WsnaAsMaterialsItemGradeDvo dvo);

    int insertCstSvItmGdForSave(WsnaAsMaterialsItemGradeDvo dvo);

}
