package com.kyowon.sms.wells.web.closing.sales.mapper;

import java.util.List;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.*;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbBusinessAtamAdjustDvo;

@Mapper
public interface WdcbBusinessAtamAdjustMgtMapper {
    List<SearchSapPdDvCdRes> selectSapPdDvCds();

    List<SearchTotalRes> selectBusinessAtamTotals(SearchReq dto);

    PagingResult<SearchTotalRes> selectBusinessAtamTotals(SearchReq dto, PageInfo pageInfo);

    SearchSummaryRes selectBusinessAtamSummary(SearchReq dto);

    List<SearchDetailRes> selectBusinessAtamDetails(SearchReq dto);

    PagingResult<SearchDetailRes> selectBusinessAtamDetails(SearchReq dto, PageInfo pageInfo);

    List<SearchSlpnoRes> selectSapAlrpySlpnos(String sapAlrpySlpno);

    int updateAllRepaymentBase(WdcbBusinessAtamAdjustDvo dvo);

    int updateDepositSlip(WdcbBusinessAtamAdjustDvo dvo);
}
