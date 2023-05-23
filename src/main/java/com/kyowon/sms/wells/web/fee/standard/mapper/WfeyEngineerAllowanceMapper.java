package com.kyowon.sms.wells.web.fee.standard.mapper;

import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.*;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto.SearchAllowanceUnitPriceReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto.SearchAllowanceUnitPriceRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfeyEngineerAllowanceMapper {
    List<SearchAllowanceUnitPriceRes> selectEngienerAwUprcs(SearchAllowanceUnitPriceReq req);
}
