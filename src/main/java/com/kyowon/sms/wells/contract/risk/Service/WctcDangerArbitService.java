package com.kyowon.sms.wells.contract.risk.Service;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.contract.risk.converter.WctcDangerArbitConverter;
import com.kyowon.sms.wells.contract.risk.dto.WctcDangerArbitDto.SearchReq;
import com.kyowon.sms.wells.contract.risk.dto.WctcDangerArbitDto.SearchRes;
import com.kyowon.sms.wells.contract.risk.dvo.WctcDangerArbitDvo;
import com.kyowon.sms.wells.contract.risk.mapper.WctcDangerArbitMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctcDangerArbitService {
    private final WctcDangerArbitMapper mapper;
    private final WctcDangerArbitConverter converter;

    @Transactional
    public List<SearchRes> getIrgBznsArbitArtc(SearchReq dto) {
        return mapper.selectIrgBznsArbitArtc(dto);
    }

    @Transactional
    public int removeIrgBznsArbitArtc(List<String> dangChkIds) {
        int processCount = 0;
        int result = 0;
        for (Iterator<String> iterator = dangChkIds.iterator(); iterator.hasNext(); processCount += result) {
            String dangChkId = iterator.next();
            WctcDangerArbitDvo dangerArbit = converter.mapSaveReqWctcDangerArbitDvo(dangChkId);
            mapper.updateDangerCheckIz(dangChkId);
            mapper.updateDangerCheckChHist(dangChkId);
            result = mapper.insertDangerCheckChHist(dangerArbit);
        }
        return processCount;
    }
}
