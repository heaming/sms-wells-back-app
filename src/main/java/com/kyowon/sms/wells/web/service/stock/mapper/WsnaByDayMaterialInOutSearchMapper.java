package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaByDayMaterialInOutSearchDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaByDayMaterialInOutSearchDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaByDayMaterialInOutSearchMapper {

    PagingResult<WsnaByDayMaterialInOutSearchDvo> selectByDayMaterialInOutSearchPages(
        SearchReq searchReq, PageInfo pageInfo
    );

    List<WsnaByDayMaterialInOutSearchDvo> selectByDayMaterialInOutSearchPages(
        SearchReq searchReq
    );

    int deleteOutOfStorageAskItemization(WsnaByDayMaterialInOutSearchDvo dvo);
}
