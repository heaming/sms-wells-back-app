package com.kyowon.sms.wells.web.closing.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDepositDelinquentDto.SearchReq;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDepositDelinquentDto.SearchRes;

@Mapper
public interface WdcaDepositDelinquentMapper {

    List<SearchRes> selectDepositDelinquents(SearchReq dto);

}