package com.kyowon.sms.wells.web.deduction.burden.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.deduction.burden.dto.WdeeRealTimeDlqAdamtDto;
import com.kyowon.sms.wells.web.deduction.burden.mapper.WdeeRealTimeDlqAdamtMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdeeRealTimeDlqAdamtService {

    private final WdeeRealTimeDlqAdamtMapper mapper;

    public List<WdeeRealTimeDlqAdamtDto.SearchRes> getRealTimeDelinquentAdamt(WdeeRealTimeDlqAdamtDto.SearchReq dtos) {
        return mapper.selectRealTimeDlqAdamt(dtos);
    }

}
