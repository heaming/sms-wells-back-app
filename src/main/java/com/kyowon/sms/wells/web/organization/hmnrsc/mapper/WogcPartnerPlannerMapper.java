package com.kyowon.sms.wells.web.organization.hmnrsc.mapper;

import java.util.List;

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

}
