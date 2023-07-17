package com.kyowon.sms.wells.web.service.interfaces.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.converter.WsniRegistrationBarcodeInterfaceConverter;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniRegistrationBarcodeInterfaceDto;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniRegistrationBarcodeInterfaceDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniRegistrationBarcodeInterfaceMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsniRegistrationBarcodeInterfaceService {

    private final WsniRegistrationBarcodeInterfaceMapper mapper;

    private final WsniRegistrationBarcodeInterfaceConverter converter;

    private final MessageResourceService messageService;

    public WsniRegistrationBarcodeInterfaceDto.SearchJsonRes getRegistrationBarcodes(
        WsniRegistrationBarcodeInterfaceDto.SearchReq dto
    ) {
        log.info("[WsniRegistrationBarcodeInterfaceService.getRegistrationBarcodes] QRCD ::: " + dto.qrcd());
        WsniRegistrationBarcodeInterfaceDvo dvo = new WsniRegistrationBarcodeInterfaceDvo();

        try {
            WsniRegistrationBarcodeInterfaceDto.SearchRes res = mapper.selectRegistrationBarcode(dto);

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

        return converter.mapRegistrationBarcodeInterfaceDvoToJsonRes(dvo);
    }

}
