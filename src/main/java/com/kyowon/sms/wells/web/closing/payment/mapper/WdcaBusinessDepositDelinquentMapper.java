package com.kyowon.sms.wells.web.closing.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.payment.dto.WdcaBusinessDepositDelinquentDto.SearchReq;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaBusinessDepositDelinquentDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WdcaBusinessDepositDelinquentMapper {

    PagingResult<SearchRes> selectBusinessDepositDelinquentPages(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectBusinessDepositDelinquentPages(SearchReq dto);

}