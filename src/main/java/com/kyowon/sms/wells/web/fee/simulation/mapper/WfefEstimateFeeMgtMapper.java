package com.kyowon.sms.wells.web.fee.simulation.mapper;

import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfefEstimateFeeMgtMapper {
    BaseP selectBaseP(SearchOgPReq req);
    List<PerformanceP> selectPerformanceP(SearchOgPReq req);
    List<EstimateP> selectEstimateP(SearchOgPReq req);
    List<SaleP> selectSaleP(SearchOgPReq req);

    BaseM selectBaseM(SearchOgPReq req);
    List<PerformanceM> selectPerformanceM(SearchOgPReq req);
    List<BsM> selectBsM(SearchOgPReq req);
    List<EstimateM> selectEstimateM(SearchOgPReq req);
    List<SaleM> selectSaleM(SearchOgPReq req);


}
