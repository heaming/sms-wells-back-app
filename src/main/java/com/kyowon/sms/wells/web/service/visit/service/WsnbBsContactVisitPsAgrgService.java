package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsContactVisitPsAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsContactVisitPsAgrgDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbBsContactVisitPsAgrgMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnbBsContactVisitPsAgrgService {

    private final WsnbBsContactVisitPsAgrgMapper mapper;

    public List<SearchRes> getBsContactVisitPsAgrgs(
        SearchReq dto
    ) {
        return mapper.selectBsContactVisitPsAgrgs(dto);
    }
}
