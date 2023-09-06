package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbIstYOneAcuAfSvRtPsDto;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbIstYOneAcuAfSvRtPsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WsnbIstYOneAcuAfSvRtPsService {
    private final WsnbIstYOneAcuAfSvRtPsMapper mapper;

    public List<WsnbIstYOneAcuAfSvRtPsDto.SearchRes> getIstYOneAcuAfSvRtPsList(WsnbIstYOneAcuAfSvRtPsDto.SearchReq dto){
        return mapper.selectIstYOneAcuAfSvRtPsList(dto);
    }
}
