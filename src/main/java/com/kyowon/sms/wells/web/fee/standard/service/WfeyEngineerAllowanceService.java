package com.kyowon.sms.wells.web.fee.standard.service;

import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchOgPReq;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchOgPRes;
import com.kyowon.sms.wells.web.fee.simulation.mapper.WfefEstimateFeeMgtMapper;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto.SearchAllowanceUnitPriceReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto.SearchAllowanceUnitPriceRes;
import com.kyowon.sms.wells.web.fee.standard.mapper.WfeyEngineerAllowanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 * 엔지니어 단가 서비스
 * </pre>
 *
 * @author jingun jung
 * @since 2023.05.23
 */
@Service
@RequiredArgsConstructor
public class WfeyEngineerAllowanceService {

    private final WfeyEngineerAllowanceMapper engineerAllowanceMapper;

    /**
     * 엔지니어 수당 단가 록 조회
     * @param req
     * @return
     */
    public List<SearchAllowanceUnitPriceRes> getEngineerAwUprcs(SearchAllowanceUnitPriceReq req) {
        return engineerAllowanceMapper.selectEngienerAwUprcs(req);
    }

}
