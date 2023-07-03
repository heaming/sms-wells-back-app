package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdOperatingCostMgtDto.SearchAmountRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdOperatingCostMgtDto.SearchReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdOperatingCostMgtDto.SearchSummaryRes;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdOperatingCostDvo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WdcdOperatingCostMgtMapper {

    SearchAmountRes selectAmount(SearchReq req);

    SearchSummaryRes selectSummary(SearchReq req);

    int updateFile(WdcdOperatingCostDvo dvo);

}
