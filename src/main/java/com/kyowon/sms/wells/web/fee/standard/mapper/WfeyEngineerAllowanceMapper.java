package com.kyowon.sms.wells.web.fee.standard.mapper;

import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.*;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfeyEngineerAllowanceMapper {
    List<SearchAllowanceUnitPriceRes> selectEngienerAwUprcs(SearchAllowanceUnitPriceReq req);

    Integer insertEnginnerAwUprcs(CreateAllowanceUnitPriceReq req);
    Integer updateEnginnerAwUprcs(EditAllowanceUnitPriceReq req);
    Integer deleteEnginnerAwUprcs(RemoveAllowanceUnitPriceReq req);

    Integer updatePriorEnginnerAwUprcs(String pdGrpCd, String svTpCd, String siteAwAtcCd, String rglvlDvCd, Integer dsbBaseSn, String date);
}
