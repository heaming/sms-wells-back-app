package com.kyowon.sms.wells.web.fee.simulation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.simulation.dto.WfefBfsvcEtPerfDto.SearchReq;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefBfsvcEtPerfDto.SearchRes;
import com.kyowon.sms.wells.web.fee.simulation.mapper.WfefBfsvcEtPerfMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * BS 예상 실적 조회
 * </pre>
 *
 * @author seoin.jeon
 * @since 2023.09.25
 */
@Service
@RequiredArgsConstructor
public class WfefBfsvcEtPerfService {

    private final WfefBfsvcEtPerfMapper mapper;

    /**
     * BS 예상 실적 조회
     * @param dto
     * @return
     */
    public List<SearchRes> getBsEstimatePerformance(SearchReq dto) {
        return mapper.selectBsEstimatePerformance(dto);
    }

}
