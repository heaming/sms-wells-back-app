package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAdvanceRefundAccountDto.SearchAdvanceRefundAccountReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAdvanceRefundAccountDto.SearchAdvanceRefundAccountRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbAdvanceRefundAccountMapper {

    PagingResult<SearchAdvanceRefundAccountRes> selectAdvanceRefundAccount(
        SearchAdvanceRefundAccountReq req,
        PageInfo pageInfo
    );

    List<SearchAdvanceRefundAccountRes> selectAdvanceRefundAccount(
        SearchAdvanceRefundAccountReq req
    );

}
