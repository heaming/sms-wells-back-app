package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WwdcdOperatingCostMgtSecuritiesDto.SearchReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WwdcdOperatingCostMgtSecuritiesDto.SearchRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WwdcdOperatingCostMgtSecuritiesMapper {

    List<SearchRes> selectAdjustObjectExceptionPages(SearchReq dto);
}
