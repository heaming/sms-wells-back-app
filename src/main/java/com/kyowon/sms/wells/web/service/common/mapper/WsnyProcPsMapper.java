package com.kyowon.sms.wells.web.service.common.mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyProcPsDto.*;

import org.apache.ibatis.annotations.Mapper;

/**
 * <pre>
 * [W-SV-U-1301M01] 홈카드 - 처리 현황 Mapper
 * <pre>
 *
 * @author 김동엽
 * @since 2023.11.16
 */
@Mapper
public interface WsnyProcPsMapper {
    /**
     * 홈카드 처리 현황 조회
     * @param dto
     * @return
     */
    SearchRes selectProcPs(SearchReq dto);

    /**
     * 홈카드 오늘/내일 배정 건수 조회
     * @param dto
     * @return
     */
    SearchCnt selectTodayTomorrowCnt(SearchReq dto);
}
