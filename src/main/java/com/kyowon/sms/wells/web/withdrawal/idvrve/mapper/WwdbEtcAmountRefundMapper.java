package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAmountRefundDto.*;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbEtcAmountRefundMapper {

    PagingResult<SearchEtcAmountRefundRes> selectEtcAmountRefunds(
        SearchEtcAmountRefundReq req,
        PageInfo pageInfo
    );

    List<SearchEtcAmountRefundRes> selectEtcAmountRefunds(
        SearchEtcAmountRefundReq req
    );

    SearchEtcAmountRefundAggregateRes selectEtcAmountRefundAggregates(
        SearchEtcAmountRefundAggregateReq req
    );

    SearchEtcAmountRefundSummaryRes selectEtcAmountRefundsSummary(
        SearchEtcAmountRefundReq req
    );

}
