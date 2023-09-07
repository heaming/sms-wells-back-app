package com.kyowon.sms.wells.web.closing.expense.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdOperatingCostMgtDto.*;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdOperatingCostDvo;

@Mapper
public interface WdcdOperatingCostMgtMapper {

    SearchAmountRes selectAmount(SearchAmountReq req);

    SearchSummaryRes selectSummary(SearchSummaryReq req);

    int updateFile(WdcdOperatingCostDvo dvo);

    FindOrganizationLevelRes selectOrganizationLevel();
}
