package com.kyowon.sms.wells.closing.expense.service;

import com.kyowon.sms.wells.closing.expense.mapper.WwdcdOperatingCostMgtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WwdcdMarketableSecuritiesMgtService {

    private final WwdcdOperatingCostMgtMapper wOpcsWhtxAdjMscrMapper;
}
