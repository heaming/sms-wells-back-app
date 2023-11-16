package com.kyowon.sms.wells.web.closing.performance.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountHomeCardDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccProductAccountHomeCardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdccProductAccountHomeCardService {
    private final WdccProductAccountHomeCardMapper mapper;

    /**
     * 홈카드 계정순증
     * @return
     */
    public SearchRes getProductAccountHomeCard() {
        return mapper.selectProductAccountHomeCard();
    }
}
