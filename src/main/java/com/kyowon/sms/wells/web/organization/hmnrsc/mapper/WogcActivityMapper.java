package com.kyowon.sms.wells.web.organization.hmnrsc.mapper;

import com.kyowon.sms.common.web.organization.hmnrsc.dto.ZogcPartnerOutputDto.*;
import com.kyowon.sms.common.web.organization.hmnrsc.dvo.ZogcPartnerOutputDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcActivityDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import static com.kyowon.sms.common.web.organization.hmnrsc.dto.ZogcPartnerOutputDto.*;
import static com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcActivityDto.*;

/**
 * <pre>
 * 활동 현황 mapper
 * </pre>
 *
 * @author 유현웅
 * @since 2023-07-03
 */
@Mapper
public interface WogcActivityMapper {
    List<SearchMonthlyActivityRes> searchMonthlyActivitiesS(
        SearchMonthlyActivityReq dto
    );

    PagingResult<SearchMonthlyActivityRes> searchMonthlyActivitiesS(
        SearchMonthlyActivityReq dto, PageInfo pageInfo
    );

    List<SearchMonthlyActivityRes> searchMonthlyActivitiesE(
        SearchMonthlyActivityReq dto
    );

    PagingResult<SearchMonthlyActivityRes> searchMonthlyActivitiesE(
        SearchMonthlyActivityReq dto, PageInfo pageInfo
    );

    List<SearchMonthlyActivityRes> searchMonthlyActivitiesA(
        SearchMonthlyActivityReq dto
    );

    PagingResult<SearchMonthlyActivityRes> searchMonthlyActivitiesA(
        SearchMonthlyActivityReq dto, PageInfo pageInfo
    );

    List<SearchAccureActivityRes> searchAccureActivitiesA(
        SearchAccureActivityReq dto
    );

    PagingResult<SearchAccureActivityRes> searchAccureActivitiesA(
        SearchAccureActivityReq dto, PageInfo pageInfo
    );

    List<SearchAccureActivityRes> searchAccureActivitiesI(
        SearchAccureActivityReq dto
    );

    PagingResult<SearchAccureActivityRes> searchAccureActivitiesI(
        SearchAccureActivityReq dto, PageInfo pageInfo
    );
}
