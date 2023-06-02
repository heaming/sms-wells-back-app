package com.kyowon.sms.wells.web.service.interfaces.rest;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniIotQrcodeCustomerInterfaceDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniIotQrcodeCustomerInterfaceDto.SearchRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniIotQrcodeCustomerInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1 + ": Wells IoT QR코드 KIWI 연동 인터페이스")
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/iot-qrcode-customer")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsniIotQrcodeCustomerInterfaceController {

    private final WsniIotQrcodeCustomerInterfaceService service;

    @ApiOperation(value = "계약번호, 고객명 조회", notes = "wells IoT에서 바코드 번호로 계약번호, 고객명을 조회한다")
    @PostMapping
    public EaiWrapper getContractInfoByIotQrcode(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) {
        EaiWrapper<SearchRes> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getContractInfoByIotQrcode(reqWrapper.getBody()));
        return resWrapper;
    }
}
