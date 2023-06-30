package com.kyowon.sms.wells.web.closing.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDepositDelinquentDetailDto.SearchContractReq;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDepositDelinquentDetailDto.SearchContractRes;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDepositDelinquentDetailDto.SearchReq;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDepositDelinquentDetailDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WdcaDepositDelinquentDetailMapper {

    List<SearchRes> selectDepositDelinquentDetails(SearchReq dto);

    PagingResult<SearchContractRes> selectDepositDelinquentContractPages(SearchContractReq dto, PageInfo pageInfo);

    List<SearchContractRes> selectDepositDelinquentContractPages(SearchContractReq dto);

}