package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoiceDetailDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiTaxInvoiceDetailMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiTaxInvoiceDetailService {
    private final WctiTaxInvoiceDetailMapper mapper;

    /**
     * 세금계산서 상세목록 조회
     *
     * @programid EAI_WSSI1088
     * @param  String
     * @return list
     */
    public List<SearchRes> getTaxInvoiceDetails(String txinvId) {
        return mapper.selectTaxInvoiceDetails(txinvId);
    }
}

