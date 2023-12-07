package com.kyowon.sms.wells.web.organization.hmnrsc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseDetailRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerPlannerDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerPlannerQualificationDvo;
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

    List<SearchLicenseRes> selectPlannerLicensePages(SearchLicenseReq dto);

    PagingResult<WogcPartnerPlannerDto.SearchRes> selectTopPlannerPages(
        WogcPartnerPlannerDto.SearchReq dto, PageInfo pageinfo
    );

    List<WogcPartnerPlannerDto.SearchRes> selectTopPlannerPages(WogcPartnerPlannerDto.SearchReq dto);

    int insertOneTopPlanner(WogcPartnerPlannerDvo planner);

    int insertTwoTopPlanner(WogcPartnerPlannerDvo planner);

    int updateMmPartner(WogcPartnerPlannerDvo planner);

    int updateDtlPartner(WogcPartnerPlannerDvo planner);

    int insertAdTopPlanner(WogcPartnerPlannerDvo planner);

    int updateAdMmPartner(WogcPartnerPlannerDvo planner);

    int updateAdDtlPartner(WogcPartnerPlannerDvo planner);

    int updateADTopPlannerUpgradeMonth(WogcPartnerPlannerDvo planner);

    WogcPartnerPlannerDto.FindRes selectTopPlannerByPk(String ogTpCd, String prtnrNo, String mngtYm);

    WogcPartnerPlannerDto.FindRes selectMmPlannerByPk(String ogTpCd, String prtnrNo, String mngtYm);

    PagingResult<SearchLicenseDetailRes> selectPlannerLicenseDetailPages(String prtnrNo, PageInfo pageinfo);

    List<SearchLicenseDetailRes> selectPlannerLicenseDetailPages(String prtnrNo);

    int insertPlannerQualificationChange(WogcPartnerPlannerQualificationDvo dvo);

    int updatePlannerQualificationChange(WogcPartnerPlannerQualificationDvo dvo);

    int selectFeamOrderCnt(WogcPartnerPlannerDto.SearchCheckReq dto);

    int selectQuaCreateCnt(WogcPartnerPlannerDto.SearchCheckReq dto);

}
