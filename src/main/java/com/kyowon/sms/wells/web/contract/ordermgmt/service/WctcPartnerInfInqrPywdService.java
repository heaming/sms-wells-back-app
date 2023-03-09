package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctcPartnerInfInqrPywdDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctcPartnerInfInqrPywdMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctcPartnerInfInqrPywdService {
    private final WctcPartnerInfInqrPywdMapper mapper;

    public WctcPartnerInfInqrPywdDvo getPartnerInfInqrPywd(String cntrNo) {

        return mapper.selectPartnerInfInqrPywd(cntrNo);
    }
}
