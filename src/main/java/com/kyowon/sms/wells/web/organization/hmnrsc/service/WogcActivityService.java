package com.kyowon.sms.wells.web.organization.hmnrsc.service;

import com.kyowon.sms.wells.web.organization.hmnrsc.mapper.WogcActivityMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcActivityDto.*;

/**
 *
 * <pre>
 * 활동 현황 Service
 * </pre>
 *
 * @author 유현웅
 * @since 2023-07-03
 */
@Service
@RequiredArgsConstructor
public class WogcActivityService {
    private final WogcActivityMapper wogcActivityMapper;

    /**
     * 월별 활동 현황 목록 조회
     * @param dto
     * @return SearchMonthlyActivityRes
     */
    public List<SearchMonthlyActivityRes> searchMonthlyActivities(SearchMonthlyActivityReq dto) {
        return switch (dto.rsbDvCd()) {
            case "S" -> wogcActivityMapper.searchMonthlyActivitiesS(dto);
            case "E" -> wogcActivityMapper.searchMonthlyActivitiesE(dto);
            default -> wogcActivityMapper.searchMonthlyActivitiesA(dto);
        };
    }

    /**
     * 월별 활동 현황 목록 조회
     * @param dto
     * @param pageInfo
     * @return SearchMonthlyActivityRes
     */
    public PagingResult<SearchMonthlyActivityRes> searchMonthlyActivitiesPages(
        SearchMonthlyActivityReq dto, PageInfo pageInfo
    ) {
        return switch (dto.rsbDvCd()) {
            case "S" -> wogcActivityMapper.searchMonthlyActivitiesS(dto, pageInfo);
            case "E" -> wogcActivityMapper.searchMonthlyActivitiesE(dto, pageInfo);
            default -> wogcActivityMapper.searchMonthlyActivitiesA(dto, pageInfo);
        };
    }

    /**
     * 누적 활동 현황 목록 조회
     * @param dto
     * @return SearchAccureActivityRes
     */
    public List<SearchAccureActivityRes> searchAccureActivities(SearchAccureActivityReq dto) {
        if ("A".equals(dto.perfCd())) {
            return wogcActivityMapper.searchAccureActivitiesA(dto);
        } else {
            return wogcActivityMapper.searchAccureActivitiesI(dto);
        }
    }

    /**
     * 누적 활동 현황 목록 조회
     * @param dto
     * @param pageInfo
     * @return SearchMonthlyActivityRes
     */
    public PagingResult<SearchAccureActivityRes> searchAccureActivitiesPages(
        SearchAccureActivityReq dto, PageInfo pageInfo
    ) {
        if ("A".equals(dto.perfCd())) {
            return wogcActivityMapper.searchAccureActivitiesA(dto, pageInfo);
        } else {
            return wogcActivityMapper.searchAccureActivitiesI(dto, pageInfo);
        }
    }
}
