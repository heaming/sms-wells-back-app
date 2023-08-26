package com.kyowon.sms.wells.web.service.visit.mapper;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbTaskTypeItemSearchDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbTaskTypeItemSearchDvo;

/**
 *
 * <pre>
 * K-W-SV-U-0075M01 업무유형별 품목 현황
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.10
 */
@Mapper
public interface WsnbTaskTypeItemSearchMapper {
    /**
     * 업무유형별 품목 현황 조회
     *
     * @param searchReq 조회조건
     * @return 조회결과
     */
    List<WsnbTaskTypeItemSearchDvo> selectTaskTypeItemList(SearchReq searchReq);
}
