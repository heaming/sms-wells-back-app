package com.kyowon.sms.wells.web.fee.aggregate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.aggregate.converter.WfeaActivityPeopleConverter;
import com.kyowon.sms.wells.web.fee.aggregate.mapper.WfeaActivityPeopleMapper;

import static com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaActivityPeopleDto.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WfeaActivityPeopleService {

    private final WfeaActivityPeopleMapper mapper;
    private final WfeaActivityPeopleConverter converter;

    public List<SearchRes> getActivityPeoples(SearchReq dto) {
        return mapper.selectActivityPeoples(dto);
    }

}
