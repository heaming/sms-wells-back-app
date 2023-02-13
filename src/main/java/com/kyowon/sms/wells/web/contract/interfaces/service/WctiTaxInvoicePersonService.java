package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoicePersonDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiTaxInvoicePersonMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiTaxInvoicePersonService {
    private final WctiTaxInvoicePersonMapper mapper;

    /**
     * 세금계산서 담당자 정보 조회
     *
     * @programid EAI_WSSI1089
     * @param  String
     * @return list
     */
    public List<SearchRes> getTaxInvoicePersons(String psicNm) {
        return mapper.selectTaxInvoicePersons(psicNm);
    }
}
