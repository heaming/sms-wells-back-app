package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WwctaDocumentReceiptPssDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WwctaDocumentReceiptPssDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WwctaDocumentReceiptPssMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WwctaDocumentReceiptPssService {

    private final WwctaDocumentReceiptPssMapper mapper;

    public List<SearchRes> getDocumentReceipts(SearchReq dto) {

        return mapper.selectDocumentReceipts(dto);
    }
}
