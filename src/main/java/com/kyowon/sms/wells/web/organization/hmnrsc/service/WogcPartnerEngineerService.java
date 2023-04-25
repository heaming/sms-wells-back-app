package com.kyowon.sms.wells.web.organization.hmnrsc.service;

import com.kyowon.sms.wells.web.organization.hmnrsc.mapper.WogcPartnerEngineerMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SearchEngineerRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SearchEngineerReq;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 김동석
 * @since 2023-05-24
 */
@Service
@RequiredArgsConstructor
public class WogcPartnerEngineerService {
    private final WogcPartnerEngineerMapper mapper;

    public PagingResult<SearchEngineerRes> getEngineerAttends(SearchEngineerReq dto, PageInfo pageInfo) {
        return mapper.selectEngineerAttends(dto, pageInfo);
    }

    public List<SearchEngineerRes> getEngineerAttendsForExcelDownload(SearchEngineerReq dto) {
        return mapper.selectEngineerAttends(dto);
    }
}
