package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallationYOneAcuAfSvRtDto.*;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbInstallationYOneAcuAfSvRtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WsnbInstallationYOneAcuAfSvRtService {
    private final WsnbInstallationYOneAcuAfSvRtMapper mapper;


    public List<SearchRes> getInstallationYOneAcuAfSvRtInfo(SearchReq dto){
        return mapper.selectInstallationYOneAcuAfSvRtInfos(dto);
    }
}
