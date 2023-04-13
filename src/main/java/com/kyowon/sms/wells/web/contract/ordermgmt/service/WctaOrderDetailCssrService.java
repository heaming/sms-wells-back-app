package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailCssrDto.FindBaseRcpRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailCssrDto.SearchRcpRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaOrderDetailCssrMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaOrderDetailCssrService {

    private final WctaOrderDetailCssrMapper mapper;

    public List<SearchRcpRes> getCashSalesReceipts(String cntrNo, String cntrSn) {
        return mapper.selectCashSalesReceipts(cntrNo, cntrSn);
    }

    public FindBaseRcpRes getContractBaseInformation(String cntrNo, String cntrSn) {
        /* TODO: 테이블 모델링 완료 후 수정 예정 */
        return mapper.selectContractBaseInformation(cntrNo, cntrSn);
    }

}
