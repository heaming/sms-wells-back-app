package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbIntegrationDepositNumberDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbIntegrationDepositNumberDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbIntegrationDepositNumberMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbIntegrationDepositNumberService {
    private final WwdbIntegrationDepositNumberMapper mapper;

    public PagingResult<SearchRes> getIntegrationDepositNumberPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectIntegrationDepositNumbers(dto, pageInfo);
    }

    public List<SearchRes> getIntegrationDepositNumberExcels(SearchReq dto) {
        return mapper.selectIntegrationDepositNumbers(dto);
    }
}
