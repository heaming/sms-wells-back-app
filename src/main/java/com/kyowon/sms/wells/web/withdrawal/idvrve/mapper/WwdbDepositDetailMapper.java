package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbDepositDetailSearchDvo;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositDetailDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositDetailDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbDepositDetailMapper {

    PagingResult<SearchRes> selectDepositDetail(WwdbDepositDetailSearchDvo dVo, PageInfo pageInfo);

    List<SearchRes> selectDepositDetail(WwdbDepositDetailSearchDvo dVo);

}
