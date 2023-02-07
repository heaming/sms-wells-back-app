package com.kyowon.sms.wells.web.contract.interfaces.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.interfaces.converter.WctiContractInstallConverter;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallDto;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractInstallDvo;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiContractInstallMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctiContractInstallService {
    private final WctiContractInstallMapper mapper;
    private final WctiContractInstallConverter converter;

    /**
     * 계약처, 설치처 정보 변경
     * @programId   EAI_WSSI1051
     * @param       req
     * @return      결과 성공 여부
     */
    @Transactional
    public String editContractInstallInf(WctiContractInstallDto.SaveReq req) {
        WctiContractInstallDvo dvo = converter.mapSaveReqToWctiContractInstallDvo(req);
        int result = mapper.updateContractInstallInf(dvo);

        return (result >= 1) ? "Y" : "N";
    }
}
