package com.kyowon.sms.wells.contract.risk.Service;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.contract.risk.converter.WctcRiskAuditConverter;
import com.kyowon.sms.wells.contract.risk.dto.WctcRiskAuditDto.SearchReq;
import com.kyowon.sms.wells.contract.risk.dto.WctcRiskAuditDto.SearchRes;
import com.kyowon.sms.wells.contract.risk.dvo.WctcRiskAuditDvo;
import com.kyowon.sms.wells.contract.risk.mapper.WctcRiskAuditMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctcRiskAuditService {
    private final WctcRiskAuditMapper mapper;
    private final WctcRiskAuditConverter converter;

    @Transactional
    public List<SearchRes> getIrregularBznsInqr(SearchReq dto) {
        return mapper.selectIrregularBznsInqr(dto);
    }

    @Transactional
    public int removeIrgBznsArbitArtc(List<String> dangChkIds) {
        int processCount = 0;
        int result = 0;
        for (Iterator<String> iterator = dangChkIds.iterator(); iterator.hasNext(); processCount += result) {
            String dangChkId = iterator.next();
            WctcRiskAuditDvo dangerArbit = converter.mapSaveReqWctcRiskAuditDvo(dangChkId);
            mapper.updateDangerCheckIz(dangChkId);
            mapper.updateDangerCheckChHist(dangChkId);
            result = mapper.insertDangerCheckChHist(dangerArbit);
        }
        return processCount;
    }
}
