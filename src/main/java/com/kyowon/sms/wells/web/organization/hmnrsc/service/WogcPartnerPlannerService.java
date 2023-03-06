package com.kyowon.sms.wells.web.organization.hmnrsc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchPlannerLicenseReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchPlannerLicenseRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.mapper.WogcPartnerPlannerMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 *
 * <pre>
 *
 * </pre>
 *
 * @author 김동석
 * @since 2023-05-24
 */
@Service
@RequiredArgsConstructor
public class WogcPartnerPlannerService {

    private final WogcPartnerPlannerMapper mapper;

    public List<SearchPlannerLicenseRes> getPlannerLicenses(SearchPlannerLicenseReq dto) {
        return mapper.selectPlannerLicensePages(dto);
    }
    public PagingResult<SearchPlannerLicenseRes> getPlannerLicensePages(SearchPlannerLicenseReq dto, PageInfo pageinfo){
        return mapper.selectPlannerLicensePages(dto, pageinfo);
    }
    public List<SearchPlannerLicenseRes> getPlannerLicenseForExcelDownload(SearchPlannerLicenseReq dto) {
        return mapper.selectPlannerLicensePages(dto);
    }


}
