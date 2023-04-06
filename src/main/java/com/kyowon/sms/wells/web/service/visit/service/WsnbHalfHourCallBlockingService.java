package com.kyowon.sms.wells.web.service.visit.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.visit.mapper.WsnbHalfHourCallBlockingMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnbHalfHourCallBlockingService {
    private final WsnbHalfHourCallBlockingMapper mapper;

    @Transactional
    public int saveHalfHourCallBlocking() {
        return mapper.insertHalfHourCallBlocking();
    }
}
