package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdSecuritiesExceptionMgtDto.SearchAdjustObjectReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdSecuritiesExceptionMgtDto.SearchWithholdingTaxAdjustReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdSecuritiesExceptionMgtDto.SearchWithholdingTaxAdjustRes;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdSecuritiesExceptionDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdSecuritiesExceptionMgtMapper {

    List<WdcdSecuritiesExceptionDvo> selectAdjustObject(SearchAdjustObjectReq req);

    List<SearchWithholdingTaxAdjustRes> selectWithholdingTaxAdjust(SearchWithholdingTaxAdjustReq req);

    int editWithholdingTaxAdjust(WdcdSecuritiesExceptionDvo req);
}
