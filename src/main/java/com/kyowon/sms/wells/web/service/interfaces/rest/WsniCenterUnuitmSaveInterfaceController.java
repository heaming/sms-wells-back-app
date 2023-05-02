package com.kyowon.sms.wells.web.service.interfaces.rest;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterUnuitmSaveInterfaceDto.CreateReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterUnuitmSaveInterfaceDto.CreateRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniCenterUnuitmSaveInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1)
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/unusual-items")
@Validated
@RequiredArgsConstructor
public class WsniCenterUnuitmSaveInterfaceController {
    private final WsniCenterUnuitmSaveInterfaceService service;

    @ApiOperation(value = "고객센터 인터페이스 특이사항 저장", notes = "고객센터 인터페이스 특이사항 저장")
    @PostMapping
    public EaiWrapper createUnusualItem(
        @Valid
        @RequestBody
        EaiWrapper<CreateReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<CreateRes> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.createUnusualItem(reqWrapper.getBody()));

        return resWrapper;
    }
}
