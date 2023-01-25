package com.kyowon.sms.wells.web.contract.risk.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.risk.converter.WctaContractConverter;
import com.kyowon.sms.wells.web.contract.risk.dto.WctaContractDto.RemoveReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctaContractDto.SearchRes;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctaCntrAprAkDvCdDvo;
import com.kyowon.sms.wells.web.contract.risk.mapper.WctaContractMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaContractService {

    private final WctaContractMapper mapper;
    private final WctaContractConverter converter;

    @Transactional
    public List<SearchRes> getApprovalAskDivides(String standardDt) {
        return mapper.selectApprovalAskDivides(standardDt);
    }

    @Transactional
    public int removeApprovalAskDivides(List<RemoveReq> dtos) {
        int processCount = 0;
        Iterator<RemoveReq> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            RemoveReq dto = iterator.next();
            WctaCntrAprAkDvCdDvo dvo = converter.mapRemoveReqToWctaCntrAprAkDvCdDvo(dto);
            int result = mapper.removeApprovalAskDivides(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_DEL_ERR");
            processCount += result;
        }
        return processCount;
    }

}
