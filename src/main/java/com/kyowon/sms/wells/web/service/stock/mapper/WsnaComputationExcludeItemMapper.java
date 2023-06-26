package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaComputationExcludeItemDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaComputationExcludeItemDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaComputationExcludeItemDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaComputationExcludeItemPdDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaComputationExcludeItemMapper {

    List<WsnaComputationExcludeItemPdDvo> selectProducts();

    PagingResult<SearchRes> selectCmptExcdItms(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectCmptExcdItms(SearchReq dto);

    int insertCmptExcdItm(WsnaComputationExcludeItemDvo dvo);

    int updateCmptExcdItm(WsnaComputationExcludeItemDvo dvo);

    int updateCmptExcdItmForRemove(WsnaComputationExcludeItemDvo dvo);

    Integer selectCmptExcdItmCount(String inqrYm, int period);

    int insertCmptExcdItmForTransfer(String inqrYm);

}
