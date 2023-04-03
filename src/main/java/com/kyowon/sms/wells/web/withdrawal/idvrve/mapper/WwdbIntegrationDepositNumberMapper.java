package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbIntegrationDepositNumberDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbIntegrationDepositNumberDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbIntegrationDepositNumberMapper {

    PagingResult<SearchRes> selectIntegrationDepositNumbers(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectIntegrationDepositNumbers(SearchReq dto);
}
