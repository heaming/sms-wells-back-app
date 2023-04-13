package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdOperatingCostMgtDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdOperatingCostMgtMapper {
    
    List<SearchAmountRes> selectAmount(SearchReq req);

    List<SearchSummaryRes> selectSummary(SearchReq req);

    void insertWithholdingTaxCfdc(FileReq req);

    SaveRes selectWithholdingTaxCfdc(SaveReq req);

}
