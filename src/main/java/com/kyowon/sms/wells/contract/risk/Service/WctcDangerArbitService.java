package com.kyowon.sms.wells.contract.risk.Service;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.contract.risk.dto.WctcDangerArbitDto.RemoveReq;
import com.kyowon.sms.wells.contract.risk.dto.WctcDangerArbitDto.SearchReq;
import com.kyowon.sms.wells.contract.risk.dto.WctcDangerArbitDto.SearchRes;
import com.kyowon.sms.wells.contract.risk.mapper.WctcDangerArbitMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctcDangerArbitService {
    private final WctcDangerArbitMapper mapper;

    @Transactional
    public List<SearchRes> getIrgBznsArbitArtc(SearchReq dto) {
        return mapper.selectIrgBznsArbitArtc(dto);
    }

    @Transactional
    public int removeIrgBznsArbitArtc(List<RemoveReq> dtos) {
        int processCount = 0;
        int result = 0;
        for (Iterator<RemoveReq> iterator = dtos.iterator(); iterator.hasNext(); processCount += result) {
            RemoveReq DangerCheckIzList = iterator.next();
            mapper.updateDangerCheckIz(DangerCheckIzList.dangChkId());
            mapper.updateDangerCheckChHist(DangerCheckIzList.dangChkId());
            result = mapper.insertDangerCheckChHist(DangerCheckIzList.dangChkId());
        }
        return processCount;
    }
}
