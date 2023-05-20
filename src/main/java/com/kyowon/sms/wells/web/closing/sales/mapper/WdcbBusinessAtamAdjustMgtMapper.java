package com.kyowon.sms.wells.web.closing.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.SearchDetailRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.SearchSapPdDvCdRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.SearchSlpnoRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.SearchTotalRes;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbBusinessAtamAdjustDvo;

@Mapper
public interface WdcbBusinessAtamAdjustMgtMapper {
    List<SearchSapPdDvCdRes> selectSapPdDvCds();

    List<SearchTotalRes> selectBusinessAtamTotals(SearchReq dto);

    List<SearchDetailRes> selectBusinessAtamDetails(SearchReq dto);

    List<SearchSlpnoRes> selectSapAlrpySlpnos(String sapAlrpySlpno);

    int updateAllRepaymentBase(WdcbBusinessAtamAdjustDvo dvo);

    int updateDepositSlip(WdcbBusinessAtamAdjustDvo dvo);
}
