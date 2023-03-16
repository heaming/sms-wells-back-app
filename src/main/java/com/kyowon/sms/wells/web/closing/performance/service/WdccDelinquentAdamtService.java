package com.kyowon.sms.wells.web.closing.performance.service;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccDelinquentAdamtDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccDelinquentAdamtDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccDelinquentAdamtMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WdccDelinquentAdamtService {

    private final WdccDelinquentAdamtMapper mapper;

    @GetMapping
    public List<SearchRes> getDelinquentAdamt(
        @Valid SearchReq req
    ) {
        return mapper.selectDelinquentAdamt(req);
    }
}
