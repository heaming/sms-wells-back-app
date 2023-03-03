package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WcteAlreadyReceivedMphDto.SearchRes;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WcteAlreadyReceivedMphMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WcteAlreadyReceivedMphService {

    private final WcteAlreadyReceivedMphMapper mapper;

    @Transactional
    public List<SearchRes> getAlreadyReceivedMph(String cntrNo, String cntCstNo) {

        if (StringUtil.isEmpty(cntrNo)) {
            throw new BizException("MSG_ALT_CHK_CNTR_NO"); //계약번호를 확인해주세요.
        }

        if (StringUtil.isEmpty(cntCstNo)) {
            throw new BizException("MSG_ALT_CHK_CNTR_CST_NO"); //계약고객번호를 확인해주세요.
        }

        return mapper.selectAlreadyReceivedMph(cntrNo, cntCstNo);
    }
}
