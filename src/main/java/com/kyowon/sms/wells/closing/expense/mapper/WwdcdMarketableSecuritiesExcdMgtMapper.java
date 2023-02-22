package com.kyowon.sms.wells.closing.expense.mapper;

import com.kyowon.sms.wells.closing.expense.dto.WOpcsWhtxAdjMscrExcdDto;

public interface WOpcsWhtxAdjMscrExcdMapper {

    String selectRgstMngt(WOpcsWhtxAdjMscrExcdDto.SearchReq dto);
}
