package com.kyowon.sms.wells.closing.expense.mapper;

import com.kyowon.sms.wells.closing.expense.dto.WOpcsRgstMngtDto.SearchReq;
import com.kyowon.sms.wells.closing.expense.dvo.WOpcsRgstMngtDvo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WOpcsRgstMngtMapper {
    WOpcsRgstMngtDvo selectOperatingCostSummaryPresentState(SearchReq dto);

    WOpcsRgstMngtDvo selectOperatingCostAmountPresentState(SearchReq dto);

    WOpcsRgstMngtDvo selectOperatingCostSummaryPresentStateTab(SearchReq dto);

    WOpcsRgstMngtDvo selectOperatingCostAmountPresentStateTab(SearchReq dto);
}
