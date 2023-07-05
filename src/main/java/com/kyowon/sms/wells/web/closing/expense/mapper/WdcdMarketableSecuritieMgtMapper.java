package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritieMgtDto.*;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritieDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdMarketableSecuritieMgtMapper {

    List<FindCodeRes> selectBuilDingCd(FindCodeReq req);

    List<SearchSubjectRes> selectSubject(SearchSubjectReq req);

    List<SearchFinalSettlementRes> selectFinalWithholdingTaxSettlement(SearchFinalSettlementReq req);

    String selectCheckWhetherMonthFinalized(SaveReq req);

    List<AccCardInfoDetailRes> selectAccCardInfoDetail(SaveReq req);

    int updateAccMst(WdcdMarketableSecuritieDvo dvo);

    int deleteAccDetail(WdcdMarketableSecuritieDvo dvo);

    String selectOpcsAdjNo(WdcdMarketableSecuritieDvo dvo);

    int insertAccMst(WdcdMarketableSecuritieDvo dvo);

    int insertAccDetail(WdcdMarketableSecuritieDvo dvo);

    int updateOpcsCard(WdcdMarketableSecuritieDvo dvo);

    int insertAccMap(WdcdMarketableSecuritieDvo dvo);
}
