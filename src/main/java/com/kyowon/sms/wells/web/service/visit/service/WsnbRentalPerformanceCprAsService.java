package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbRentalPerformanceCprAsDto;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbRentalPerformanceCprAsMapper;

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
public class WsnbRentalPerformanceCprAsService {

    private final WsnbRentalPerformanceCprAsMapper mapper;

    public List<WsnbRentalPerformanceCprAsDto.SearchRes> getRentalPerformanceCprAs(
        WsnbRentalPerformanceCprAsDto.SearchReq dto
    ) {
        return this.mapper.selectRentalPerformanceCprAsPer(dto);
    }

}
