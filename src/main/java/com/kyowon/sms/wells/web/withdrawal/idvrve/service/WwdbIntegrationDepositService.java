package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbIntegrationDepositDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbIntegrationDepositDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbIntegrationDepositMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbIntegrationDepositService {

    private final WwdbIntegrationDepositMapper mapper;

    @Transactional
    public PagingResult<SearchRes> getIntegrationDepositPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectIntegrationDeposit(dto, pageInfo);
    }

    @Transactional
    public List<SearchRes> getIntegrationDepositExcels(SearchReq dto) {
        return mapper.selectIntegrationDeposit(dto);
    }
}
