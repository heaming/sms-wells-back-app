package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAggregateServiceCenterDto.SearchAggregateEngineerOgRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAggregateServiceCenterDto.SearchAggregateServiceCenterReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAggregateServiceCenterDto.SearchAggregateServiceCenterRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAggregateServiceCenterDto.SearchAggregateServiceCenterTotalRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbAggregateServiceCenterMapper {

    PagingResult<SearchAggregateServiceCenterRes> selectAggregateServiceCenters(
        SearchAggregateServiceCenterReq req,
        PageInfo pageInfo
    );

    List<SearchAggregateServiceCenterRes> selectAggregateServiceCenters(
        SearchAggregateServiceCenterReq req
    );

    List<SearchAggregateEngineerOgRes> selectServiceCenters(
        SearchAggregateServiceCenterReq req
    );

    SearchAggregateServiceCenterTotalRes selectAggregateServiceCentersTotal(
        SearchAggregateServiceCenterReq req
    );
}
