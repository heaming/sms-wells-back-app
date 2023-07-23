package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbContractRefundDto.*;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbContractRefundMapper {

    PagingResult<SearchContractRefundRes> selectContractRefunds(
        SearchContractRefundReq req,
        PageInfo pageInfo
    );

    List<SearchContractRefundRes> selectContractRefunds(
        SearchContractRefundReq req
    );

    SearchContractRefundAggregateRes selectContractRefundAggregates(
        SearchContractRefundAggregateReq req
    );

    SearchContractRefundSummaryRes selectContractRefundSummary(
        SearchContractRefundReq req
    );

}
