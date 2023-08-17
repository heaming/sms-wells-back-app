package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaEgerAllowanceDto;

/**
 * <pre>
 * 엔지니어 실적 집계
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Mapper
public interface WfeaEgerAllowanceMapper {

    int selectEngineerPerformanceCount(WfeaEgerAllowanceDto.SaveReq dto);

}
