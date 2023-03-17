package com.kyowon.sms.wells.web.closing.performance.service;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccDelinquentAdditionalChargesDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccDelinquentAdditionalChargesDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccDelinquentAdditionalChargesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WdccDelinquentAdditionalChargesService {

    private final WdccDelinquentAdditionalChargesMapper mapper;

    public List<SearchRes> getDelinquentAdditionalCharges(
        @Valid SearchReq req
    ) {
        return mapper.selectDelinquentAdditionalCharges(req);
    }
}
