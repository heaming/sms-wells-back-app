package com.kyowon.sms.wells.web.service.orgcode.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelPdlvMngtDto.SaveReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelPdlvMngtDto.SearchReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelPdlvMngtDto.SearchRes;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndPlaceOfDeliveryDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsndRegionLevelPdlvMngtMapper {

    PagingResult<SearchRes> selectPlaceOfDeliveryPages(
        SearchReq dto, PageInfo pageInfo
    );

    List<SearchRes> selectPlaceOfDeliveryPages(
        SearchReq dto
    );

    int deletePlaceOfDelivery(WsndPlaceOfDeliveryDvo dvo);

    int insertPlaceOfDelivery(WsndPlaceOfDeliveryDvo dvo);

    int insertPlaceOfDeliveryHistory(WsndPlaceOfDeliveryDvo dvo);

    SearchRes selectPlaceOfDeliveryByPdlvNo(SaveReq dto);

    int updatePlaceOfDelivery(WsndPlaceOfDeliveryDvo dvo);

    int updatePlaceOfDeliveryHistory(WsndPlaceOfDeliveryDvo dvo);
}
