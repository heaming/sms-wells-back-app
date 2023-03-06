package com.kyowon.sms.wells.web.fee.confirm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.confirm.mapper.WfeeFeeSpecificationMapper;

import static com.kyowon.sms.wells.web.fee.confirm.dto.WfeeFeeSpecificationDto.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WfeeFeeSpecificationService {

    private final WfeeFeeSpecificationMapper mapper;
    public List<SearchRes> getFeeSpecifications(SearchReq dto) {
        return mapper.selectFeeSpecifications(dto);
    }

}
