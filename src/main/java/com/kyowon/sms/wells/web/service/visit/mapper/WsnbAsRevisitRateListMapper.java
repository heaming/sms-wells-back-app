package com.kyowon.sms.wells.web.service.visit.mapper;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbAsRevisitRateListDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbAsRevisitRateListDvo;

/**
 *
 * <pre>
 * K-W-SV-U-0100M01 A/S 재방문율 조회
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.07.28
 */
@Mapper
public interface WsnbAsRevisitRateListMapper {
    /**
     * A/S 재방문율 조회
     *
     * @param searchReq 조회조건
     * @return 조회결과
     */
    List<WsnbAsRevisitRateListDvo> selectAsRevisitRateList(SearchReq searchReq);

}
