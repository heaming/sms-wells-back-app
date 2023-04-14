package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritieMgtDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdMarketableSecuritieMgtMapper {

    List<FindCodeRes> selectBuilDingCd();

    List<SearchSubjectRes> selectSubject(SearchSubjectReq req);

    List<SearchFinalSettlementRes> selectFinalWithholdingTaxSettlement(SearchFinalSettlementReq req);
}
