package com.kyowon.sms.wells.web.closing.expense.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritieExceptionMgttDto.*;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritieExceptionDvo;

@Mapper
public interface WdcdMarketableSecuritieExceptionMgtMapper {

    List<FindCodeRes> selectBuilDingCd(FindCodeReq req);

    List<SearchSubjectRes> selectSubject(SearchSubjectReq req);

    List<SearchFinalSettlementRes> selectFinalWithholdingTaxSettlement(SearchFinalSettlementReq req);

    String selectCheckWhetherMonthFinalized(SaveReq req);

    AccCardInfoDetailRes selectAccCardInfoDetail(SaveReq req);

    int deleteAccDetail(WdcdMarketableSecuritieExceptionDvo dvo);

    int updateAccMst(WdcdMarketableSecuritieExceptionDvo dvo);

    String selectOpcsAdjNo(WdcdMarketableSecuritieExceptionDvo dvo);

    int insertAccMst(WdcdMarketableSecuritieExceptionDvo dvo);

    int insertAccDetail(WdcdMarketableSecuritieExceptionDvo dvo);

    int updateOpcsCard(WdcdMarketableSecuritieExceptionDvo dvo);

    int insertAccMap(WdcdMarketableSecuritieExceptionDvo dvo);
}
