package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiFreeASPeriodDto.FindReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiFreeASPeriodDto.FindRes;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiFreeASPeriodMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiFreeASPeriodService {

    private final WctiFreeASPeriodMapper mapper;

    public FindRes getFreeASPeriod(FindReq dto) {
        FindRes res = mapper.selectFreeASPeriodFromEx(dto) ;

        if(null == res)
            res = mapper.selectFreeASPeriod(dto);

        return res;
    }
}
