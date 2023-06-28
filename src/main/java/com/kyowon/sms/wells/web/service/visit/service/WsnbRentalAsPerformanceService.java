package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbRentalAsPerformanceDto;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbRentalAsPerformanceMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-U-0032M01 렌탈 실적 대비 A/S율 집계 현황
 * </pre>
 *
 * @author 홍세기
 * @since 2023.06.26
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbRentalAsPerformanceService {

    private final WsnbRentalAsPerformanceMapper mapper;

    public List<WsnbRentalAsPerformanceDto.SearchRes> getRentalPerformanceCprAs(
        WsnbRentalAsPerformanceDto.SearchReq dto
    ) {
        return this.mapper.selectRentalPerformanceCprAsPer(dto);
    }

}
