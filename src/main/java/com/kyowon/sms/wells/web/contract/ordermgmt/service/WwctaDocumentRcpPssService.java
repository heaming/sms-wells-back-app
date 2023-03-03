package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WwctaDocumentRcpPssDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WwctaDocumentRcpPssDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WwctaDocumentRcpPssMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WwctaDocumentRcpPssService {

    private final WwctaDocumentRcpPssMapper mapper;

    public List<SearchRes> getDocumentRcpPss(SearchReq dto) {

        return mapper.selectDocumentRcpPss(dto);
    }
}
