package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbIstYOneAcuAsRtPsDto.*;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbIstYOneAcuAsRtPsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbIstYOneAcuAsRtPsService {
    private final WsnbIstYOneAcuAsRtPsMapper mapper;

    public List<SearchRes> getIstYOneAcuAsRtPss(SearchReq dto){
        return mapper.selectIstYOneAcuAsRtPss(dto);
    }
}
