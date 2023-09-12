package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbMobileHappyCallAgrgDto.*;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbMobileHappyCallAgrgMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbMobileHappyCallAgrgService {
    private final WsnbMobileHappyCallAgrgMapper mapper;

    public List<SearchRes> getMobileHappyCallAgrgs(SearchReq dto){
        return mapper.selectMobileHappyCallAgrgs(dto);
    }
}
