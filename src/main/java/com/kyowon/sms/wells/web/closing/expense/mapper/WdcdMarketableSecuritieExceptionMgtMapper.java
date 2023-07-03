package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritieExceptionMgttDto.*;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritieExceptionDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdMarketableSecuritieExceptionMgtMapper {

    List<FindCodeRes> selectBuilDingCd();

    List<SearchSubjectRes> selectSubject(SearchSubjectReq req);

    List<SearchFinalSettlementRes> selectFinalWithholdingTaxSettlement(SearchFinalSettlementReq req);

    String selectCheckWhetherMonthFinalized(WdcdMarketableSecuritieExceptionDvo dvo);

    String selectOpcsAdjNo(WdcdMarketableSecuritieExceptionDvo dvo);

    int insertAccMst(WdcdMarketableSecuritieExceptionDvo dvo);

    int insertAccDetail(WdcdMarketableSecuritieExceptionDvo dvo);

    int updateAccMst(WdcdMarketableSecuritieExceptionDvo dvo);

    int deleteAccDetail(WdcdMarketableSecuritieExceptionDvo dvo);

    int updateOpcsCard(WdcdMarketableSecuritieExceptionDvo dvo);

    AccCardInfoDetailRes selectAccCardInfoDetail(WdcdMarketableSecuritieExceptionDvo dvo);
}
