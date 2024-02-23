package com.kyowon.sms.wells.web.fee.simulation.mapper;

import com.kyowon.sms.common.web.fee.standard.dvo.ZfeyTargetPartnerConditionDvo;
import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebGridMetgDvo;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WfefEstimateFeeMgtMapper {
    String selectUserDvCd(String perfYm, String ogTpCd, String sellPrtnrNo);

    BaseP selectBaseP(SearchEstimateReq req, String userDvCd);
    List<MeetingP> selectMeetingP(SearchEstimateReq req, String userDvCd);
    List<PerformanceP> selectPerformanceP(SearchEstimateReq req, String userDvCd);
    List<EstimatePM> selectEstimatePM(SearchEstimateReq req, String ogTpCd, List<ZfeyTargetPartnerConditionDvo> feeCds);
    List<SaleP> selectSaleP(SearchEstimateReq req, String userDvCd);

    BaseM selectBaseM(SearchEstimateReq req, String userDvCd);
    List<Map> selectMeetingM(SearchEstimateReq req, String userDvCd, String qlfDvCd, List<WfebGridMetgDvo> meetingPerfAtcCds, String pivotColumn);
    List<PerformanceM> selectPerformanceM(SearchEstimateReq req, String userDvCd);
    List<BsM> selectBsM(SearchEstimateReq req, String userDvCd);
    List<OgBsM> selectOgBsM(SearchEstimateReq req, String userDvCd);
    List<SaleM> selectSaleM(SearchEstimateReq req, String userDvCd);

    BaseHome selectBaseHome(SearchEstimateReq req);
    List<PerformanceHome> selectPerformanceHome(SearchEstimateReq req);
    List<ServicePerformanceHome> selectServicePerformanceHome(SearchEstimateReq req);
    List<Map<String, Object>> selectEstimateHome(SearchEstimateReq req, List<ZfeyTargetPartnerConditionDvo> feeCds, String pivotColums);
    List<SaleHome> selectSaleHome(SearchEstimateReq req);

}
