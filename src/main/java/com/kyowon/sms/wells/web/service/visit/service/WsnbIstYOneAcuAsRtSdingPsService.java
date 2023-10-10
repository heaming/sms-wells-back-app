package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sms.wells.web.service.visit.mapper.WsnbIstYOneAcuAsRtSdingPsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbIstYOneAcuAsRtSdingPsDto.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbIstYOneAcuAsRtSdingPsService {
    private final WsnbIstYOneAcuAsRtSdingPsMapper mapper;

    public List<SearchRes> getIstYOneAcuAsRtSdingPss(SearchReq dto){
        return mapper.selectIstYOneAcuAsRtSdingPss(dto);
    }
}
