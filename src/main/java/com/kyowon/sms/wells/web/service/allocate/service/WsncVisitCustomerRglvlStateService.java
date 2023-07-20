package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncVisitCustomerRglvlStateConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitCustomerRglvlStateDto.FindOrganizationRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitCustomerRglvlStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitCustomerRglvlStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncVisitCustomerRglvlStateMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncVisitCustomerRglvlStateService {
    private final WsncVisitCustomerRglvlStateMapper mapper;
    private final WsncVisitCustomerRglvlStateConverter converter;

    public List<SearchRes> getVisitCustomerRglvlState(
        SearchReq dto
    ) {
        return mapper.selectVisitCustomerRglvlState(dto);
    }

    public List<SearchRes> getVisitCustomerRglvlStateForExcelDownload(
        SearchReq dto
    ) {
        return mapper.selectVisitCustomerRglvlState(dto);
    }

    public FindOrganizationRes getOrganizationInfo(String ogId) {
        return mapper.selectOrganizationInfo(ogId);
    }

}
