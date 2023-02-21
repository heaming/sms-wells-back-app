package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaOgNetOrderDvo;

/**
 * <pre>
 * 조직별 실적 집계
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Mapper
public interface WfeaOgNetOrderMapper {

    int deleteBsPerformances(WfeaOgNetOrderDvo dvo);

    int insertBsPerformances(WfeaOgNetOrderDvo dvo);

}
