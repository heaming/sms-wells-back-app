package com.kyowon.sms.wells.closing.expense.service;

import com.kyowon.sms.wells.closing.expense.mapper.WOpcsWhtxAdjMscrMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WOpcsWhtxAdjMscrService {

    private final WOpcsWhtxAdjMscrMapper wOpcsWhtxAdjMscrMapper;
}
