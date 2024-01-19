package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbSnProcessingPsDto.*;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbSnProcessingPsMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * <pre>
 * K-W-SV-U-0078M01 S/N 처리 현황
 * </pre>
 *
 * @author heymi.cho
 * @since 2023.08.18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbSnProcessingPsService {
    private final WsnbSnProcessingPsMapper mapper;

    public PagingResult<SearchCntrs> getSnProcessingPsCustomers(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectSnProcessingPsCntrs(dto, pageInfo);
    }

    public List<SearchCntrs> getSnProcessingPsCustomers(SearchReq dto) {
        return mapper.selectSnProcessingPsCntrs(dto);
    }

    public List<SearchRatio> getSnProcessingPsRatio(SearchReq dto) {
        return mapper.selectSnProcessingPsRatio(dto);
    }

    public List<SearchPuPartPdRes> selectSnProcessingPuPartPds(SearchPuPartPdReq dto) {
        return mapper.selectSnProcessingPsPuPartPds(dto);
    }

    public SearchCstSignCn getSnProcessingcstSignCn(String cstSvAsnNo) {
        return mapper.selectSnProcessingPsCstSignCn(cstSvAsnNo);
    }
}
