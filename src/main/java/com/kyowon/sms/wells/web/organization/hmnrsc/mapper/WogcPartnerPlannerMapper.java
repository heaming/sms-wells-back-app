package com.kyowon.sms.wells.web.organization.hmnrsc.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerPlannerDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerPlannerQualificationDvo;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseDetailRes;
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

    int selectCountMmPartner(WogcPartnerPlannerDvo planner);

    int selectCountPlarPartner(WogcPartnerPlannerDvo planner);

    int selectCountTopPlarPartner(WogcPartnerPlannerDvo planner);

    int insertTopPlanner(WogcPartnerPlannerDvo planner);

    int updateMmPartner(WogcPartnerPlannerDvo planner);

    int updateDtlPartner(WogcPartnerPlannerDvo planner);

    int updateAdMmPartner(WogcPartnerPlannerDvo planner);

    int updateAdDtlPartner(WogcPartnerPlannerDvo planner);

    WogcPartnerPlannerDto.FindRes selectTopPlannerByPk(String ogTpCd, String prtnrNo, String mngtYm);

    PagingResult<SearchLicenseDetailRes> selectPlannerLicenseDetailPages(String prtnrNo, PageInfo pageinfo);

    List<SearchLicenseDetailRes> selectPlannerLicenseDetailPages(String prtnrNo);

    int insertPlannerQualificationChange(WogcPartnerPlannerQualificationDvo dvo);

    int updatePlannerQualificationChange(WogcPartnerPlannerQualificationDvo dvo);

    int selectFeamOrderCnt(WogcPartnerPlannerDto.SearchCheckReq dto);

    int selectQuaCreateCnt(WogcPartnerPlannerDto.SearchCheckReq dto);

}
