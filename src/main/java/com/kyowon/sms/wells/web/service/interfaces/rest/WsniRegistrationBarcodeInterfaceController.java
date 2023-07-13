package com.kyowon.sms.wells.web.service.interfaces.rest;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
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

    @ApiOperation(value = "W-SV-I-0011 바코드를 입력받아 등록된 바코드인지 확인 API")
    @PostMapping
    public EaiWrapper getRegistrationBarcodes(
        @Valid
        @RequestBody
        EaiWrapper<WsniRegistrationBarcodeInterfaceDto.SearchReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<WsniRegistrationBarcodeInterfaceDto.SearchJsonRes> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getRegistrationBarcodes(reqWrapper.getBody()));
        return resWrapper;
    }
}
