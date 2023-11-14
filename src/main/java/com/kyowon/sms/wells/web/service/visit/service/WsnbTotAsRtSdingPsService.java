package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sms.wells.web.service.visit.mapper.WsnbTotAsRtSdingPsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbTotAsRtSdingPsDto.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbTotAsRtSdingPsService {
    private final WsnbTotAsRtSdingPsMapper mapper;

    public List<SearchRes> getTotAsRtSdingPss(SearchReq dto){
        return mapper.selectTotAsRtSdingPss(dto);
    }

    public List<SdingPackageRes> getSdingPackage(){
        return mapper.selectSdingPackages();
    }

    public List<SdingDtlRes> getSdingDtlInfo(SdingDtlReq dtlDto){
        return mapper.selectSdingDtlInfos(dtlDto);
    }
}
