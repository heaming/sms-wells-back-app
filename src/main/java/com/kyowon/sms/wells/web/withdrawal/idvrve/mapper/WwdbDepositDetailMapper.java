package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositDetailDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositDetailDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbDepositDetailMapper {

    PagingResult<SearchRes> selectDepositDetail(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectDepositDetail(SearchReq dto);
}
