package com.kyowon.sms.wells.web.fee.simulation.mapper;

import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfefEstimateFeeMgtMapper {
    BaseP selectBaseP(SearchEstimateReq req);
    List<PerformanceP> selectPerformanceP(SearchEstimateReq req);
    List<EstimateP> selectEstimateP(SearchEstimateReq req);
    List<SaleP> selectSaleP(SearchEstimateReq req);

    BaseM selectBaseM(SearchEstimateReq req);
    List<PerformanceM> selectPerformanceM(SearchEstimateReq req);
    List<BsM> selectBsM(SearchEstimateReq req);
    List<EstimateM> selectEstimateM(SearchEstimateReq req);
    List<SaleM> selectSaleM(SearchEstimateReq req);

    BaseHome selectBaseHome(SearchEstimateReq req);
    List<PerformanceHome> selectPerformanceHome(SearchEstimateReq req);
    List<EstimateHome> selectEstimateHome(SearchEstimateReq req);
    List<SaleHome> selectSaleHome(SearchEstimateReq req);

}
