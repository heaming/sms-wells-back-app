package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbRentalPerformanceCprAsDto;

/**
 * <pre>
 * W-SV-U-0032M01 렌탈 실적 대비 A/S율 집계 현황
 * </pre>
 *
 * @author 홍세기
 * @since 2023.06.26
 */
@Mapper
public interface WsnbRentalPerformanceCprAsMapper {

    List<WsnbRentalPerformanceCprAsDto.SearchRes> selectRentalPerformanceCprAsPer(
        WsnbRentalPerformanceCprAsDto.SearchReq dto
    );
}
