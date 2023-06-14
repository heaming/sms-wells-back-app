package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbSinglePaymentBulkChangeDto.SearchRes;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbSinglePaymentBulkChangeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbSinglePaymentBulkChangeService {

    private final WctbSinglePaymentBulkChangeMapper mapper;

    public List<SearchRes> getSinglePaymentBulkChangs(String cntrNo, String cntrSn, String rfDt) {
        return mapper.selectSinglePaymentBulkChangs(cntrNo, cntrSn, rfDt);
    }
}
