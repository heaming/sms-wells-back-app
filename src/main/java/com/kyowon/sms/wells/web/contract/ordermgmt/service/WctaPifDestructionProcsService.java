package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPifDestructionProcsDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaPifDestructionProcsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaPifDestructionProcsService {
    private final WctaPifDestructionProcsMapper mapper;

    @Transactional
    public String savePifDestructionProcs(String cntrNo) {
        if (StringUtils.isEmpty(cntrNo))
            return "N";
        WctaPifDestructionProcsDvo dvo = new WctaPifDestructionProcsDvo();
        dvo.setCntrNo(cntrNo);
        dvo.setSpace(" ");
        mapper.updateCntrAdrpcBas(dvo);
        mapper.updateCntrAdrChHists(dvo);
        mapper.updateCntrStlmBas(dvo);
        mapper.updateCntrStlmChHists(dvo);
        return "Y";
    }
}
