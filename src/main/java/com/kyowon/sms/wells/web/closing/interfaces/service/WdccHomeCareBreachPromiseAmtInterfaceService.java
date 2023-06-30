package com.kyowon.sms.wells.web.closing.interfaces.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.interfaces.dto.WdccHomeCareBreachPromiseAmtInterfaceDto.FindReq;
import com.kyowon.sms.wells.web.closing.interfaces.dto.WdccHomeCareBreachPromiseAmtInterfaceDto.FindRes;
import com.kyowon.sms.wells.web.closing.interfaces.mapper.WdccHomeCareBreachPromiseAmtInterfaceMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WdccHomeCareBreachPromiseAmtInterfaceService {
    private final WdccHomeCareBreachPromiseAmtInterfaceMapper mapper;

    public FindRes getOtherLumpSumPerformance(FindReq req) {
        return mapper.selectHomeCareBreachPromiseAmt(req);
    }
}
