package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingTruckArrangementMapDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingTruckArrangementMapDto.SearchSeedAgg;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingTruckArrangementMapDto.SearchTodayGgLct;

/**
 *
 * <pre>
 * 입고 미승인현황
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023.06.20
 */
@Mapper
public interface WsnaSeedingTruckArrangementMapMapper {
    /**
     * 기준일 출고 센터 조회
     *
     * @param dto : SearchReq(baseDt - 기준일)
     * @return 조회결과
     */
    List<SearchTodayGgLct> selectTodayGgLct(
        SearchReq dto
    );

    /**
     * 센터별 모종 집계 조회
     *
     * @param dto : SearchReq(baseDt - 기준일)
     * @return 조회결과
     */
    List<SearchSeedAgg> selectSeedAggregation(
        SearchReq dto
    );
}
