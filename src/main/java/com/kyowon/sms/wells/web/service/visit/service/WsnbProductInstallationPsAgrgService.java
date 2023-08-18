package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbProductInstallationPsAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbProductInstallationPsAgrgDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbProductInstallationPsAgrgMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnbProductInstallationPsAgrgService {

    private final WsnbProductInstallationPsAgrgMapper mapper;

    public List<SearchRes> getProductInstallationPsAgrgs(SearchReq dto) {
        return mapper.selectProductInstallationPsAgrgs(dto);
    }

}
