package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdSecuritiesMgtDto.FindReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdSecuritiesMgtDto.SearchAdjustObjectReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdSecuritiesMgtDto.SearchWithholdingTaxAdjustReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdSecuritiesMgtDto.SearchWithholdingTaxAdjustRes;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdSecuritiesDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdSecuritiesMgtMapper {

    List<WdcdSecuritiesDvo> selectAdjustObject(SearchAdjustObjectReq req);

    List<SearchWithholdingTaxAdjustRes> selectWithholdingTaxAdjust(SearchWithholdingTaxAdjustReq req);

    int editWithholdingTaxAdjust(WdcdSecuritiesDvo req);

    String selectWithholdingTax(FindReq req);
}
