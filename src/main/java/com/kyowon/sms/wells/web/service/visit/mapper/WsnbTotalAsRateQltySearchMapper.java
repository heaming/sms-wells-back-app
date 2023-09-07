package com.kyowon.sms.wells.web.service.visit.mapper;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbTotalAsRateQltySearchDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbTotalAsRateQltySearchDvo;

/**
 * <pre>
 * W-SV-U-0248M01 총 A/S율 현황(품질)
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.09.01
 */
@Mapper
public interface WsnbTotalAsRateQltySearchMapper {
    /**
     * 총 A/S율 현황(품질)
     *
     * @param searchReq 조회조건
     * @return 조회결과
     */
    List<WsnbTotalAsRateQltySearchDvo> selectTotalAsRateQltyList(SearchReq searchReq);
}
