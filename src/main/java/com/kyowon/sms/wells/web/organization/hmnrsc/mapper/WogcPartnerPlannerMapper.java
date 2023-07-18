package com.kyowon.sms.wells.web.organization.hmnrsc.mapper;

import java.util.List;

import com.kyowon.sms.common.web.organization.organization.dto.ZogaBuildingDto;
import com.kyowon.sms.common.web.organization.organization.dto.ZogaBuildingSalesDto;
import com.kyowon.sms.common.web.organization.organization.dvo.ZogaBuildingDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerPlannerDvo;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 김동석
 * @since 2023-05-24
 */
@Mapper
public interface WogcPartnerPlannerMapper {

    PagingResult<SearchLicenseRes> selectPlannerLicensePages(SearchLicenseReq dto, PageInfo pageinfo);

    List<SearchLicenseRes> selectPlannerLicensePages(SearchLicenseReq dto);

    PagingResult<WogcPartnerPlannerDto.SearchRes> selectTopPlannerPages(
        WogcPartnerPlannerDto.SearchReq dto, PageInfo pageinfo
    );

    List<WogcPartnerPlannerDto.SearchRes> selectTopPlannerPages(WogcPartnerPlannerDto.SearchReq dto);

    int deleteTopPlanner(WogcPartnerPlannerDvo planner);

    int selectCountMmPartner(WogcPartnerPlannerDvo planner);

    int selectCountPlarPartner(WogcPartnerPlannerDvo planner);

    int selectCountTopPlarPartner(WogcPartnerPlannerDvo planner);

    int insertTopPlanner(WogcPartnerPlannerDvo planner);

    int updateMmPartner(WogcPartnerPlannerDvo planner);

    int updateDtlPartner(WogcPartnerPlannerDvo planner);

    int updateTopPlanner(WogcPartnerPlannerDvo planner);

    int updateAdMmPartner(WogcPartnerPlannerDvo planner);

    int updateAdDtlPartner(WogcPartnerPlannerDvo planner);

    WogcPartnerPlannerDto.FindRes selectTopPlannerByPk(String bldCd, String gridOgTpCd);

}
