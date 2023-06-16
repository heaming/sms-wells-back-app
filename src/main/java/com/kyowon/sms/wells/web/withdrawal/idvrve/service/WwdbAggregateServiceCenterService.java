package com.kyowon.sms.wells.web.withdrawal.idvrve.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAggregateServiceCenterDto.SearchAggregateEngineerOgRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAggregateServiceCenterDto.SearchAggregateServiceCenterReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAggregateServiceCenterDto.SearchAggregateServiceCenterRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAggregateServiceCenterDto.SearchAggregateServiceCenterTotalRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbAggregateServiceCenterMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbAggregateServiceCenterService {
    
    private final WwdbAggregateServiceCenterMapper mapper;

    /**
     * 입금집계현황-서비스센터 목록 조회
     * 
     * @param req
     * @param pageInfo
     * @return PagingResult
     */
    public PagingResult<SearchAggregateServiceCenterRes> getAggregateServiceCenters(
            SearchAggregateServiceCenterReq req, 
            PageInfo pageInfo
    ) {
        return mapper.selectAggregateServiceCenters(req, pageInfo);
    }

    /**
     * 입금집계현황-서비스센터 목록 조회 엑셀 다운로드
     * 
     * @param req
     * @return SearchAggregateServiceCenterRes
     */
    public List<SearchAggregateServiceCenterRes> getAggregateServiceCentersExcels(
        SearchAggregateServiceCenterReq req
    ) {
        return mapper.selectAggregateServiceCenters(req);
    }

    /**
     * 입금집계현황-서비스센터 목록 조회
     * 
     * @param req
     * @return SearchAggregateEngineerOgRes
     */
    public List<SearchAggregateEngineerOgRes> getServiceCenters(
            SearchAggregateServiceCenterReq req
    ) {
        return mapper.selectServiceCenters(req);
    }

    /**
     * 입금집계현황-서비스센터 목록 합계 조회
     * 
     * @param req
     * @return SearchAggregateServiceCenterTotalRes
     */
    public SearchAggregateServiceCenterTotalRes getAggregateServiceCentersTotal(
            SearchAggregateServiceCenterReq req
    ) {
        return mapper.selectAggregateServiceCentersTotal(req);
    }
    
}
