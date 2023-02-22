package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WwdcdMarketableSecuritiesExcdMgtDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WwdcdMarketableSecuritiesExcdMgtMapper {

    String selectRgstMngt(WwdcdMarketableSecuritiesExcdMgtDto.SearchReq dto);
}
