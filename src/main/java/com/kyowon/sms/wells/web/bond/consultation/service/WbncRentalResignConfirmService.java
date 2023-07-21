package com.kyowon.sms.wells.web.bond.consultation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignConfirmDto.SearchReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignConfirmDto.SearchRes;
import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncRentalResignConfirmMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WbncRentalResignConfirmService {
    private final WbncRentalResignConfirmMapper mapper;

    public List<SearchRes> getRentalResignConfirms(SearchReq dto) {
        return mapper.selectRentalResignConfirms(dto);
    }
}
