package com.kyowon.sms.wells.web.service.orgcode.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelPdlvMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelPdlvMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndPlaceOfDeliveryDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsndRegionLevelPdlvMgtMapper {

    PagingResult<SearchRes> selectPlaceOfDeliveryPages(
        SearchReq dto, PageInfo pageInfo
    );

    List<SearchRes> selectPlaceOfDeliveryPages(
        SearchReq dto
    );

    int deletePlaceOfDelivery(String pdlvNo, String pdlvDvCd);

    int insertPlaceOfDelivery(WsndPlaceOfDeliveryDvo dvo);

    int insertPlaceOfDeliveryHistory(WsndPlaceOfDeliveryDvo dvo);

    WsndPlaceOfDeliveryDvo selectPlaceOfDeliveryByPk(String pdlvNo, String pdlvDvCd);

    int updatePlaceOfDelivery(WsndPlaceOfDeliveryDvo dvo);

    int updatePlaceOfDeliveryHistory(WsndPlaceOfDeliveryDvo dvo);
}
