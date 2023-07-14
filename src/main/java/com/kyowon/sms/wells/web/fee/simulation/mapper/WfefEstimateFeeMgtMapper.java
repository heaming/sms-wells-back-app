package com.kyowon.sms.wells.web.fee.simulation.mapper;

import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfefEstimateFeeMgtMapper {
    String selectUserDvCd(String perfYm, String ogTpCd, String sellPrtnrNo);

    BaseP selectBaseP(SearchEstimateReq req, String userDvCd);
    MeetingP selectMeetingP(SearchEstimateReq req, String userDvCd);
    List<PerformanceP> selectPerformanceP(SearchEstimateReq req, String userDvCd);
    EstimateP selectEstimateP(SearchEstimateReq req, String userDvCd);
    List<SaleP> selectSaleP(SearchEstimateReq req, String userDvCd);

    BaseM selectBaseM(SearchEstimateReq req, String userDvCd);
    MeetingM selectMeetingM(SearchEstimateReq req, String userDvCd);
    List<PerformanceM> selectPerformanceM(SearchEstimateReq req, String userDvCd);
    List<BsM> selectBsM(SearchEstimateReq req, String userDvCd);
    List<OgBsM> selectOgBsM(SearchEstimateReq req, String userDvCd);
    EstimateM selectEstimateM(SearchEstimateReq req, String userDvCd);
    List<SaleM> selectSaleM(SearchEstimateReq req, String userDvCd);

    BaseHome selectBaseHome(SearchEstimateReq req);
    List<PerformanceHome> selectPerformanceHome(SearchEstimateReq req);
    List<EstimateHome> selectEstimateHome(SearchEstimateReq req);
    List<SaleHome> selectSaleHome(SearchEstimateReq req);

}
