package com.kyowon.sms.wells.web.service.interfaces.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.converter.WsniBarcodeProductInterfaceConverter;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniBarcodeProductInterfaceDto;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniBarcodeProductInterfaceDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniBarcodeProductInterfaceMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsniBarcodeProductInterfaceService {

    private final WsniBarcodeProductInterfaceMapper mapper;

    private final WsniBarcodeProductInterfaceConverter converter;

    private final MessageResourceService messageService;

    public WsniBarcodeProductInterfaceDto.SearchJsonRes getBarcodeProducts(
        WsniBarcodeProductInterfaceDto.SearchReq dto
    ) {
        log.info("[WsniRegistrationBarcodeInterfaceService.getRegistrationBarcodes] QRCD ::: " + dto.qrcd());
        WsniBarcodeProductInterfaceDvo dvo = new WsniBarcodeProductInterfaceDvo();

        try {
            WsniBarcodeProductInterfaceDto.SearchRes res = mapper.selectBarcodeProduct(dto);

            if (res.resultcode() > 0) {
                dvo.setResultCode("0");
                if (res.regi() > 0) {
                    dvo.setRegi("true");
                } else {
                    dvo.setRegi("false");
                }
            } else {
                dvo.setResultCode("1");
                dvo.setResultMessage(messageService.getMessage("MSG_TXT_BARCODE_INFO_INVALID")); //바코드 정보가 올바르지 않습니다
                if (res.regi() > 0) {
                    dvo.setRegi("true");
                } else {
                    dvo.setRegi("false");
                }
            }
        } catch (Exception e) {
            throw new BizException(messageService.getMessage("MSG_TXT_BARCODE_SEARCH_ERROR")); //바코드 조회 오류
        }

        return converter.mapBarcodeProductInterfaceDvoToJsonRes(dvo);
    }

    public WsniBarcodeProductInterfaceDto.SearchCustJsonRes getBarcodeSearchCustomers(
        WsniBarcodeProductInterfaceDto.SearchCustReq dto
    ) {
        log.info("[WsniRegistrationBarcodeInterfaceService.getBarcodeSearchCustomers] BARCODE ::: " + dto.barcode());
        WsniBarcodeProductInterfaceDto.SearchCustRes res;

        try {
            res = mapper.selectBarcodeSearchCustomer(dto);

            if(res == null || StringUtils.isEmpty(res.cntrNo())){
                throw new BizException(messageService.getMessage("MSG_TXT_RENTAL_NOT_EXIST")); //렌탈 정보가 존재하지 않습니다
            }
        } catch (Exception e) {
            throw new BizException(messageService.getMessage("MSG_TXT_BARCODE_SEARCH_ERROR")); //바코드 조회 오류
        }

        return converter.mapBarcodeProductCustDtoToJsonRes(res);
    }
}
