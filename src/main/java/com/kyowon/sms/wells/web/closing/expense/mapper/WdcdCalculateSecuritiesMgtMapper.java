package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCalculateSecuritiesMgtDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdCalculateSecuritiesMgtMapper {

    List<CodeRes> selectBuilDingCd();

    List<SubjectRes> selectSubject(SubjectReq req);

    List<finalWithholdingTaxSettlementRes> selectFinalWithholdingTaxSettlement(finalWithholdingTaxSettlementReq req);
}
