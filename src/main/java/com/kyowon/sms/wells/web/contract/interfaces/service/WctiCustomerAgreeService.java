package com.kyowon.sms.wells.web.contract.interfaces.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerAgreeDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerAgreeDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiCustomerAgreeMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiCustomerAgreeService {

    private final WctiCustomerAgreeMapper mapper;

    /**
    * 개인정보 동의 현황 조회
    * 고객번호 또는 계약번호 단위의 개인정보 동의 현황 조회한다.
    * @interfaceId   EAI_WCUI1014
    * @programId   W-SS-I-0050
    * @param       dto
    * @return      list
    */
    public List<SearchRes> getCustomerAgree(SearchReq dto) {
        if (StringUtils.isAnyEmpty(dto.cntrNo(), dto.cstNo())) {
            throw new BizException("고객번호 또는 계약번호는 필수입니다.");
        }
        return mapper.selectCustomerAgree(dto);
    }
}
