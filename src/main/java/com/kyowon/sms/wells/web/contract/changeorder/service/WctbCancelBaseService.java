package com.kyowon.sms.wells.web.contract.changeorder.service;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelBaseDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelBaseDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbCancelBaseMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbCancelBaseService {

    private final WctbCancelBaseMapper mapper;

    public List<SearchRes> getCancelBases(SearchReq dto) {
        return mapper.selectCancelBases(dto);
    }
}
