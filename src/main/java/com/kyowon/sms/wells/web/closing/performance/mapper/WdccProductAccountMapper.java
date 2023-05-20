package com.kyowon.sms.wells.web.closing.performance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchExcelRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchProductRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchTotalRes;

@Mapper
public interface WdccProductAccountMapper {

    List<SearchTotalRes> selectProductAccountTotals(SearchReq dto);

    List<SearchProductRes> selectProductAccounts(SearchReq dto);

    List<SearchExcelRes> selectProductAccountsExcelDownload(SearchReq dto);
}