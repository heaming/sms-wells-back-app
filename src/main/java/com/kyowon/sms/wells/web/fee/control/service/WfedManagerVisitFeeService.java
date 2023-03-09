package com.kyowon.sms.wells.web.fee.control.service;

import static com.kyowon.sms.wells.web.fee.control.dto.WfedManagerVisitFeeDto.SearchReq;
import static com.kyowon.sms.wells.web.fee.control.dto.WfedManagerVisitFeeDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.control.mapper.WfedManagerVisitFeeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WfedManagerVisitFeeService {

    private final WfedManagerVisitFeeMapper mapper;

    public List<SearchRes> getManagerVisitFees(SearchReq dto) {
        return mapper.selectManagerVisitFees(dto);
    }
}
