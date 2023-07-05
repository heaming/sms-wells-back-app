package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritieExceptionMgttDto.*;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritieExceptionDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdMarketableSecuritieExceptionMgtMapper {

    List<FindCodeRes> selectBuilDingCd(FindCodeReq req);

    List<SearchSubjectRes> selectSubject(SearchSubjectReq req);

    List<SearchFinalSettlementRes> selectFinalWithholdingTaxSettlement(SearchFinalSettlementReq req);

    String selectCheckWhetherMonthFinalized(SaveReq req);

    String selectOpcsAdjNo(WdcdMarketableSecuritieExceptionDvo dvo);

    int insertAccMst(WdcdMarketableSecuritieExceptionDvo dvo);

    int insertAccDetail(WdcdMarketableSecuritieExceptionDvo dvo);

    int updateAccMst(WdcdMarketableSecuritieExceptionDvo dvo);

    int deleteAccDetail(WdcdMarketableSecuritieExceptionDvo dvo);

    int updateOpcsCard(WdcdMarketableSecuritieExceptionDvo dvo);

    int insertAccMap(WdcdMarketableSecuritieExceptionDvo dvo);

    List<AccCardInfoDetailRes> selectAccCardInfoDetail(SaveReq req);
}
