package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaOrderDetailCssrConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailCssrDto.FindBaseRcpReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailCssrDto.FindBaseRcpRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailCssrDto.SaveRcpReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailCssrDto.SearchRcpRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailCssrDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaOrderDetailCssrMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaOrderDetailCssrService {

    private final WctaOrderDetailCssrMapper mapper;
    private final WctaOrderDetailCssrConverter converter;

    public List<SearchRcpRes> getCashSalesReceipts(String cntrNo, String cntrSn) {
        return mapper.selectCashSalesReceipts(cntrNo, cntrSn);
    }

    public FindBaseRcpRes getContractBaseInformation(FindBaseRcpReq dto) {
        return mapper.selectContractBaseInformation(dto);
    }

    @Transactional
    public int saveCashSalesReceipt(SaveRcpReq dto) {
        WctaOrderDetailCssrDvo dvo = converter.mapSaveRcpReqToWctaOrderDetailCssrDvo(dto);

        int processCount = 0;
        int checkCount = mapper.selectContractBaseCheck(dvo);

        if (checkCount <= 0) {
            processCount += mapper.insertCashSalesReceipt(dvo);
            processCount += mapper.insertCashSalesReceiptApprovalPresentState(dvo);
        } else {
            processCount += mapper.insertCashSalesReceiptApprovalPresentState(dvo);
            processCount += mapper.updateCashSalesReceiptApprovalPresentState(dvo);
        }

        return processCount;
    }

}
