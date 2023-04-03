package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbIntegrationDepositNumberInquiryDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbIntegrationDepositNumberInquiryDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwwdbIntegrationDepositNumberInquiryMapper {

    PagingResult<SearchRes> selectIntegrationDepositNumberInquiryPages(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectIntegrationDepositNumberInquiryPages(SearchReq dto);
}
