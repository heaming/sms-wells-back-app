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
@Slf4j
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
        switch (dto.rsbDvCd()){
            case "S":
                return wogcActivityMapper.searchMonthlyActivitiesS(dto);
            case "E":
                return wogcActivityMapper.searchMonthlyActivitiesE(dto);
            case "A":
                return wogcActivityMapper.searchMonthlyActivitiesA(dto);
            default:
                return null;
        }
    }

    /**
     * 월별 활동 현황 목록 조회
     * @param dto
     * @param pageInfo
     * @return SearchMonthlyActivityRes
     */
    public PagingResult<SearchMonthlyActivityRes> searchMonthlyActivitiesPages(SearchMonthlyActivityReq dto, PageInfo pageInfo) {
        switch (dto.rsbDvCd()){
            case "S":
                return wogcActivityMapper.searchMonthlyActivitiesS(dto, pageInfo);
            case "E":
                return wogcActivityMapper.searchMonthlyActivitiesE(dto, pageInfo);
            case "A":
                return wogcActivityMapper.searchMonthlyActivitiesA(dto, pageInfo);
            default:
                return null;
        }
    }

    /**
     * 누적 활동 현황 목록 조회
     * @param dto
     * @return SearchAccureActivityRes
     */
    public List<SearchAccureActivityRes> searchAccureActivities(SearchAccureActivityReq dto) {
        List<SearchAccureActivityRes> resultLists = wogcActivityMapper.searchAccureActivities(dto);

        return resultLists;
    }

    /**
     * 누적 활동 현황 목록 조회
     * @param dto
     * @param pageInfo
     * @return SearchMonthlyActivityRes
     */
    public PagingResult<SearchAccureActivityRes> searchAccureActivitiesPages(SearchAccureActivityReq dto, PageInfo pageInfo) {
        return wogcActivityMapper.searchAccureActivities(dto, pageInfo);
    }
}
