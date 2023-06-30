package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbDepositAggregateDtlServiceCenterDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbDepositAggregateDtlServiceCenterDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbDepositAggregateDtlServiceCenterDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbDepositAggregateDtlServiceCenterDto.SearchCodeRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbDepositAggregateDtlServiceCenterDto.SearchSumRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwwdbDepositAggregateDtlServiceCenterMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WwwdbDepositAggregateDtlServiceCenterService {

    private final WwwdbDepositAggregateDtlServiceCenterMapper mapper;

    public PagingResult<SearchRes> getDepositAggregateServiceCenterPages(
        SearchReq req, PageInfo pageInfo
    ) {
        return mapper.selectDepositAggregateServiceCenters(req, pageInfo);
    }

    public List<SearchRes> getDepositAggregateServiceCenterExcels(
        SearchReq req
    ) {
        return mapper.selectDepositAggregateServiceCenters(req);
    }

    public List<SearchCodeRes> getCenterCodes(
        SearchReq req
    ) {
        return mapper.selectCenterCodes(req);
    }

    public SearchSumRes getDepositAggregateServiceCentersTotal(
        SearchReq req
    ) {
        return mapper.selectDepositAggregateServiceCentersTotal(req);
    }

}
