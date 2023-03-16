package com.kyowon.sms.wells.web.service.common.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.common.dto.WsnzRegistrationBarCodeDto;
import com.kyowon.sms.wells.web.service.common.mapper.WsnzRegistrationBarCodeMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsnzRegistrationBarCodeService {
    private final WsnzRegistrationBarCodeMapper mapper;

    public WsnzRegistrationBarCodeDto.SearchRes getRegistrationBarCodes(
        WsnzRegistrationBarCodeDto.SearchReq dto
    ) {
        return mapper.selectRegistrationBarCode(dto);
    }

    public WsnzRegistrationBarCodeDto.SearchRes getRegistrationBarCodes(
        String qrcd
    ) {
        WsnzRegistrationBarCodeDto.SearchReq dto = new WsnzRegistrationBarCodeDto.SearchReq(qrcd);
        return getRegistrationBarCodes(dto);
    }

}
