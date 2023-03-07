package com.kyowon.sms.wells.web.organization.hmnrsc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseRes;
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

    public List<SearchLicenseRes> getPlannerLicenses(SearchLicenseReq dto) {
        return mapper.selectPlannerLicensePages(dto);
    }
    public PagingResult<SearchLicenseRes> getPlannerLicensePages(SearchLicenseReq dto, PageInfo pageinfo){
        return mapper.selectPlannerLicensePages(dto, pageinfo);
    }
    public List<SearchLicenseRes> getPlannerLicenseForExcelDownload(SearchLicenseReq dto) {
        return mapper.selectPlannerLicensePages(dto);
    }


}
