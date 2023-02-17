package com.kyowon.sms.wells.web.contract.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailSummaryDto.FindReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailSummaryDto.FindRes;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiContractDetailSummaryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiContractDetailSummaryService {

    private final WctiContractDetailSummaryMapper mapper;

    public List<FindRes> getDetailSummary(FindReq dto) { return mapper.selectDetailSummary(dto);}
}
