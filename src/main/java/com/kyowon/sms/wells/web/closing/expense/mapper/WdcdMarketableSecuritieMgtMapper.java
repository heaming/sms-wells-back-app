package com.kyowon.sms.wells.web.closing.expense.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritieMgtDto.*;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritieDvo;

@Mapper
public interface WdcdMarketableSecuritieMgtMapper {

    List<FindCodeRes> selectBuilDingCd(FindCodeReq req);

    List<SearchSubjectRes> selectSubject(SearchSubjectReq req);

    List<SearchFinalSettlementRes> selectFinalWithholdingTaxSettlement(SearchFinalSettlementReq req);

    String selectCheckWhetherMonthFinalized(SaveReq req);

    AccCardInfoDetailRes selectAccCardInfoDetail(SaveReq req);

    int deleteAccDetail(WdcdMarketableSecuritieDvo dvo);

    int updateAccMst(WdcdMarketableSecuritieDvo dvo);

    String selectOpcsAdjNo(WdcdMarketableSecuritieDvo dvo);

    int insertAccMst(WdcdMarketableSecuritieDvo dvo);

    int insertAccDetail(WdcdMarketableSecuritieDvo dvo);

    int updateOpcsCard(WdcdMarketableSecuritieDvo dvo);

    int insertAccMap(WdcdMarketableSecuritieDvo dvo);
}
