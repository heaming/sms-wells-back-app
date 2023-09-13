package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositAggregateDtlServiceCenterDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositAggregateDtlServiceCenterDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositAggregateDtlServiceCenterDto.SearchCodeRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositAggregateDtlServiceCenterDto.SearchSumRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WwdbDepositAggregateDtlServiceCenterMapper {

    PagingResult<SearchRes> selectDepositAggregateServiceCenters(SearchReq req, PageInfo pageInfo);

    List<SearchRes> selectDepositAggregateServiceCenters(SearchReq req);

    List<SearchCodeRes> selectCenterCodes(SearchReq req);

    SearchSumRes selectDepositAggregateServiceCentersTotal(SearchReq req);
}
