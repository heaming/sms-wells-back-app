package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbDepositAggregateDtlServiceCenterDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbDepositAggregateDtlServiceCenterDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbDepositAggregateDtlServiceCenterDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbDepositAggregateDtlServiceCenterDto.SearchCodeRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbDepositAggregateDtlServiceCenterDto.SearchSumRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WwwdbDepositAggregateDtlServiceCenterMapper {

    PagingResult<SearchRes> selectDepositAggregateServiceCenters(SearchReq req, PageInfo pageInfo);

    List<SearchRes> selectDepositAggregateServiceCenters(SearchReq req);

    List<SearchCodeRes> selectCenterCodes(SearchReq req);

    SearchSumRes selectDepositAggregateServiceCentersTotal(SearchReq req);
}
