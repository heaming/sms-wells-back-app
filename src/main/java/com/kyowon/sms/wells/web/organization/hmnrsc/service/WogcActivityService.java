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
        if(dto.ogTpCd().equals("W01")){ // P추진
            return wogcActivityMapper.searchMonthlyActivitiesP(dto);
        }else{ // M 추진
            return wogcActivityMapper.searchMonthlyActivitiesM(dto);
        }
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
        if(dto.ogTpCd().equals("W01")){ // P추진
            return wogcActivityMapper.searchMonthlyActivitiesP(dto, pageInfo);
        }else{ // M 추진
            return wogcActivityMapper.searchMonthlyActivitiesM(dto, pageInfo);
        }
    }

        /**
     * 월별 활동 현황 목록 조회용 순주문 Flag 조회.
     * @param dto
     * @return String
     */
    public String selectCountFeamCntr(
        SearchMonthlyActivityReq dto
    ) {
        return wogcActivityMapper.selectCountFeamCntr(dto);
    }


    /**
     * 누적 활동 현황 목록 조회
     * @param dto
     * @return SearchAccureActivityRes
     */
    public List<SearchAccureActivityRes> searchAccureActivities(SearchAccureActivityReq dto) {
        return wogcActivityMapper.searchAccureActivities(dto);
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
        return wogcActivityMapper.searchAccureActivities(dto, pageInfo);
    }
}
