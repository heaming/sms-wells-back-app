package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbIntegrationDepositNumberInquiryDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbIntegrationDepositNumberInquiryDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwwdbIntegrationDepositNumberInquiryMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwwdbIntegrationDepositNumberInquiryService {
    private final WwwdbIntegrationDepositNumberInquiryMapper mapper;

    public PagingResult<SearchRes> getIntegrationDepositNumberInquiryPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectIntegrationDepositNumberInquiryPages(dto, pageInfo);
    }

    public List<SearchRes> getIntegrationDepositNumberInquiryExcels(SearchReq dto) {
        return mapper.selectIntegrationDepositNumberInquiryPages(dto);
    }
}
