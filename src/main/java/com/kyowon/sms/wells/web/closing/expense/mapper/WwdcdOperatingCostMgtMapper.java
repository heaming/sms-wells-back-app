package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WwdcdOperatingCostMgtDto;
import com.kyowon.sms.wells.web.closing.expense.dto.WwdcdOperatingCostMgtDto.SearchReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WwdcdOperatingCostMgtDto.SearchRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WwdcdOperatingCostMgtMapper {

    SearchRes selectOperatingCostAmount(SearchReq dto);

    SearchRes selectOperatingCostSummary(SearchReq dto);

    void insertWithholdingTaxCfdc(WwdcdOperatingCostMgtDto.FileReq req);

    WwdcdOperatingCostMgtDto.SaveRes selectWithholdingTaxCfdc(WwdcdOperatingCostMgtDto.SaveReq req);

}
