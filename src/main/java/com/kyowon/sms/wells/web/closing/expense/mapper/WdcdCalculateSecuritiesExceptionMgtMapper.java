package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCalculateSecuritiesExceptionMgtDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdCalculateSecuritiesExceptionMgtMapper {

    List<CodeRes> selectBuilDingCd();

    List<SubjectRes> selectSubject(SubjectReq req);

    List<finalWithholdingTaxSettlementRes> selectFinalWithholdingTaxSettlement(finalWithholdingTaxSettlementReq req);
}
