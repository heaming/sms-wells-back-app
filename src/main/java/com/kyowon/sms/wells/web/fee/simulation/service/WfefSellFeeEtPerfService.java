package com.kyowon.sms.wells.web.fee.simulation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.simulation.dto.WfefSellFeeEtPerfDto.SearchReq;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefSellFeeEtPerfDto.SearchRes;
import com.kyowon.sms.wells.web.fee.simulation.mapper.WfefSellFeeEtPerfMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 수수료 예상 실적 조회 (판매)
 * </pre>
 *
 * @author LEE SUNHO
 * @since 2023.10.10
 */
@Service
@RequiredArgsConstructor
public class WfefSellFeeEtPerfService {

    private final WfefSellFeeEtPerfMapper mapper;

    public List<SearchRes> getSellFeeEtPerf(SearchReq dto) {
        return mapper.getSellFeeEtPerf(dto);
    }
}
