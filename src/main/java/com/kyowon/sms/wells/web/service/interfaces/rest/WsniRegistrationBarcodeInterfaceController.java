package com.kyowon.sms.wells.web.service.interfaces.rest;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniRegistrationBarcodeInterfaceDto;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniRegistrationBarcodeInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1)
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/regist-barcodes")
@RequiredArgsConstructor
@Validated
public class WsniRegistrationBarcodeInterfaceController {

    private final WsniRegistrationBarcodeInterfaceService service;

    @ApiOperation(value = "W-SV-I-0010 QR코드별 상품정보 조회 팝업")
    @GetMapping
    public EaiWrapper getBarcodeProducts(
        @Valid
        @RequestBody
        EaiWrapper<WsniRegistrationBarcodeInterfaceDto.SearchReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<WsniRegistrationBarcodeInterfaceDto.SearchJsonRes> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getRegistrationBarcodes(reqWrapper.getBody()));
        return resWrapper;
    }
}
