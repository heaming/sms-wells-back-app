package com.kyowon.sms.wells.web.contract.risk.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcRiskAuditDto.SearchReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcRiskAuditDto.SearchRes;
import com.kyowon.sms.wells.web.contract.risk.mapper.WctcRiskAuditMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctcRiskAuditService {
    private final WctcRiskAuditMapper mapper;

    public PagingResult<SearchRes> getIrregularBznsInqr(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectIrregularBznsInqr(dto, pageInfo);
    }

    public List<SearchRes> getIrregularBznsInqrExcelDownload(SearchReq dto) {
        return mapper.selectIrregularBznsInqr(dto);
    }

    @Transactional
    public int removeIrgBznsArbitArtc(List<String> dangChkIds) {
        int processCount = 0;
        int result = 0;
        for (Iterator<String> iterator = dangChkIds.iterator(); iterator.hasNext(); processCount += result) {
            String dangChkId = iterator.next();

            mapper.updateDangerCheckIz(dangChkId);
            mapper.updateDangerCheckChHist(dangChkId);
            result = mapper.insertDangerCheckChHist(dangChkId);
        }
        return processCount;
    }
}
